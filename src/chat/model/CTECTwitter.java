package chat.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatController;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CTECTwitter extends CTECMedia
{

	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;

	public CTECTwitter(ChatController baseController)
	{
		super(baseController);
		this.chatbotTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
	}

	public void sendMessage(String message)
	{
		try
		{
			chatbotTwitter.updateStatus(message);
		} catch (TwitterException tweetError)
		{
			getBaseController().handleErrors(tweetError);
		} catch (Exception otherError)
		{
			getBaseController().handleErrors(otherError);
		}
	}

	public String[] findRelatedTopics(String topic)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		String[] results;
		Query query = new Query(topic);
		query.setCount(10000000);
		query.setGeoCode(new GeoLocation(40.522, -111.939), 25000000, Query.KILOMETERS);
		query.setSince("2010-1-1");
		try
		{
			QueryResult result = chatbotTwitter.search(query);

			for (Status tweet : result.getTweets())
			{
				searchedTweets.add(tweet);
			}
			turnStatusesToFormatedWords();
			System.out.println(tweetedWords.size());
			results = findMostPopular(20,tweetedWords);

		} catch (TwitterException error)
		{
			getBaseController().handleErrors(error);
			results = null;
		}
		return results;
	}

	public String[] findRelatedTopics(String[] topics)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		String[] results;
		for (int index = 0; index < topics.length; index++)
		{
			System.out.println("querring " + topics[index]);
			Query query = new Query(topics[index]);
			query.setCount(1000);
			query.setGeoCode(new GeoLocation(40.522, -111.939), 250, Query.KILOMETERS);
			query.setSince("2010-1-1");
			try
			{
				QueryResult result = chatbotTwitter.search(query);
				searchedTweets.clear();
				for (Status tweet : result.getTweets())
				{
					searchedTweets.add(tweet);
				}
				System.out.println("searched tweets" + searchedTweets.size());
				turnStatusesToFormatedWords();
				System.out.println("words size" + tweetedWords.size());

			} catch (TwitterException error)
			{
				getBaseController().handleErrors(error);
				results = null;
			}
		}
		results = findMostPopular(100,tweetedWords);
		return results;
	}

	public void collectStatuses(String username)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		while (page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				searchedTweets.addAll(chatbotTwitter.getUserTimeline(username, statusPage));
			} catch (TwitterException e)
			{
				getBaseController().handleErrors(e);
			}
			page++;
		}
		System.out.println("tweets Loaded: " + searchedTweets.size());
	}

	public String searchMedia(String username)
	{
		String mostCommon = "";
		collectStatuses(username);
		turnStatusesToWords();
		filter(tweetedWords);
		String pop = findMostPopular(tweetedWords);
		// System.out.println(pop);
		mostCommon += pop;
		return mostCommon;
	}

	public void turnStatusesToWords()
	{
		for (Status currentStatus : searchedTweets)
		{
			for (String word : currentStatus.getText().split(" "))
			{
				word = removePunc(word);
				tweetedWords.add(word);
			}
		}
	}

	public void turnStatusesToFormatedWords()
	{
		for (Status currentStatus : searchedTweets)
		{
			for (String word : currentStatus.getText().split(" "))
			{

				if (!word.trim().equals("") && !filter(word))
				{
					word = removePunc(word);
					tweetedWords.add(word);
				}
			}
		}
	}

}
