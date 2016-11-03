package chat.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import chat.controller.ChatController;

public class ChatFrame extends JFrame
{

	private ChatController controller;
	private ChatPanel panel;
	private int windowWidth;
	private int windowHeight;

	public ChatFrame(ChatController controller)
	{
		super();
		this.controller = controller;
		this.panel = new ChatPanel(controller);
		setup();
	}

	private void setup()
	{
		
		this.setTitle("Chatbot");
		GraphicsDevice Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = Screen.getDisplayMode().getWidth();
		int screenHeight = Screen.getDisplayMode().getHeight();
		windowWidth = ((screenWidth)-((screenWidth)/4));
		windowHeight = ((screenHeight)-((screenHeight)/4));
		this.setSize(windowWidth,windowHeight);
		int posX = (screenWidth - windowWidth)/2;
		int posY = (screenHeight - windowHeight)/2;
		this.setLocation(posX, posY);
		panel.setWidth(windowWidth);
		this.setContentPane(panel);
		this.setVisible(true);
		
	}
	public ChatPanel getPanel(){
		return panel;
	}

}
