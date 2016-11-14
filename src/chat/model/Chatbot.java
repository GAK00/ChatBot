package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * @author Cody
 * Henrichsen * @version 1.0 10/14/15
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicsList;
	private String userName;
	private String content;
	private float politicalLevel;
	private float memeLevel;
	private float techLevel;

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.userName = userName;
		memesList = new ArrayList<String>();
		politicalTopicsList = new ArrayList<String>();
		buildMemesList();
		buildPoliticalTopicsList();
		content = "spicy boi";
		politicalLevel = 0;
		memeLevel = 0;
		techLevel = 0;
	}

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

	}

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

	}

	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput * @return
	 * A true or false based on the length of the supplied String.
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
		for(String topic : politicalTopicsList){
			if(currentInput.contains(topic)){
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
		for(String meme: memesList){
			if(currentInput.toLowerCase().contains(meme.toLowerCase())){
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
		System.out.println(content);
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

	public boolean keyboardMashChecker(String input)
	{
		boolean isMashing = true;
		String[] strings = input.split(" ");
		boolean isAcronym = false;
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
			if (Input.contains("a") || Input.contains("A") || Input.contains("e") || Input.contains("E") || Input.contains("i")
					|| Input.contains("I") || Input.contains("o") || Input.contains("O") || Input.contains("u") || Input.contains("U"))
			{
				isWord = true;
			}
			if (isAcronym || isWord)
			{
				isMashing = false;
			} else
			{
				return true;
			}
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
	
	public int getEmotion(){
		int emotionState = 0;
		return emotionState;
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
		System.out.println(this.content);

	}
}