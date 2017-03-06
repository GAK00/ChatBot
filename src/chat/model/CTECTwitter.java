package chat.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chat.controller.ChatController;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CTECTwitter
{
	private ChatController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> filter;

	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		this.chatbotTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		filter = new ArrayList<String>();
		setupFilter();
	}

	private void setupFilter()
	{
		String wordList = FileController.readFile(baseController, "Words");
		filter = Arrays.asList(wordList.split("\n"));
		System.out.println("size");
		for(String out : filter)
		{
			System.out.println(out);
		}
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
	}

	public String searchTwitter(String username)
	{
		return "";
	}
}
