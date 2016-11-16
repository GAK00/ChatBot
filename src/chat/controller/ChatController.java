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
	//0 for memes
	//1 for politics
	//2 for tech

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

	public String Chat(String input)
	{
		return generateResponse(input);
	}
	
	private String yesNoCheckers(String input)
	{
		String response = "";
		if (input.toLowerCase().contains("yes"))
		{
			response += "\nCool I am gald you agree\n";

		} else if (input.toLowerCase().contains("no"))
		{
			response += "\nOh well it is ok you don't agree\n";

		} else
		{
			response += "\nyour answer does not make sense\n";
		}
		return response;
	}

	private String generateResponse(String input){
		String response = "";
		response += "You said:\n " + input;
		if (stupidBot.retriveYesNo())
		{
			yesNoCheckers(input);

		} else
		{
			response += "\n"+useChatbotCheckers(input)+ "\n";
		}
		response += "\n\n"+stupidBot.generateQuestion(input)+"\n\n\n\n";
		return response;
	}

	private String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about " + input;
		boolean understoodContent = false;
		float toAdd = 1;
		float toSub = -.5;
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		if (stupidBot.memeChecker(input))
		{
			if (Float.compare(stupidBot.getMemeLevel(), 10) < 0)
			{
				if(stupidBot.getLastProbe()==0)
				{
					toAdd += .5;
				}
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() + toAdd);
				toAdd = 1;
			}

			checkedInput += "\nYou like memes :)\n";

			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getMemeLevel(), 0) > 0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() - 0.5f);
			}
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
			if (Float.compare(stupidBot.getPoliticalLevel(), 10) < 0)
			{
				if(stupidBot.getLastProbe()==1)
				{
					toAdd += .5;
				}
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() + toAdd);
				toAdd = 1;
			}
			checkedInput += "\n I hate politics \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getPoliticalLevel(), 0) > 0)
			{
				if(stupidBot.getLastProbe()==1)
				{
					toSub-=1;
				}
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() - toSub);
				toSub = -0.5f;
			}
		}
		if (stupidBot.inputHTMLChecker(input))
		{
			if (Float.compare(stupidBot.getTechLevel(), 10) < 0)
			{

				stupidBot.setTechLevel(stupidBot.getTechLevel() + 0.75f);
			}
			checkedInput += "\n You know about the HTMLS!!!! \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{

				stupidBot.setTechLevel(stupidBot.getTechLevel() - 0.5f);
			}
		}
		if (stupidBot.twitterChecker(input))
		{
			if (Float.compare(stupidBot.getTechLevel(), 10) < 0)
			{

				stupidBot.setTechLevel(stupidBot.getTechLevel() + 0.75f);
			}
			checkedInput += "\n Was that a tweet ?!?!?!?!?!?!?!?!?!? \n";
		} else
		{
			if (Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{
				stupidBot.setTechLevel(stupidBot.getTechLevel() - 0.5f);
			}
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
