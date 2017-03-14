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
			results = findMostPopular(20);

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
		results = findMostPopular(100);
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
		String pop = findMostPopular();
		// System.out.println(pop);
		mostCommon += pop;
		return mostCommon;
	}

	public String findMostPopular()
	{
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		while (!tweetedWords.isEmpty())
		{
			String currentSearch = tweetedWords.get(0);
			int currentCount = 0;
			while (tweetedWords.remove(currentSearch))
			{
				currentCount++;
			}
			removedStrings.add(currentSearch);
			counts.add(currentCount);
		}
		int maxIndex = 0;
		int maxValue = counts.get(0);
		for (int index = 1; index < counts.size(); index++)
		{
			if (counts.get(index) > maxValue)
			{
				maxValue = counts.get(index);
				maxIndex = index;
			}
		}
		return removedStrings.get(maxIndex) + " and has been used: " + counts.get(maxIndex) + " times" + "or "
				+ DecimalFormat.getPercentInstance().format((double) counts.get(maxIndex) / tweetedWords.size());
	}

	public String[] findMostPopular(int Number)
	{
		String[] popWords = new String[Number];
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			tweetedWords.set(index, tweetedWords.get(index).toLowerCase());
		}

		int pos = 0;
		while (pos < tweetedWords.size())
		{

			// System.out.println(DecimalFormat.getPercentInstance().format((double)
			// pos / tweetedWords.size()));

			int count = 0;
			for (int index = 0; index < tweetedWords.size(); index++)
			{
				if (tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(pos)))
				{
					count++;
				}

			}
			removedStrings.add(tweetedWords.get(pos));
			counts.add(count);
			while (pos < tweetedWords.size() && removedStrings.contains(tweetedWords.get(pos)))
			{

				pos++;
			}
		}
		for (int index = 0; index < Number; index++)
		{
			if (removedStrings.size() > 0)
			{
				int maxIndex = 0;
				int maxValue = counts.get(0);
				for (int pos2 = 1; pos2 < counts.size(); pos2++)
				{
					if (counts.get(pos2) > maxValue)
					{
						maxValue = counts.get(pos2);
						maxIndex = pos2;
					}
				}

				popWords[index] = removedStrings.get(maxIndex) + " and has been used: " + counts.get(maxIndex) + " times" + "or "
						+ DecimalFormat.getPercentInstance().format((double) counts.get(maxIndex) / tweetedWords.size());
				removedStrings.remove(maxIndex);
				counts.remove(maxIndex);
			}
		}
		return popWords;
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
