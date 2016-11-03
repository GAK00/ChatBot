package chat.view;

import javax.swing.*;
import javax.swing.SpringLayout;

import chat.controller.ChatController;

public class ChatPanel extends JPanel
{
	private ChatController controller;
	private JButton submitTextButton;
	private SpringLayout layout;
	public ChatPanel(ChatController controller)
	{
		
		this.controller = controller;
		this.layout = new SpringLayout();
		this.submitTextButton = new JButton("Enter");
		layout.putConstraint(SpringLayout.SOUTH, submitTextButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submitTextButton, -78, SpringLayout.EAST, this);
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.add(submitTextButton);
	}
	private void setupLayout(){}
	private void setupListeners(){}

}
