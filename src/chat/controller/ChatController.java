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
	String lastQuestion;
	String addQuestion;

	// 0 for memes
	// 1 for politics
	// 2 for tech

	public ChatController()
	{
		stupidBot = new Chatbot("wall-e");
		display = new ChatViewer();
		rand = new Random();
		baseFrame = new ChatFrame(this);
		lastQuestion = "";
		addQuestion = "";

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
		String response = "";
		response = "ChatBot Asks: " + stupidBot.generateQuestion(input) + "?\n\n";
		lastQuestion = response;
		if (!addQuestion.equals(""))
		{
			response += addQuestion;
		}
		response += generateResponse(input);
		setPicture();
		return response;
	}

	public String format(String conversation)
	{
		if (!lastQuestion.equals(""))
		{
			conversation = conversation.replaceFirst(lastQuestion.trim(), "");
			conversation = conversation.substring(1, conversation.length());
			lastQuestion = lastQuestion.replaceFirst("[\n]{2,}", "\n");
			lastQuestion = lastQuestion.replaceFirst("ChatBot Asks: ", "ChatBot Asked: ");
			addQuestion = lastQuestion;

		}
		return conversation;
	}

	private void setPicture()
	{
		if (stupidBot.getLastProbe() == 0)
		{
			baseFrame.getPanel().setPicture("images/doYouLikeMemes.png");
		}

		if (stupidBot.getLastProbe() == 1)
		{
			baseFrame.getPanel().setPicture("images/Politics.png");
		}

		if (stupidBot.getLastProbe() == 2)
		{
			baseFrame.getPanel().setPicture("images/Internet.png");
		}
	}

	private String yesNoCheckers(String input)
	{
		String response = "";
		if (input.toLowerCase().contains("yes"))
		{
			if (stupidBot.getLastProbe() == 0 && Float.compare(stupidBot.getMemeLevel(), 10) < 0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() + 1.5f);
			}
			if (stupidBot.getLastProbe() == 1 && Float.compare(stupidBot.getPoliticalLevel(), 10) < 0)
			{
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() + 1.5f);
			}
			if (stupidBot.getLastProbe() == 2 && Float.compare(stupidBot.getTechLevel(), 10) < 0)
			{
				stupidBot.setTechLevel(stupidBot.getTechLevel() + 1.5f);
			}
			response += "\nCool I am gald you agree\n";

		} else if (input.toLowerCase().contains("no"))
		{
			if (stupidBot.getLastProbe() == 0 && Float.compare(stupidBot.getMemeLevel(), 0) > 0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() - 1.5f);
			}
			if (stupidBot.getLastProbe() == 1 && Float.compare(stupidBot.getPoliticalLevel(), 0) > 0)
			{
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() - 1.5f);
			}
			if (stupidBot.getLastProbe() == 2 && Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{
				stupidBot.setTechLevel(stupidBot.getTechLevel() - 1.5f);
			}
			response += "\nOh well it is ok you don't agree\n";

		} else
		{
			response += "\nyour answer does not make sense\n";
		}
		return response;
	}

	private String generateResponse(String input)
	{
		String response = "";
		response += "You said: " + input;
		if (stupidBot.retriveYesNo())
		{
			response += "\n" + "ChatBot Said: " + yesNoCheckers(input);

		} else
		{
			response += "\n" + "ChatBot Said: " + useChatbotCheckers(input);
		}

		return response;
	}

	private String useChatbotCheckers(String input)
	{
		String unknown =randomUnknownGenerator();
		String checkedInput =  unknown + input;
		boolean understoodContent = false;
		float toAdd = 1;
		float toSub = .5f;
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		if (stupidBot.isGreeting(input))
		{
			int greeting = rand.nextInt(stupidBot.getGreetings().size());
			checkedInput += stupidBot.getGreetings().get(greeting) + "\n";
			understoodContent = true;
		}
		if (stupidBot.memeChecker(input))
		{
			if (Float.compare(stupidBot.getMemeLevel(), 10) < 0)
			{
				if (stupidBot.getLastProbe() == 0)
				{
					toAdd += .5;
				}
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() + toAdd);
				toAdd = 1;
			}

			checkedInput += "You like memes :)\n";

			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getMemeLevel(), 0) > 0)
			{
				if (stupidBot.getLastProbe() == 1)
				{
					toSub += 1;
				}
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() - toSub);
				toSub = .5f;
			}
		}
		if (stupidBot.contentChecker(input))
		{
			checkedInput += "You know my secret topic!\n";
			understoodContent = true;
		}
		if (stupidBot.keyboardMashChecker(input))
		{
			checkedInput += "Mashing is wrong \n";
			understoodContent = true;
		}
		if (stupidBot.politicalTopicChecker(input))
		{
			if (Float.compare(stupidBot.getPoliticalLevel(), 10) < 0)
			{
				if (stupidBot.getLastProbe() == 1)
				{
					toAdd += .5;
				}
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() + toAdd);
				toAdd = 1;
			}
			checkedInput += "I hate politics \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getPoliticalLevel(), 0) > 0)
			{
				if (stupidBot.getLastProbe() == 1)
				{
					toSub += 1;
				}
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() - toSub);
				toSub = 0.5f;
			}
		}
		if (stupidBot.inputHTMLChecker(input))
		{
			if (Float.compare(stupidBot.getTechLevel(), 10) < 0)
			{
				toAdd = 0.75f;
				if (stupidBot.getLastProbe() == 2)
				{
					toAdd += .75f;
				}

				stupidBot.setTechLevel(stupidBot.getTechLevel() + toAdd);
				toAdd = 1;
			}
			checkedInput += "You know about the HTMLS!!!! \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{
				if (stupidBot.getLastProbe() == 2)
				{
					toSub += 1;
				}
				stupidBot.setTechLevel(stupidBot.getTechLevel() - toSub);
				toSub = 0.5f;
			}
		}
		if (stupidBot.twitterChecker(input))
		{
			if (Float.compare(stupidBot.getTechLevel(), 10) < 0)
			{

				toAdd = 0.75f;
				if (stupidBot.getLastProbe() == 2)
				{
					toAdd += .75f;
				}

				stupidBot.setTechLevel(stupidBot.getTechLevel() + toAdd);
				toAdd = 1;
			}
			checkedInput += "Was that a tweet ?!?!?!?!?!?!?!?!?!? \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{
				if (stupidBot.getLastProbe() == 2)
				{
					toSub += 1;
				}
				stupidBot.setTechLevel(stupidBot.getTechLevel() - toSub);
				toSub = 0.5f;
			}
		}
		if (understoodContent)
		{
			checkedInput = checkedInput.substring(unknown.length() + input.length(), checkedInput.length());
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{

		return baseFrame;

	}

	public String getGreeting()
	{
		int greeting = rand.nextInt(stupidBot.getGreetings().size());
		return stupidBot.getGreetings().get(greeting);
	}

	private String randomUnknownGenerator()
	{
		int rand = (int) (Math.random() * 7);
		String topic = "";
		switch (rand)
		{
		case 0:
			topic = "What is this strange ";
			break;
		case 1:
			topic = "Could you elaborate on ";
			break;
		case 2:
			topic = "i am confused by the concept of ";
			break;
		case 3:
			topic = "Cannont compute ";
			break;
		case 4:
			topic = "error 404 chat response not found for ";
			break;
		case 5:
			topic = "Why do you assume I know about ";
			break;
		case 6:
			topic = "I like cats more than I like ";
			break;
		default:
			topic = "fatal error your computer is now a bomb run!!!!";
			break;

		}
		return topic;
	}

}
