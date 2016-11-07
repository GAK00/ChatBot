package chat.view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chat.controller.ChatController;

public class ChatPanel extends JPanel
{
	private ChatController controller;
	private JButton submitTextButton;
	private JTextArea mainDialog;
	private JTextField input;
	private SpringLayout layout;
	private JLabel pictureDisplay;
	private int width;
	private String Conversation;

	public ChatPanel(ChatController controller)
	{

		this.controller = controller;
		this.layout = new SpringLayout();
		this.submitTextButton = new JButton("Enter");
		this.mainDialog = new JTextArea();
		layout.putConstraint(SpringLayout.SOUTH, mainDialog, -243, SpringLayout.SOUTH, this);
		this.input = new JTextField();
		this.pictureDisplay = new JLabel();
		layout.putConstraint(SpringLayout.NORTH, pictureDisplay, 169, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, pictureDisplay, 0, SpringLayout.SOUTH, this);
		Conversation = "";
		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.add(submitTextButton);
		this.add(pictureDisplay);
		this.add(mainDialog);
		mainDialog.setEditable(false);
		mainDialog.setEnabled(false);
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setText("Hello My Name Is ChatBot");
		
		this.add(input);
	}

	private void setupLayout()
	{

		layout.putConstraint(SpringLayout.WEST, input, 39, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, input, -125, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, mainDialog, 37, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, mainDialog, 39, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, mainDialog, -66, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, input, 3, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, submitTextButton, 1, SpringLayout.NORTH, input);
		layout.putConstraint(SpringLayout.WEST, submitTextButton, 6, SpringLayout.EAST, input);
	}

	private void setupListeners()
	{
		submitTextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				enterButtonClicked();
			}
		});
	}
	public void setPicture(String picture){
		this.remove(pictureDisplay);
		ImageIcon image = new ImageIcon(getClass().getResource(picture));
		this.pictureDisplay = new JLabel(image);
		layout.putConstraint(SpringLayout.NORTH, pictureDisplay, 5, SpringLayout.SOUTH, mainDialog);
		layout.putConstraint(SpringLayout.WEST, pictureDisplay, ((width/2)-(image.getIconWidth()/2)), SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, pictureDisplay, 0, SpringLayout.SOUTH, this);
		this.add(pictureDisplay);
	}
	public void setWidth(int width){
		this.width = width;
	}
	private void enterButtonClicked(){
		if(!input.getText().equals("")){
		Conversation = "\n"+input.getText() + Conversation;
		Conversation = "\n"+controller.useChatbotCheckers(input.getText()) + Conversation;
		input.setText("");
		mainDialog.setText(Conversation);
		}
		
	}
}
