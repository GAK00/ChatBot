package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer display;

	public ChatController()
	{
		stupidBot = new Chatbot("wall-e");
		display = new ChatViewer();
	}

	public void start()
	{
		String response = display.collectResponse("What do you wan to talk about ?");

		while (stupidBot.lengthChecker(response))
		{
			display.diplayMessage(useChatbotCheckers(response));;
			response = display.collectResponse("Oh, you want to talk about " + response + "? Tell me more..."); 
			
		}
	}

	public Chatbot getChatbot()
	{

		return stupidBot;
	}
	private String useChatbotCheckers(String input){
		String checkedInput = "I have no idea what you mean about " + input;
		boolean understoodContent = false;
		if(stupidBot.quitChecker(input)){
			System.exit(0);
		}
		if(stupidBot.memeChecker(input))
		{
			checkedInput += "\nYou like memes!\n";
			understoodContent = true;
		}
		if(stupidBot.contentChecker(input))
		{
			checkedInput += "\nYou know my secret topic!\n";
			understoodContent = true;
		}
		if(stupidBot.keyboardMashChecker(input)){
			checkedInput += "\n Mashing is wrong \n";
			understoodContent = true;
		}
		if(stupidBot.politicalTopicChecker(input)){
			checkedInput += "\n I hate politics \n";
			understoodContent = true;
		}
		if(stupidBot.inputHTMLChecker(input)){
			checkedInput += "\n You know about the HTMLS!!!! \n";
			understoodContent = true;
		}
		if(stupidBot.twitterChecker(input)){
			checkedInput += "\n Was that a tweet ?!?!?!?!?!?!?!?!?!? \n";
		}
		if(understoodContent){
			checkedInput = checkedInput.substring(35, checkedInput.length());
			understoodContent = true;
		}
		
		return checkedInput;
	}

}
