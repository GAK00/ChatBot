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
			if(stupidBot.getLastProbe()==0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel()+1.5f);
			}
			if(stupidBot.getLastProbe()==1)
			{
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel()+1.5f);
			}
			if(stupidBot.getLastProbe()==2)
			{
				stupidBot.setTechLevel(stupidBot.getTechLevel()+1.5f);
			}
			response += "\nCool I am gald you agree\n";

		} else if (input.toLowerCase().contains("no"))
		{
			if(stupidBot.getLastProbe()==0)
			{
				stupidBot.setMemeLevel(stupidBot.getMemeLevel()-1.5f);
			}
			if(stupidBot.getLastProbe()==1)
			{
				stupidBot.setPoliticalLevel(stupidBot.getPoliticalLevel()-1.5f);
			}
			if(stupidBot.getLastProbe()==2)
			{
				stupidBot.setTechLevel(stupidBot.getTechLevel()-1.5f);
			}
			response += "\nOh well it is ok you don't agree\n";

		} else
		{
			response += "\nyour answer does not make sense\n";
		}
		return response;
	}

	private String generateResponse(String input){
		String response = "";
		response += "You said: " + input;
		if (stupidBot.retriveYesNo())
		{
			yesNoCheckers(input);

		} else
		{
			response += "\n"+" ChatBot Said: "+useChatbotCheckers(input)+ "\n";
		}
		response += "\n\n"+stupidBot.generateQuestion(input)+"\n\n\n\n";
		return response;
	}

	private String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about " + input;
		boolean understoodContent = false;
		float toAdd = 1;
		float toSub = .5f;
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		if(stupidBot.isGreeting(input)){
			int greeting = rand.nextInt(stupidBot.getGreetings().size());
			checkedInput +=  stupidBot.getGreetings().get(greeting) +"\n";
			understoodContent = true;
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

			checkedInput += "You like memes :)\n";

			understoodContent = true;
		} else
		{
			if (Float.compare(stupidBot.getMemeLevel(), 0) > 0)
			{
				if(stupidBot.getLastProbe()==1)
				{
					toSub+=1;
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
				if(stupidBot.getLastProbe()==1)
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
				if(stupidBot.getLastProbe()==1)
				{
					toSub+=1;
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
				if(stupidBot.getLastProbe()==2)
				{
					toAdd+=.75f;
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
				if(stupidBot.getLastProbe()==2)
				{
					toSub+=1;
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
				if(stupidBot.getLastProbe()==2)
				{
					toAdd+=.75f;
				}

				stupidBot.setTechLevel(stupidBot.getTechLevel() + toAdd);
				toAdd = 1;
			}
			checkedInput += "Was that a tweet ?!?!?!?!?!?!?!?!?!? \n";
		} else
		{
			if (Float.compare(stupidBot.getTechLevel(), 0) > 0)
			{
				if(stupidBot.getLastProbe()==2)
				{
					toSub+=1;
				}
				stupidBot.setTechLevel(stupidBot.getTechLevel() - toSub);
				toSub = 0.5f;
			}
		}
		if (understoodContent)
		{
			checkedInput = checkedInput.substring(35+input.length(), checkedInput.length());
		}

		return checkedInput;
	}

	public ChatFrame getBaseFrame()
	{
		
		return baseFrame;
		
	}
	public String getGreeting()
	{
		System.out.println(stupidBot.getGreetings().size());
		int greeting = rand.nextInt(stupidBot.getGreetings().size());
		return stupidBot.getGreetings().get(greeting);
	}

}
