package chat.view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chat.controller.ChatController;
import java.awt.Component;

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
	private String picture;
	private JScrollPane scrool;
	JFrame parentFrame;

	/**
	 * @wbp.parser.constructor
	 */
	public ChatPanel(ChatController controller, JFrame parentFrame)
	{
		this.parentFrame = parentFrame;
		this.controller = controller;
		this.layout = new SpringLayout();
		this.submitTextButton = new JButton("Enter");
		layout.putConstraint(SpringLayout.NORTH, submitTextButton, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, submitTextButton, -10, SpringLayout.EAST, this);
		this.scrool = new JScrollPane();
		this.input = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, input, -1, SpringLayout.NORTH, submitTextButton);
		layout.putConstraint(SpringLayout.WEST, input, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, input, -6, SpringLayout.WEST, submitTextButton);
		this.pictureDisplay = new JLabel();

		Conversation = "";
		setupPanel();
		setupLayout();
		setupListeners();
	}
	public ChatPanel(ChatController controller)
	{
		parentFrame = new JFrame();
		this.controller = controller;
		this.layout = new SpringLayout();
		this.submitTextButton = new JButton("Enter");
		this.mainDialog = new JTextArea();
		mainDialog.setEditable(false);
		mainDialog.setEnabled(false);
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setText(controller.getGreeting()+","+" My Name Is ChatBot");
		this.scrool = new JScrollPane(mainDialog);
		this.input = new JTextField();
		this.pictureDisplay = new JLabel();

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
		this.add(scrool);
		this.add(input);
		this.mainDialog = new JTextArea();
		layout.putConstraint(SpringLayout.NORTH, mainDialog, 6, SpringLayout.SOUTH, submitTextButton);
		layout.putConstraint(SpringLayout.WEST, mainDialog, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, mainDialog, -231, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, mainDialog, 0, SpringLayout.EAST, submitTextButton);
		add(mainDialog);
		mainDialog.setEnabled(false);
		mainDialog.setEditable(false);
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setText(controller.getGreeting()+","+" My Name Is ChatBot");
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setText(controller.getGreeting()+","+" My Name Is ChatBot");
		
		
		

		
	}

	private void setupLayout()
	{

		
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
		input.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent Enter){
			enterButtonClicked();
		}}
				);
	}
	public void setPicture(String picture){
		this.picture = picture;
		this.remove(pictureDisplay);
		layout.removeLayoutComponent(pictureDisplay);
		ImageIcon image = new ImageIcon(getClass().getResource(picture));
		this.pictureDisplay = new JLabel(image);
		layout.putConstraint(SpringLayout.NORTH, pictureDisplay, 5, SpringLayout.SOUTH, mainDialog);
		layout.putConstraint(SpringLayout.WEST, pictureDisplay, ((width/2)-(image.getIconWidth()/2)), SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, pictureDisplay, 0, SpringLayout.SOUTH, this);
		this.add(pictureDisplay);
	}
	public int getMyKnownWith()
	{
		return width;
	}
	public String getPicture(){
		return picture;
	}
	public void setWidth(int width){
		this.width = width;
	}
	private void enterButtonClicked(){
		if(!input.getText().equals("")){
			Conversation = controller.format(Conversation);
		Conversation = controller.Chat(input.getText()) + Conversation;
		
		input.setText("");
		mainDialog.setText(Conversation);
		}
		
	}
}
