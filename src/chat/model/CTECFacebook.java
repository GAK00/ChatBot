package chat.model;

import java.util.ArrayList;
import java.util.List;

import chat.controller.ChatController;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Page;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.User;
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
		this.setPercentComplete(0);
		this.setPrefix("Downloading for: " + topic);
		try
		{
			List<Page> pages = facebook.search().searchPages(topic);
			this.setPercentComplete(.9);
			for (Page page : pages)
			{
				posts.addAll(facebook.getFeed(page.getId()));
			}
		} catch (FacebookException e)
		{
			getBaseController().handleErrors(e);
		}
		turnStatusesToFormatedWords();
		results = this.findMostPopular(20, words);
		return results;
	}

	public String[] findRelatedTopics(String[] topics)
	{
		String[] results = null;
		words.clear();
		for (int index = 0; index < topics.length; index++)
		{

			this.setPercentComplete(0);
			this.setPrefix("Downloading for: " + topics[index]);
			posts.clear();
			try
			{
				this.setPercentComplete(.1);
				List<Page> pages = facebook.search().searchPages(topics[index], new Reading().limit(5));
				this.setPercentComplete(.9);
				for (Page page : pages)
				{
					posts.addAll(facebook.getFeed(page.getId()));
				}
			} catch (FacebookException e)
			{
				getBaseController().handleErrors(e);
			}
			turnStatusesToFormatedWords();

		}
		results = this.findMostPopular(100, words);
		return results;
	}

	public void turnStatusesToWords()
	{
		this.setPrefix("Formatting");
		int size = posts.size();
		double current = 0;
		for (Post post : posts)
		{
			this.setPercentComplete(current / size);
			current++;
			for (String word : post.getMessage().split(" "))
			{
				words.add(word);
			}
		}
	}

	public void turnStatusesToFormatedWords()
	{
		this.setPrefix("Formatting");
		int size = posts.size();
		double current = 0;
		for (Post post : posts)
		{
			this.setPercentComplete(current / size);
			current++;
			if (post.getMessage() != null)
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

	public String getMostPopularWord(String user)
	{
		words.clear();
		posts.clear();
		String popular = "";
		try
		{
			this.setPrefix("Downloading for user: " + user);
			this.setPercentComplete(0);
			List<User> users = facebook.getUsers(user);
			int index = 0;
			int size = users.size();
			for(User currentUser : users)
			{
				this.setPercentComplete((double)index/size);
				index++;
				try
				{
					posts.addAll(facebook.getPosts(currentUser.getId()));
				}
				catch(FacebookException e)
				{
				}
				this.turnStatusesToFormatedWords();
				popular = this.findMostPopular(words);
			}
		} catch (FacebookException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return popular;
	}
}
