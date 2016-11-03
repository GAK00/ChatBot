package chat.controller;

import chat.view.ChatFrame;
import chat.model.Chatbot;
import chat.view.ChatViewer;

import java.util.Random;
public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	private Random rand;
	private ChatFrame baseFrame;

	public ChatController()
	{
		stupidBot = new Chatbot("wall-e");
		display = new ChatViewer();
		rand = new Random();
		baseFrame = new ChatFrame(this);
	}

	public void start()
	{
		baseFrame.getPanel().setPicture("images/chatbot.png");
		
	}

	public Chatbot getChatbot()
	{

		return stupidBot;
	}

	public String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about " + input;
		boolean understoodContent = false;
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		if (stupidBot.memeChecker(input))
		{
			stupidBot.setMemeLevel(stupidBot.getMemeLevel() + 1);
			
				checkedInput += "\nYou don't like memes :(\n";
			
			understoodContent = true;
		} else
		{
			stupidBot.setMemeLevel(stupidBot.getMemeLevel() - 0.5f);
		}
		if (stupidBot.contentChecker(input))
		{
			checkedInput += "\nYou know my secret topic!\n";
			understoodContent = true;
		}
		if (stupidBot.keyboardMashChecker(input))
		{
			checkedInput += "\n Mashing is wrong \n";
			understoodContent = true;
		}
		if (stupidBot.politicalTopicChecker(input))
		{
			stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel()+1);
			checkedInput += "\n I hate politics \n";
			understoodContent = true;
		}
		else{stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel()-0.5f);}
		if (stupidBot.inputHTMLChecker(input))
		{
			stupidBot.setTechLevel(stupidBot.getTechLevel()+0.75f);
			checkedInput += "\n You know about the HTMLS!!!! \n";
			understoodContent = true;
		}
		else{
			stupidBot.setTechLevel(stupidBot.getTechLevel()-0.5f);
		}
		if (stupidBot.twitterChecker(input))
		{
			stupidBot.setTechLevel(stupidBot.getTechLevel()+0.75f);
			checkedInput += "\n Was that a tweet ?!?!?!?!?!?!?!?!?!? \n";
		}
		else{
			stupidBot.setTechLevel(stupidBot.getTechLevel() - 0.5f);
		}
		if (understoodContent)
		{
			checkedInput = checkedInput.substring(35, checkedInput.length());
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}

}
