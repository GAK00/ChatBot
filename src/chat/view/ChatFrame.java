package chat.view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import chat.controller.ChatController;

public class ChatFrame extends JFrame
{

	private ChatController controller;
	private ChatPanel panel;
	private int windowWidth;
	private int windowHeight;
	private ChatViewer display;

	public ChatFrame(ChatController controller)
	{
		super();
		display = new ChatViewer();
		this.controller = controller;
		this.panel = new ChatPanel(controller);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setup();
		setupFrameListener();
	}

	/**
	 * sets up the frame
	 */
	private void setup()
	{

		this.setTitle("Chatbot");
		GraphicsDevice Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = Screen.getDisplayMode().getWidth();
		int screenHeight = Screen.getDisplayMode().getHeight();
		windowWidth = ((screenWidth) - ((screenWidth) / 4));
		windowHeight = ((screenHeight) - ((screenHeight) / 4));
		this.setSize(windowWidth, windowHeight);
		int posX = (screenWidth - windowWidth) / 2;
		int posY = (screenHeight - windowHeight) / 2;
		this.setLocation(posX, posY);
		panel.setWidth(windowWidth);
		this.setContentPane(panel);
		this.setVisible(true);

	}

	/**
	 * sets up all listeneres
	 */
	public void setupFrameListener()
	{
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{

				closing();
			}

		});
		this.addComponentListener(

		new ComponentListener()
		{

			public void componentResized(ComponentEvent e)
			{
				if (panel.getMyKnownWith() != controller.getBaseFrame().getWidth())
				{
					panel.setWidth(controller.getBaseFrame().getWidth());
					panel.setPicture(panel.getPicture());
				}

			}

			public void componentMoved(ComponentEvent e)
			{

			}

			public void componentShown(ComponentEvent e)
			{
				panel.setVisible(true);
				controller.getBaseFrame().setVisible(true);

			}

			public void componentHidden(ComponentEvent e)
			{
				panel.setVisible(false);
				controller.getBaseFrame().setVisible(false);

			}
		});

	}

	public ChatPanel getPanel()
	{
		return panel;
	}

	/**
	 * runs when window is closing handels closing
	 * 
	 */
	private void closing()
	{
		if (display.collectuserOptionWithPicture("are you sure you wish to quit?", "images/chatbot.png") == 0)
		{
			display.diplayMessage("Thanks for chatting!");
			System.exit(0);
		}
	}
}
