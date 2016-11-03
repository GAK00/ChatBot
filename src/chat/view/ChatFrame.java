package chat.view;
import java.awt.Dimension;

import javax.swing.JFrame;

import chat.controller.ChatController;
public class ChatFrame extends JFrame
{
	
	private ChatController controller;
	private ChatPanel panel;
	public ChatFrame(ChatController controller){
		super();
		this.controller = controller;
		this.panel = new ChatPanel(controller);
		setup();
	}
	private void setup()
	{
		this.setContentPane(panel);
		this.setTitle("Chatbot");
		this.setSize(new Dimension(500,500));
		this.setVisible(true);
	}

}
