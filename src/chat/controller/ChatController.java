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
		String response = display.collectUserTextWithPics("What do you wan to talk about ?", null);

		while (stupidBot.lengthChecker(response))
		{
			int chatChoice = rand.nextInt(5)+1;

			display.diplayMessage(useChatbotCheckers(response));
			if(chatChoice == 1){
			response = display.collectResponse("Oh, you want to talk about " + response + "? Tell me more...");}
			else if(chatChoice == 2){
				response = display.collectUserTextWithPics("Do you know any memes", "images/doYouLikeMemes.png");
			}
			else if(chatChoice ==3){
				response = display.collectUserTextWithPics("What are yout thoughts on politics", "images/Politics.png");
			}
			else if(chatChoice == 4){
				response = display.collectUserTextWithPics("Do you know any memes", "images/Internet.png");
			}
			else if(chatChoice == 5){
				response = display.collectResponse("I don't know much about " + response + " please elaborate");
			}
			if (response == null)
			{
				System.exit(0);
			}
			useChatbotCheckers(response);
			
		}
	}

	public Chatbot getChatbot()
	{

		return stupidBot;
	}

	private String useChatbotCheckers(String input)
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
			understoodContent = true;
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}

}
