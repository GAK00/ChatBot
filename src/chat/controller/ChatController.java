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
	private boolean yesNo;

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
		String response = "";
		response += "You said:\n " + input;
		if (yesNo)
		{
			yesNo = false;
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

		} else
		{
			response += "\n"+useChatbotCheckers(input)+ "\n";
		}
		response += "\n\n"+askQuestions(input)+"\n\n\n\n";
		return response;
	}

	private String askQuestions(String input)
	{
		String question = "";

		if (Float.compare(stupidBot.getMemeLevel(), 10) >= 0 || Float.compare(stupidBot.getPoliticalLevel(), 10) >= 0
				|| Float.compare(stupidBot.getTechLevel(), 10) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(stupidBot.getMemeLevel(), 10) >= 0)
			{
				baseFrame.getPanel().setPicture("images/doYouLikeMemes.png");
				if (qToAsk == 1)
				{
					int meme = rand.nextInt(stupidBot.getMemesList().size());
					question = "Do you like the " + stupidBot.getMemesList().get(meme) + " meme";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "Wow you really like memes don't you";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "What do you like other than memes";
				}
			} else if (Float.compare(stupidBot.getPoliticalLevel(), 10) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Politics.png");
				if (qToAsk == 1)
				{
					int politic = rand.nextInt(stupidBot.getPoliticalTopicList().size());
					question = "Do you like the " + stupidBot.getMemesList().get(politic) + " Politcal topic";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "Wow you really like Politics don't you";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "What do you like other than Politics";
				}
			} else if (Float.compare(stupidBot.getTechLevel(), 10) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Internet.png");
				if (qToAsk == 1)
				{
					question = "Wow you really like computers don't you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "How much time do you spend on computer";
				} else if (qToAsk == 3)
				{
					question = "Computers Love you too";
				}
			}
		} else if (Float.compare(stupidBot.getMemeLevel(), 5) >= 0 || Float.compare(stupidBot.getPoliticalLevel(), 5) >= 0
				|| Float.compare(stupidBot.getTechLevel(), 5) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(stupidBot.getMemeLevel(), 5) >= 0)
			{
				baseFrame.getPanel().setPicture("images/doYouLikeMemes.png");
				if (qToAsk == 1)
				{
					question = "Stop talking about Memes!!";
				} else if (qToAsk == 2)
				{
					question = "Wow you talk know a lot of memes, What other do you know";
				} else if (qToAsk == 3)
				{
					question = "Are you a meme lord";
				}

			} else if (Float.compare(stupidBot.getPoliticalLevel(), 5) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Politics.png");
				if (qToAsk == 1)
				{
					question = "Your are really poltically involved aren't you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "What else do you feel about politics";
				} else if (qToAsk == 3)
				{
					question = "Do you prefer Trump or Hillary";
				}
			} else if (Float.compare(stupidBot.getTechLevel(), 5) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Internet.png");
				if (qToAsk == 1)
				{
					question = "You spend a fair amount of time on computers dont you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "What else do you care about relitive to computers";
				} else if (qToAsk == 3)
				{
					question = "Do you tweet regularly";
					yesNo = true;
				}

			}

		}
		else if(Float.compare(stupidBot.getMemeLevel(), 3) >= 0 || Float.compare(stupidBot.getPoliticalLevel(), 3) >= 0
				|| Float.compare(stupidBot.getTechLevel(), 3) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(stupidBot.getMemeLevel(), 3) >= 0)
			{
				baseFrame.getPanel().setPicture("images/doYouLikeMemes.png");
				if (qToAsk == 1)
				{
					question = "Tell me more about memes";
				} else if (qToAsk == 2)
				{
					question = "Do you dabble in memes";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "do you Meme often";
						yesNo = true;
				}

			} else if (Float.compare(stupidBot.getPoliticalLevel(), 3) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Politics.png");
				if (qToAsk == 1)
				{
					question = "You dabble in politics don't you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "tell me more about politics";
				} else if (qToAsk == 3)
				{
					question = "Do you think politics are dumb";
					yesNo = true;
				}
			} else if (Float.compare(stupidBot.getTechLevel(), 3) >= 0)
			{
				baseFrame.getPanel().setPicture("images/Internet.png");
				if (qToAsk == 1)
				{
					question = "You know how to use computers, right";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "how often do you use computers";
				} else if (qToAsk == 3)
				{
					question = "Do you have a twitter acount";
					yesNo = true;
				}

			}
			
		}
		else{
			int qToAsk = rand.nextInt(3) + 1;
			if(qToAsk == 1){
				baseFrame.getPanel().setPicture("images/doYouLikeMemes.png");
				question = "Tell me about memes";
			}
			else if(qToAsk == 2){
				baseFrame.getPanel().setPicture("images/Politics.png");
				question = "tell me about politics";
			}
			else if(qToAsk == 3){
				baseFrame.getPanel().setPicture("images/Internet.png");
				question = "tell me more about tech";
			}
			
		}
		return question;
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
			if (Float.compare(stupidBot.getMemeLevel(), 10) < 0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel() + 1);
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
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() + 1);
			}
			checkedInput += "\n I hate politics \n";
			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getPoliticalLevel(), 0) > 0)
			{
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel() - 0.5f);
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
			checkedInput = checkedInput.substring(35 + input.length(), checkedInput.length());
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}

}
