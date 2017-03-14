package chat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chat.controller.ChatController;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.auth.AccessToken;

public class CTECFacebook extends CTECMedia
{
	Facebook facebook;
	List<Post> posts;
	List<String> words;

	public CTECFacebook(ChatController baseController)
	{
		super(baseController);
		facebook = new FacebookFactory().getInstance();
		words = new ArrayList<String>();
		posts = new ArrayList<Post>();
		facebook.setOAuthAccessToken(refreshToken());

	}

	public String[] findRelatedTopics(String topic)
	{
		posts.clear();
		words.clear();
		String[] results = null;
		for (int index = 0; index < 100; index++)
		{
			try
			{
				posts.addAll(facebook.getFeed(Integer.toString(index)));
			} catch (FacebookException e)
			{
				System.out.println(index);
			}
			System.out.println(posts.size());
		}
		for (int index = 0; index < posts.size(); index++)
		{
			if (!posts.get(index).getMessage().toLowerCase().contains(topic.toLowerCase()))
			{
				posts.remove(index);
				index--;
			}
		}
		turnStatusesToFormatedWords();
		for (String word : words)
		{
			System.out.println(word);
		}
		results = this.findMostPopular(20, words);
		return results;
	}

	public String[] findRelatedTopics(String[] topics)
	{

		String[] results = null;
		words.clear();
		for (int index = 0; index < topics.length; index++)
		{
			posts.clear();
			for (int index2 = 0; index2 < 100; index2++)
			{
				try
				{
					posts = facebook.getFeed(Integer.toString(index));
				} catch (FacebookException e)
				{
					System.out.println(index);
				}
			}
			for (int index2 = 0; index2 < posts.size(); index2++)
			{
				if (!posts.get(index2).getMessage().toLowerCase().contains(topics[index].toLowerCase()))
				{
					posts.remove(index2);
					index--;
				}
			}
			turnStatusesToFormatedWords();

		}
		results = this.findMostPopular(100, words);
		return results;
	}

	public void turnStatusesToWords()
	{
		for (Post post : posts)
		{
			for (String word : post.getMessage().split(" "))
			{
				words.add(word);
			}
		}
	}

	public void turnStatusesToFormatedWords()
	{
		for (Post post : posts)
		{
			for (String word : post.getMessage().split(" "))
			{
				if (!word.trim().equalsIgnoreCase("") && !filter(word) && !(word == null))
				{
					word = removePunc(word);
					words.add(word);
				}
			}
		}
	}

	public void sendMessage(String message)
	{
		try
		{
			facebook.postStatusMessage(message);
		} catch (FacebookException e)
		{
			getBaseController().handleErrors(e);
		}
	}

	private AccessToken refreshToken()
	{
		try
		{
			String config = FileController.readFile(getBaseController(), "facebook4j.properties");
			String[] configlines = config.split("\n");
			AccessToken extended = facebook.extendTokenExpiration(facebook.getOAuthAccessToken().getToken());
			configlines[4] = "oauth.accessToken=" + extended.getToken();
			String newConfig = "";
			for (int index = 1; index < configlines.length; index++)
			{
				if (index != 1)
				{
					newConfig += "\n";
				}
				newConfig += configlines[index];
			}
			FileController.saveFile(getBaseController(), "facebook4j.properties", newConfig);
			return extended;
		} catch (FacebookException e)
		{
			System.out.println("error");
			return facebook.getOAuthAccessToken();
		}
	}
}
