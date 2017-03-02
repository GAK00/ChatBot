package chat.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * @author Cody
 * Henrichsen * @version 1.0 10/14/15
 */
public class Chatbot
{
	private ArrayList<String> baseMemesList;
	private ArrayList<String> basePoliticalTopicsList;
	private ArrayList<String> baseGreetingsList;
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicsList;
	private ArrayList<String> greetingsList;
	private String userName;
	private String content;
	private float politicalLevel;
	private float memeLevel;
	private float techLevel;
	private boolean yesNo;
	private Random rand;
	private int currentTopicProbe;
	private FileController fileHandler;
	private boolean safeToSave;

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		fileHandler = new FileController();
		safeToSave = fileHandler.makeDirectory("ChatVocab");
		if (!safeToSave)
		{
			System.out.println("Error creating directory");
		}
		this.userName = userName;
		memesList = new ArrayList<String>();
		politicalTopicsList = new ArrayList<String>();
		greetingsList = new ArrayList<String>();
		baseMemesList = new ArrayList<String>();
		basePoliticalTopicsList = new ArrayList<String>();
		baseGreetingsList = new ArrayList<String>();
		buildMemesList();
		buildPoliticalTopicsList();
		buildGreetingsList();
		content = "spicy boi";
		politicalLevel = 0;
		memeLevel = 0;
		techLevel = 0;
		yesNo = false;
		rand = new Random();
		currentTopicProbe = -1;
		setLevels();

	}

	public Chatbot(String userName, FileController fileHandler, boolean safeToSave)
	{
		this.fileHandler = fileHandler;
		this.safeToSave = safeToSave;
		this.userName = userName;
		memesList = new ArrayList<String>();
		politicalTopicsList = new ArrayList<String>();
		greetingsList = new ArrayList<String>();
		baseMemesList = new ArrayList<String>();
		basePoliticalTopicsList = new ArrayList<String>();
		baseGreetingsList = new ArrayList<String>();
		buildMemesList();
		buildPoliticalTopicsList();
		buildGreetingsList();
		content = "spicy boi";
		politicalLevel = 0;
		memeLevel = 0;
		techLevel = 0;
		yesNo = false;
		rand = new Random();
		currentTopicProbe = -1;
		setLevels();

	}

	/**
	 * builds the greetings list
	 */
	private void buildGreetingsList()
	{
		greetingsList.add("hello");
		greetingsList.add("hi");
		greetingsList.add("sup");
		greetingsList.add("whats up");
		greetingsList.add("hows it going");
		ArrayList<String> greetings = fileHandler.getData("greeting.txt");
		if (greetings != null)
		{
			for (String greeting : greetings)
			{
				greetingsList.add(greeting);
			}
		}
		baseGreetingsList.add("hello");
		baseGreetingsList.add("hi");
		baseGreetingsList.add("sup");
		baseGreetingsList.add("whats up");
		baseGreetingsList.add("hows it going");
		if (greetings != null)
		{
			for (String greeting : greetings)
			{
				baseGreetingsList.add(greeting);
			}
		}

	}

	/**
	 * builds the memesList
	 */
	private void buildMemesList()
	{
		memesList.add("harambe");
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("catz");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("john cena");
		memesList.add("pepe");
		memesList.add("rare pepe");
		memesList.add("shreckt");
		memesList.add("dank");
		memesList.add("get a job");
		memesList.add("sanic");
		memesList.add("420");
		memesList.add("puns");
		memesList.add("bad puns");
		memesList.add("dad jokes");
		ArrayList<String> memes = fileHandler.getData("memes.txt");
		if (memes != null)
		{
			for (String meme : memes)
			{
				memesList.add(meme);
			}
		}
		baseMemesList.add("harambe");
		baseMemesList.add("doge");
		baseMemesList.add("cute animals");
		baseMemesList.add("catz");
		baseMemesList.add("grumpy cat");
		baseMemesList.add("dat boi");
		baseMemesList.add("willy wonka");
		baseMemesList.add("john cena");
		baseMemesList.add("pepe");
		baseMemesList.add("rare pepe");
		baseMemesList.add("shreckt");
		baseMemesList.add("dank");
		baseMemesList.add("get a job");
		baseMemesList.add("sanic");
		baseMemesList.add("420");
		baseMemesList.add("puns");
		baseMemesList.add("bad puns");
		baseMemesList.add("dad jokes");
		if (memes != null)
		{
			for (String meme : memes)
			{
				baseMemesList.add(meme);
			}
		}

	}

	/**
	 * builds the Poltical list
	 */
	private void buildPoliticalTopicsList()
	{
		politicalTopicsList.add("Democrat");
		politicalTopicsList.add("Republican");
		politicalTopicsList.add("11/8/16");
		politicalTopicsList.add("conservative");
		politicalTopicsList.add("Clinton");
		politicalTopicsList.add("Trump");
		politicalTopicsList.add("Kaine");
		politicalTopicsList.add("Pence");
		politicalTopicsList.add("Stein");
		politicalTopicsList.add("Johnson");
		politicalTopicsList.add("liberal");
		politicalTopicsList.add("Congress");
		politicalTopicsList.add("President");
		politicalTopicsList.add("Judge");
		politicalTopicsList.add("Court");
		politicalTopicsList.add("Hillary");
		politicalTopicsList.add("War");
		politicalTopicsList.add("Economy");
		politicalTopicsList.add("election");
		ArrayList<String> politics = fileHandler.getData("politics.txt");
		if (politics != null)
		{
			for (String politic : politics)
			{
				politicalTopicsList.add(politic);
			}
		}
		basePoliticalTopicsList.add("Democrat");
		basePoliticalTopicsList.add("Republican");
		basePoliticalTopicsList.add("11/8/16");
		basePoliticalTopicsList.add("conservative");
		basePoliticalTopicsList.add("Clinton");
		basePoliticalTopicsList.add("Trump");
		basePoliticalTopicsList.add("Kaine");
		basePoliticalTopicsList.add("Pence");
		basePoliticalTopicsList.add("Stein");
		basePoliticalTopicsList.add("Johnson");
		basePoliticalTopicsList.add("liberal");
		basePoliticalTopicsList.add("Congress");
		basePoliticalTopicsList.add("President");
		basePoliticalTopicsList.add("Judge");
		basePoliticalTopicsList.add("Court");
		basePoliticalTopicsList.add("Hillary");
		basePoliticalTopicsList.add("War");
		basePoliticalTopicsList.add("Economy");
		basePoliticalTopicsList.add("election");
		if (politics != null)
		{
			for (String politic : politics)
			{
				basePoliticalTopicsList.add(politic);
			}
		}
	}

	/**
	 * Udates chatbots vocab 0 = meme 1 = politic 2 = greeting
	 * 
	 * @param updateCatogory
	 *            list to update
	 * @param newWord
	 *            new vocab
	 */
	public void updateVocabulary(int updateCatogory, String newWord)
	{
		switch (updateCatogory)
		{
		case 0:
			memesList.add(newWord);
			break;
		case 1:
			politicalTopicsList.add(newWord);
			break;
		case 2:
			greetingsList.add(newWord);
			break;
		default:
			System.out.println("error");
		}
	}

	public void saveForShutdown()
	{
		if (safeToSave)
		{
			boolean firstComplete = false;
			for (String meme : memesList)
			{
				if (!baseMemesList.contains(meme))
				{
					if (firstComplete)
					{
						fileHandler.createFile(("\n" + meme).getBytes(), "memes.txt");
					} else
					{
						fileHandler.createFile((meme).getBytes(), "memes.txt");
						firstComplete = true;
					}
				}
			}
			firstComplete = false;
			for (String politics : politicalTopicsList)
			{
				if (!basePoliticalTopicsList.contains(politics))
				{
					if (firstComplete)
					{
						fileHandler.createFile(("\n" + politics).getBytes(), "politics.txt");
					} else
					{
						fileHandler.createFile((politics).getBytes(), "politics.txt");
						firstComplete = true;
					}
				}
			}
			firstComplete = false;
			for (String greeting : greetingsList)
			{
				if (!baseGreetingsList.contains(greeting))
				{
					if (firstComplete)
					{
						fileHandler.createFile(("\n" + greeting).getBytes(), "greeting.txt");
					} else
					{
						fileHandler.createFile((greeting).getBytes(), "greeting.txt");
						firstComplete = true;
					}
				}
			}
		}

		fileHandler.setFileData((Float.toString(memeLevel) + "\n" + Float.toString(politicalLevel) + "\n" + Float.toString(techLevel)).getBytes(),
				"TopicLevels.txt");
	}

	private void setLevels()
	{
		if (fileHandler.getData("TopicLevels.txt") != null)
		{

			memeLevel = Float.parseFloat(fileHandler.getData("TopicLevels.txt").get(0));
			politicalLevel = Float.parseFloat(fileHandler.getData("TopicLevels.txt").get(1));
			techLevel = Float.parseFloat(fileHandler.getData("TopicLevels.txt").get(2));

		}
	}

	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput
	 * * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		if (currentInput != null && !currentInput.equals(""))
		{
			hasLength = true;
		}
		return hasLength;
	}

	/**
	 * * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether it
	 *            matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean contentContained = false;

		if (currentInput.toLowerCase().contains(content.toLowerCase()))
		{
			contentContained = true;
		}
		return contentContained;
	}

	/**
	 * * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean isPolitical = false;
		for (String topic : politicalTopicsList)
		{
			if (currentInput.contains(topic))
			{
				isPolitical = true;
			}
		}

		return isPolitical;
	}

	/**
	 * * Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean isMeme = false;
		currentInput = currentInput.toLowerCase();
		for (String meme : memesList)
		{
			if (currentInput.toLowerCase().contains(meme.toLowerCase()))
			{
				isMeme = true;
			}
		}

		return isMeme;
	}

	/**
	 * * Returns the username of this Chatbot instance. * @return The username
	 * of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * * Returns the content area for this Chatbot instance. * @return The
	 * content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * * Getter method for the memesList object. * @return The reference to the
	 * meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * checks if input is mashing
	 * 
	 * @param input
	 *            input to check
	 * @return if the input is mashing
	 */
	public boolean keyboardMashChecker(String input)
	{
		boolean isMashing = true;
		String[] strings = input.split(" ");
		boolean isAcronym = false;
		int Passes = 0;
		int Fails = 0;
		for (String Input : strings)
		{
			if (Input.contains("."))
			{
				for (int counter = 0; counter < Input.length(); counter++)
				{
					if (Character.isUpperCase(Input.charAt(counter)))
					{
						isAcronym = true;
					}
				}
			}
			boolean isWord = false;
			if (Input.contains("a") || Input.contains("A") || Input.contains("e") || Input.contains("E") || Input.contains("i") || Input.contains("I")
					|| Input.contains("o") || Input.contains("O") || Input.contains("u") || Input.contains("U"))
			{
				isWord = true;
			} else if (Input.contains("y") || Input.contains("Y"))
			{
				isWord = true;
			}
			if (isAcronym && !(Input.length() >= 40) || isWord && !(Input.length() >= 40))
			{
				Passes++;
			} else
			{
				Fails++;
			}
		}
		if (Passes > Fails)
		{
			isMashing = false;
		}
		return isMashing;
	}

	/**
	 * checks if a string is a valid HTML tag
	 * 
	 * @param Input
	 *            input string
	 * @return if the string is a valid HTML tag
	 */
	public boolean inputHTMLChecker(String input)
	{
		boolean isHTML = false;
		if (input.startsWith("<") && input.endsWith(">"))
		{
			if (input.length() == 3 && input.contains("P"))
			{
				isHTML = true;
			}
			if (input.length() >= 2)
			{
				if (input.contains("</") && input.substring(1, 2).equalsIgnoreCase(input.substring(input.length() - 2, input.length() - 1)))
				{
					if (input.contains("HREF"))
					{
						if (input.contains("="))
						{
							isHTML = true;
						}
					} else
					{
						isHTML = true;
					}
				}
			}
		}
		return isHTML;
	}

	/**
	 * Test if a string is a valid tweet
	 * 
	 * @param Input
	 *            input string
	 * @return if the string is a valid tweet
	 */
	public boolean twitterChecker(String Input)
	{
		boolean ValidTweet = false;
		if (Input.startsWith("@") || Input.startsWith("#"))
		{
			ValidTweet = true;
		}
		return ValidTweet;
	}

	/**
	 * searches for quit and returns true if quit is the input
	 * 
	 * @param Input
	 *            the input
	 * @return wheter or not it was the word quit
	 */
	public boolean quitChecker(String Input)
	{
		boolean quit = false;
		if (Input.equalsIgnoreCase("quit"))
		{
			quit = true;
		}
		return quit;
	}

	public float getMemeLevel()
	{
		return memeLevel;
	}

	public float getPoliticalLevel()
	{
		return politicalLevel;
	}

	public float getTechLevel()
	{
		return techLevel;
	}

	public void setMemeLevel(float memeLevel)
	{
		this.memeLevel = memeLevel;
	}

	public void setPoliticalLevel(float politicalLevel)
	{
		this.politicalLevel = politicalLevel;
	}

	public void setTechLevel(float techLevel)
	{
		this.techLevel = techLevel;
	}

	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicsList;
	}

	public void setContent(String content)
	{
		this.content = content;

	}

	/**
	 * generates a queston based on chatbots current state
	 * 
	 * @return a new question
	 */
	public String generateQuestion()
	{
		String question = "";

		if (Float.compare(memeLevel, 10) >= 0 || Float.compare(politicalLevel, 10) >= 0 || Float.compare(techLevel, 10) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(memeLevel, 10) >= 0)
			{
				currentTopicProbe = 0;
				if (qToAsk == 1)
				{
					int meme = rand.nextInt(memesList.size());
					question = "Do you like the " + memesList.get(meme) + " meme";

					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "Wow you really like memes don't you";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "What do you like other than memes";
				}
			} else if (Float.compare(politicalLevel, 10) >= 0)
			{
				currentTopicProbe = 1;
				if (qToAsk == 1)
				{
					int politic = rand.nextInt(politicalTopicsList.size());
					question = "Do you like the " + politicalTopicsList.get(politic) + " Politcal topic";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "Wow you really like Politics don't you";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "What do you like other than Politics";
				}
			} else if (Float.compare(techLevel, 10) >= 0)
			{
				currentTopicProbe = 2;
				if (qToAsk == 1)
				{
					question = "Wow you really like computers don't you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "How much time do you spend on computer";
				} else if (qToAsk == 3)
				{
					question = "Do you use twitter to much";
					yesNo = true;
				}
			}
		} else if (Float.compare(memeLevel, 5) >= 0 || Float.compare(politicalLevel, 5) >= 0 || Float.compare(techLevel, 5) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(memeLevel, 5) >= 0)
			{
				currentTopicProbe = 0;
				if (qToAsk == 1)
				{
					question = "Memes are great do you agree";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "Wow you talk know a lot of memes, What other do you know";
				} else if (qToAsk == 3)
				{
					question = "Are you a meme lord";
					yesNo = true;
				}

			} else if (Float.compare(politicalLevel, 5) >= 0)
			{
				currentTopicProbe = 1;
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
			} else if (Float.compare(techLevel, 5) >= 0)
			{
				currentTopicProbe = 2;
				if (qToAsk == 1)
				{
					question = "You spend a fair amount of time on computers don't you";
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

		} else if (Float.compare(memeLevel, 3) >= 0 || Float.compare(politicalLevel, 3) >= 0 || Float.compare(techLevel, 3) >= 0)
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (Float.compare(memeLevel, 3) >= 0)
			{

				currentTopicProbe = 0;
				if (qToAsk == 1)
				{
					question = "Tell me more about memes";
				} else if (qToAsk == 2)
				{
					question = "Do you dabble in memes";
					yesNo = true;
				} else if (qToAsk == 3)
				{
					question = "do you meme often";
					yesNo = true;
				}

			} else if (Float.compare(politicalLevel, 3) >= 0)
			{
				currentTopicProbe = 1;
				if (qToAsk == 1)
				{
					question = "You dabble in politics don't you";
					yesNo = true;
				} else if (qToAsk == 2)
				{
					question = "tell me more about politics";
				} else if (qToAsk == 3)
				{
					question = "Do you think politics are cool";
					yesNo = true;
				}
			} else if (Float.compare(techLevel, 3) >= 0)
			{
				currentTopicProbe = 2;
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
		} else
		{
			int qToAsk = rand.nextInt(3) + 1;
			if (qToAsk == 1)
			{
				currentTopicProbe = 0;
				question = "Tell me about memes";
			} else if (qToAsk == 2)
			{
				currentTopicProbe = 1;
				question = "tell me about politics";
			} else if (qToAsk == 3)
			{
				currentTopicProbe = 2;
				question = "tell me  about tech";
			}

		}
		return question;
	}

	public boolean retriveYesNo()
	{
		boolean toggle = yesNo;
		yesNo = false;
		return toggle;
	}

	/**
	 * 0=meme 1=politics 2=tech
	 * 
	 * @return last topic chatbot asked a question about
	 */
	public int getLastProbe()
	{
		return currentTopicProbe;
	}

	/**
	 * Checks if somthing is a greeting
	 * 
	 * @param input
	 *            String to check
	 * @return if the input is a greeting
	 */
	public boolean isGreeting(String input)
	{
		boolean isGreeting = false;
		for (String currentGreet : greetingsList)
		{
			if (input.toLowerCase().contains(" " + currentGreet.toLowerCase()) || input.toLowerCase().contains(currentGreet.toLowerCase() + " ")
					|| input.toLowerCase().equals(currentGreet.toLowerCase()))
			{
				isGreeting = true;
			}
		}
		return isGreeting;

	}

	public ArrayList<String> getGreetings()
	{
		return greetingsList;
	}
}