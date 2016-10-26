package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ChatViewer {
	private String windowMessage;
	private ImageIcon chatIcon;
	
		public ChatViewer(){
			windowMessage = "This message brought to you by the chatbot!";
			chatIcon = new ImageIcon(getClass().getResource("images/chatbot.png"));
		}
	/**
	 * Creates an input popup to collect response from user as a string.
	 * @param question The question to ask the user.
	 * @return The users response.
	 */
	public String collectResponse(String question)
	{
	String response = "";
	response = JOptionPane.showInputDialog(null,question);
	return response;
		
	}
	/**
	 * Make a popup window to display the supplied message.
	 * @param message The message to be placed in the popup.
	 */
	public void diplayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	/**
	 * Creates a popup to ask a yes/no/cancel type question.
	 * @param question The question being asked to the user.
	 * @return The constant value from JOptionPane for yes/no/cancel.
	 */
	public int collectuserOption(String question){
		int response = 0;
		response = JOptionPane.showConfirmDialog(null, question);
		return response;
	}

}
