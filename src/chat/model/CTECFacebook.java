package chat.model;

import chat.controller.ChatController;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

public class CTECFacebook extends CTECMedia
{
	Facebook facebook;
	public CTECFacebook(ChatController baseController)
	{
		super(baseController);
		facebook = new FacebookFactory().getInstance();

	}

	
	public String[] findRelatedTopics(String topic)
	{
		return null;
	}


	public String[] findRelatedTopics(String[] topics)
	{
		return null;
	}


	public String findMostPopular()
	{
		return null;
	}

	public String[] findMostPopular(int Number)
	{

		return null;
	}


	public void turnStatusesToWords()
	{
		
	}

	public void turnStatusesToFormatedWords()
	{
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
}
