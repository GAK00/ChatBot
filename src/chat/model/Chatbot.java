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
		if(currentInput != null &&!currentInput.equals("")){hasLength = true;}
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
		if(currentInput.contains(content.substring(0, content.length()))){contentContained = true;}
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
		if(currentInput != null && politicalTopicsList.contains(currentInput)){isPolitical = true;}
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
		if(currentInput != null && memesList.contains(currentInput)){isMeme = true;}
		
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
	 * * Getter method for the politicalTopicList object. * @return The
	 * reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicsList;
	}

	/**
	 * * Updates the content area for this Chatbot instance. * @param content
	 * The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
/**
 * Test a Input to see if it is just mashing
 * @param Input your input
 * @return if input is mashing or not
 */
	public boolean keyboardMashChecker(String IntialInput)
	{
		boolean isMashing = true;
	boolean isAcronym = false;
	String[] strings = IntialInput.split(" ");
	for(String Input : strings){
	if(Input.contains(".")){
		for(int counter = 0; counter<Input.length();counter++){
			if(Character.isUpperCase(Input.charAt(counter))){
				isAcronym = true;
			}
		}}
	boolean isWord = false;
	if(Input.contains("a")||Input.contains("A")||Input.contains("e")||Input.contains("E")||Input.contains("i")||Input.contains("I")||Input.contains("o")||Input.contains("O")||Input.contains("u")||Input.contains("U"))
	{
		isWord = true;
	}
	if(isAcronym||isWord){
		isMashing = false;
	}
	else{
		return true;
	}}
		return isMashing;
	}
/**
 * checks if a string is a valid HTML tag
 * @param Input input string 
 * @return if the string is a valid HTML tag
 */
	public boolean inputHTMLChecker(String Input)
	{
		boolean valid = false;
		boolean contentInBrakets = false;
		if(Input.startsWith("<")&&Input.endsWith(">")){
			int testCond1 = Input.split("<").length;
			int testCond2= Input.split(">").length +1;
			
			if(testCond1 == testCond2){
				
				for(int pos = 0; pos<Input.length();pos++){
					if(Input.charAt(pos) == '<'){
						if(Input.charAt(pos+1)=='>'){valid = false;
						break;}
						else{
							pos++;
							
							while(Input.charAt(pos)!='>'){
								if(contentInBrakets){pos++;}
								else{if(Input.charAt(pos)!= ' '){contentInBrakets = true;
								pos++;
								}
								else{pos++;}}
							}
						}
					}
				}
				
			}
		}
		if(contentInBrakets){
			if(Input.contains("HREF")){
				if(Input.split("HREF").length==Input.split(".html").length){}
				else{contentInBrakets = false;}
			}
		}
		if(contentInBrakets){
			valid = true;
		}
		return valid;
	}
/**
 * Test if a string is a valid tweet
 * @param Input input string
 * @return if the string is a valid tweet
 */
	public boolean twitterChecker(String Input)
	{
		boolean ValidTweet = false;
		if(Input.startsWith("@")||Input.startsWith("#")){
			ValidTweet = true;
		}
		return ValidTweet;
	}
/**
 * searches for quit and returns true if quit is the input
 * @param Input the input
 * @return wheter or not it was the word quit
 */
	public boolean quitChecker(String Input)
	{
		boolean quit = false;
		if(Input.equalsIgnoreCase("quit")){quit = true;}
		return quit;
	}
	
	

}