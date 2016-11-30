package chat.controller;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import chat.model.Chatbot;
import chat.model.FileHandler;
import chat.view.ChatFrame;
import chat.view.ChatViewer;

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	private Random rand;
	private ChatFrame baseFrame;
	private boolean inquire = false;
	private String lastInput;
	private boolean inquireCycle = false;
	String lastQuestion;
	String addQuestion;
	private FileHandler fileHandler;
	private boolean yesNo;

	// 0 for memes
	// 1 for politics
	// 2 for tech

	public ChatController()
	{
		yesNo = false;
		fileHandler = new FileHandler();
		boolean safeToSave = fileHandler.makeDirectory("ChatData");
		if (!safeToSave)
		{
			System.out.println("Error creating directory");
		}
		stupidBot = new Chatbot("wall-e", fileHandler, safeToSave);
		display = new ChatViewer();
		rand = new Random();
		

		lastQuestion = "";
		addQuestion = "";
		lastInput = "";


	}

	/**
	 * Starts the Program
	 */
	public void start()
	{
		if (!fileHandler.getIsLocked())
		{
			baseFrame = new ChatFrame(this);
			try
			{
				baseFrame.getPanel().setBackground(getSavedColor());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				baseFrame.getPanel().setConversation(getSavedConversation());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			fileHandler.lock();
			baseFrame.getPanel().setPicture("images/chatbot.png");
		} else
		{
			display.displayUserTextWithPics("you can only have one chatBot open in this memory space at one time", "images/chatbot.png");
		}

	}

	public Chatbot getChatbot()
	{

		return stupidBot;
	}

	/**
	 * runs the chat bot
	 * 
	 * @param input
	 *            whatever the user inputs
	 * @return chatbots conversation
	 */
	public String Chat(String input)
	{

		useChatbotCheckers(input);
		if (yesNo)
		{
			inquire = false;
		}
		String response = "";
		String question = stupidBot.generateQuestion();

		if (inquire && !inquireCycle)
		{
			inquireCycle = true;
			inquire = false;
			question = ("is " + input + " a meme, politics, a greeting, or none of the above");
		} else
		{
			inquireCycle = false;
		}
		response = "ChatBot Asks: " + question + "?\n\n";
		lastQuestion = response;
		if (!addQuestion.equals(""))
		{
			response += addQuestion;
		}

		setPicture();
		response += generateResponse(input);
		if (!inquireCycle)
		{
			yesNo = stupidBot.retriveYesNo();
		} else
		{
			yesNo = false;
		}

		lastInput = input;

		return response;
	}

	/**
	 * formats the latest chatbot asks into a chatbot asked and put it in the
	 * correct place
	 * 
	 * @param conversation
	 *            String to format
	 * @return formated conversation
	 */
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

	/**
	 * sets Chatbots picture based on the content of the last asked question
	 */
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

	/**
	 * ran if the chatbot is in yesNo question mode
	 * 
	 * @param input
	 *            the users input
	 * @return chatbots response
	 */
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

	/**
	 * Generates chatbots reponse
	 * 
	 * @param input
	 *            users input
	 * @return the input and chatbots reponse
	 */
	private String generateResponse(String input)
	{
		String response = "";
		response += "You said: " + input;
		if (yesNo)
		{

			response += "\n" + "ChatBot Said: " + yesNoCheckers(input);
			return response;

		} else if (inquire)
		{
			String endAdder = Inquire(input);
			response += "\n" + "ChatBot Said: " + endAdder;
			inquire = false;
			return response;
		} else
		{
			response += "\n" + "ChatBot Said: " + useChatbotCheckers(input);
			return response;
		}

	}

	private String Inquire(String input)
	{
		String response = "I will rember that";
		if (input.toLowerCase().contains("greeting"))
		{
			stupidBot.updateVocabulary(2, lastInput);
		} else if (input.toLowerCase().contains("meme"))
		{
			stupidBot.updateVocabulary(0, lastInput);
		} else if (input.toLowerCase().contains("politic"))
		{
			stupidBot.updateVocabulary(1, lastInput);
		} else
		{
			response = "I do not know that catogorie";
		}
		return response;
	}

	/**
	 * runs all of chatbots checkers
	 * 
	 * @param input
	 *            the users input
	 * @return chatbots reponse
	 */
	private String useChatbotCheckers(String input)
	{
		String unknown = randomUnknownGenerator();
		String checkedInput = unknown + input;
		boolean understoodContent = false;
		float toAdd = 1;
		float toSub = .5f;
		if (stupidBot.quitChecker(input))
		{
			stupidBot.saveForShutdown();
			display.diplayMessage("Thanks for chatting!");
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
		} else
		{
			if (rand.nextInt() % 2 == 0)
			{
				inquire = true;
			}
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{

		return baseFrame;

	}

	/**
	 * gets a random greeting
	 * 
	 * @return a random greeting
	 */
	public String getGreeting()
	{
		int greeting = rand.nextInt(stupidBot.getGreetings().size());
		return stupidBot.getGreetings().get(greeting);
	}

	/**
	 * generates a response to an unknow input
	 * 
	 * @return a random reponse
	 */
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

	public void isQuitting()
	{
		stupidBot.saveForShutdown();
		fileHandler.unlock();
		fileHandler.setFileData(baseFrame.getPanel().getConversation().getBytes(), "Conversation.txt");

	}

	public void saveSettings(Color color)
	{
		String hex = Integer.toHexString(color.getRGB());

		fileHandler.setFileData(hex.getBytes(), "Settings.txt");

	}

	private Color getSavedColor()
	{
		Color savedColor = Color.white;
		if (fileHandler.getData("Settings.txt") != null)
		{
			String hex = ("#" + (fileHandler.getData("Settings.txt").get(0).substring(2)));
			savedColor = Color.decode(hex);
		}
		return savedColor;
	}

	private String getSavedConversation()
	{
		String Conversation = "";
		Conversation = fileHandler.getRawData("Conversation.txt");
		return Conversation;

	}

}