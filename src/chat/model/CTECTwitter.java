package chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatController;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CTECTwitter
{
	private ChatController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private String[] filter;

	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		this.chatbotTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
		filter = setupFilter();
	}

	private String[] setupFilter()
	{
		int wordCount = 0;
		Scanner wordScanner = new Scanner(this.getClass().getResourceAsStream("Words.txt"));
		while (wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		String[] boringWords = new String[wordCount];
		wordCount = 0;
		wordScanner = new Scanner(this.getClass().getResourceAsStream("Words.txt"));
		while (wordScanner.hasNextLine())
		{
			boringWords[wordCount] = wordScanner.nextLine();
			wordCount++;
		}
		wordScanner.close();
		return boringWords;
	}

	public void sendTweet(String tweet)
	{
		try
		{
			chatbotTwitter.updateStatus(tweet);
		} catch (TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		} catch (Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}

	private void collectTweets(String username)
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
				baseController.handleErrors(e);
			}
			page++;
		}
		System.out.println("tweets Loaded: " + searchedTweets.size());
	}

	public String searchTwitter(String username)
	{
		String mostCommon = "";
		collectTweets(username);
		turnStatusesToWords();
		filter();
		String pop = findMostPopular();
		//System.out.println(pop);
		mostCommon += pop;
		return mostCommon;
	}

	private void removeEmptyText()
	{
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			if (tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}

	private String findMostPopular()
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
		for(int index = 1; index<counts.size(); index++)
		{
			if(counts.get(index)>maxValue)
			{
				maxValue = counts.get(index);
				maxIndex = index;
			}
		}
		return removedStrings.get(maxIndex)+" and has been used: " + counts.get(maxIndex)+" times";
	}

	private void filter()
	{
		removeEmptyText();
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			for (int filterIndex = 0; filterIndex<filter.length; filterIndex++)
			{
				//System.out.println(filter[filterIndex]);
				if (tweetedWords.get(index).equalsIgnoreCase(filter[filterIndex]))
				{
					//System.out.println("Removing" +filter[filterIndex]);
					tweetedWords.remove(index);
					if(index != 0){
					index--;}
				}
			}
		}
	}

	private void turnStatusesToWords()
	{
		for (Status currentStatus : searchedTweets)
		{
			for (String word : currentStatus.getText().split(" "))
			{
				tweetedWords.add(word);
			}
		}
	}

	private void turnTweetToWords()
	{
		for (Status currentStatus : searchedTweets)
		{
			for (String word : currentStatus.getText().split(" "))
			{
				if (!word.trim().equals("") && !filter(word))
				{
					tweetedWords.add(word);
				}
			}
		}
	}

	private boolean filter(String toFilter)
	{
		for (String word : filter)
		{
			if (toFilter.equalsIgnoreCase(word))
			{
				return true;
			}

		}
		return false;
	}

}
