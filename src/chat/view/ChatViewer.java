package chat.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ChatViewer
{
	private String windowMessage;
	private ImageIcon chatIcon;

	public ChatViewer()
	{
		windowMessage = "";
		chatIcon = new ImageIcon(getClass().getResource("images/chatbot.png"));
	}

	/**
	 * Creates an input popup to collect response from user as a string.
	 * 
	 * @param question
	 *            The question to ask the user.
	 * @return The users response.
	 */
	public String collectResponse(String question)
	{
		String response = "";
		response = JOptionPane.showInputDialog(null, question);
		return response;

	}

	/**
	 * Make a popup window to display the supplied message.
	 * 
	 * @param message
	 *            The message to be placed in the popup.
	 */
	public void diplayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Creates a popup to ask a yes/no/cancel type question.
	 * 
	 * @param question
	 *            The question being asked to the user.
	 * @return The constant value from JOptionPane for yes/no/cancel.
	 */
	public int collectuserOption(String question)
	{
		int response = 0;
		response = JOptionPane.showConfirmDialog(null, question);
		return response;
	}

	/**
	 * Creates an input popup to collect response from user as a string, and
	 * also displays a pictue.
	 * 
	 * @param question
	 *            The question to ask the user.
	 * @paramk fileName The file path to the picture
	 * @return The users response.
	 */
	public String collectUserTextWithPics(String question, String fileName)
	{
		String userInput = "";
		if (fileName != null)
		{
			chatIcon = new ImageIcon(getClass().getResource(fileName));
		}
		userInput = JOptionPane.showInputDialog(null, question, windowMessage, JOptionPane.DEFAULT_OPTION, chatIcon, null, "Type here please!")
				.toString();

		return userInput;

	}

	/**
	 * Make a popup window to display the supplied message And pictue.
	 * 
	 * @param message
	 *            The message to be placed in the popup.
	 * @param fileName
	 *            the file location for the picture
	 */
	public void displayUserTextWithPics(String message, String fileName)
	{
		if (fileName != null)
		{
			chatIcon = new ImageIcon(getClass().getResource(fileName));
		}
		JOptionPane.showMessageDialog(null, message, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon);

	}

	public int collectuserOptionWithPicture(String question, String fileName)
	{
		int response = 0;
		if (fileName != null)
		{
			chatIcon = new ImageIcon(getClass().getResource(fileName));
		}
		response = JOptionPane.showConfirmDialog(null, question, windowMessage, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, chatIcon);
		return response;
	}

}
