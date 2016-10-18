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
		String response = "words";

		while (stupidBot.lengthChecker(response))
		{
			response = display.collectResponse("What do you wan to talk about ?");
		}
	}

}
