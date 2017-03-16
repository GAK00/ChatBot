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
		this.setPercentComplete(0);
		this.setPrefix("Loading for: " + topic);
		searchedTweets.clear();
		tweetedWords.clear();
		String[] results;
		Query query = new Query(topic);
		query.setCount(100);
		query.setGeoCode(new GeoLocation(40.522, -111.939), 25000, Query.KILOMETERS);
		query.setSince("2010-1-1");
		try
		{
			this.setPrefix("downloading tweets for: " + topic);
			QueryResult result = chatbotTwitter.search(query);
			this.setPercentComplete(0.50);
			int tweetSize = result.getTweets().size();
			for (Status tweet : result.getTweets())
			{
				searchedTweets.add(tweet);
				this.setPercentComplete(.5+((double)searchedTweets.size()/tweetSize)/2);
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
			this.setPercentComplete(0);
			this.setPrefix("Loading for: " + topics[index]);
			Query query = new Query(topics[index]);
			query.setCount(1000);
			query.setGeoCode(new GeoLocation(40.522, -111.939), 250, Query.KILOMETERS);
			query.setSince("2010-1-1");
			try
			{
				this.setPrefix("downloading tweets for: " + topics[index]);
				QueryResult result = chatbotTwitter.search(query);
				this.setPercentComplete(0.50);
				searchedTweets.clear();
				int tweetSize = result.getTweets().size();
				for (Status tweet : result.getTweets())
				{
					searchedTweets.add(tweet);
					this.setPercentComplete(.5+((double)searchedTweets.size()/tweetSize)/2);
				}
				turnStatusesToFormatedWords();

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
		this.setPrefix("downloading data for user: " + username);
		this.setPercentComplete(0);
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		while (page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				List<Status> results =chatbotTwitter.getUserTimeline(username, statusPage);
				searchedTweets.addAll(results);
				this.setPercentComplete(100);
			} catch (TwitterException e)
			{
				getBaseController().handleErrors(e);
			}
			page++;
		}
	}

	public String searchMedia(String username)
	{
		this.setPrefix("loading");
		this.setPercentComplete(0);
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
		this.setPrefix("Converting tweets to words");
		this.setPercentComplete(0);
		double processed =0;
		int toProcess = searchedTweets.size();
		for (Status currentStatus : searchedTweets)
		{
			processed++;
			this.setPercentComplete(processed/toProcess);
			for (String word : currentStatus.getText().split(" "))
			{
				word = removePunc(word);
				tweetedWords.add(word);
			}
		}
	}

	public void turnStatusesToFormatedWords()
	{
		this.setPercentComplete(0);
		this.setPrefix("Formatting");
		double processed =0;
		int toProcess = searchedTweets.size();
		for (Status currentStatus : searchedTweets)
		{
			processed++;
			this.setPercentComplete(processed/toProcess);
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
