package chat.view;

import chat.controller.ChatController;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

// Referenced classes of package chat.view:
//            ChatOptionsFrame

public class ChatPanel extends JPanel
{

	private JButton settingsButton;
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

	public ChatPanel(ChatController controller)
	{
		picture = "images/chatbot.png";
		settingsButton = new JButton(new ImageIcon(getClass().getResource("images/settingIcon.png")));
		this.controller = controller;
		layout = new SpringLayout();
		submitTextButton = new JButton("Enter");
		mainDialog = new JTextArea();
		scrool = new JScrollPane(mainDialog);
		input = new JTextField();
		Conversation = "";
		input.setDisabledTextColor(Color.BLACK);
		input.setSelectedTextColor(Color.BLACK);
		mainDialog.setDisabledTextColor(Color.BLACK);
		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		setLayout(layout);
		add(submitTextButton);
		pictureDisplay = new JLabel();
		add(pictureDisplay);
		add(scrool);
		add(input);
		add(settingsButton);
		mainDialog.setEnabled(false);
		mainDialog.setEditable(false);
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setLineWrap(true);
		mainDialog.setWrapStyleWord(true);
		mainDialog.setText((new StringBuilder(String.valueOf(controller.getGreeting()))).append(",").append(" My Name Is ChatBot").toString());
		DefaultCaret caret = (DefaultCaret) mainDialog.getCaret();
		caret.setUpdatePolicy(1);
	}

	private void setupLayout()
	{
		layout.putConstraint("West", settingsButton, -33, "East", submitTextButton);
		layout.putConstraint("East", settingsButton, 0, "East", submitTextButton);
		layout.putConstraint("North", settingsButton, -41, "South", this);
		layout.putConstraint("South", settingsButton, -10, "South", this);
		layout.putConstraint("North", submitTextButton, 10, "North", this);
		layout.putConstraint("East", submitTextButton, -10, "East", this);
		layout.putConstraint("North", input, -1, "North", submitTextButton);
		layout.putConstraint("West", input, 10, "West", this);
		layout.putConstraint("East", input, -6, "West", submitTextButton);
		layout.putConstraint("North", scrool, 6, "South", submitTextButton);
		layout.putConstraint("West", scrool, 10, "West", this);
		layout.putConstraint("South", scrool, -225, "South", this);
		layout.putConstraint("East", scrool, 0, "East", submitTextButton);
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
		input.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent Enter)
			{
				enterButtonClicked();
			}
		});
		settingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				createOptionsMenu();
			}
		});

	}

	public void setPicture(String picture)
	{
		this.picture = picture;
		ImageIcon image = new ImageIcon(getClass().getResource(picture));
		image.getImage().flush();
		pictureDisplay.setIcon(image);
		layout.putConstraint("North", pictureDisplay, 5, "South", scrool);
		layout.putConstraint("West", pictureDisplay, width / 2 - image.getIconWidth() / 2, "West", this);
		layout.putConstraint("South", pictureDisplay, 0, "South", this);
	}

	public int getMyKnownWith()
	{
		return width;
	}

	public String getPicture()
	{
		return picture;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	private void enterButtonClicked()
	{
		if (!input.getText().equals(""))
		{
			Conversation = controller.format(Conversation);
			Conversation = (new StringBuilder(String.valueOf(controller.Chat(input.getText())))).append(Conversation).toString();
			input.setText("");
			mainDialog.setText(Conversation);
		}
	}

	private void createOptionsMenu()
	{
		new ChatOptionsFrame(controller, this);
	}
	/*
	 * 
	 * private JButton settingsButton; private ChatController controller;
	 * private JButton submitTextButton; private JTextArea mainDialog; private
	 * JTextField input; private SpringLayout layout; private JLabel
	 * pictureDisplay; private int width; private String Conversation; private
	 * String picture; private JScrollPane scrool; private ChatController
	 * controller; private JButton submitTextButton; private JTextArea
	 * mainDialog; private JTextField input; private SpringLayout layout;
	 * private JLabel pictureDisplay; private int width; private String
	 * Conversation; private String picture;
	 * 
	 * public ChatPanel(ChatController controller) {
	 * 
	 * this.controller = controller; this.layout = new SpringLayout();
	 * this.submitTextButton = new JButton("Enter"); this.mainDialog = new
	 * JTextArea(); layout.putConstraint(SpringLayout.SOUTH, mainDialog, -243,
	 * SpringLayout.SOUTH, this); this.input = new JTextField();
	 * this.pictureDisplay = new JLabel();
	 * layout.putConstraint(SpringLayout.NORTH, pictureDisplay, 169,
	 * SpringLayout.NORTH, this); layout.putConstraint(SpringLayout.SOUTH,
	 * pictureDisplay, 0, SpringLayout.SOUTH, this); Conversation = "";
	 * setupPanel(); setupLayout(); setupListeners(); }
	 * 
	 * private void setupPanel() { this.setLayout(layout);
	 * this.add(submitTextButton); this.add(pictureDisplay);
	 * this.add(mainDialog); mainDialog.setDisabledTextColor(Color.BLACK);
	 * 
	 * mainDialog.setEditable(false); mainDialog.setEnabled(false);
	 * mainDialog.setLineWrap(true); mainDialog.setWrapStyleWord(true);
	 * mainDialog.setText("Hello My Name Is ChatBot");
	 * 
	 * this.add(input); input.setSelectedTextColor(Color.BLACK);
	 * input.setDisabledTextColor(Color.black); }
	 * 
	 * private void setupLayout() {
	 * 
	 * layout.putConstraint(SpringLayout.WEST, input, 39, SpringLayout.WEST,
	 * this); layout.putConstraint(SpringLayout.EAST, input, -125,
	 * SpringLayout.EAST, this); layout.putConstraint(SpringLayout.NORTH,
	 * mainDialog, 37, SpringLayout.NORTH, this);
	 * layout.putConstraint(SpringLayout.WEST, mainDialog, 39,
	 * SpringLayout.WEST, this); layout.putConstraint(SpringLayout.EAST,
	 * mainDialog, -66, SpringLayout.EAST, this);
	 * layout.putConstraint(SpringLayout.NORTH, input, 3, SpringLayout.NORTH,
	 * this); layout.putConstraint(SpringLayout.NORTH, submitTextButton, 1,
	 * SpringLayout.NORTH, input); layout.putConstraint(SpringLayout.WEST,
	 * submitTextButton, 6, SpringLayout.EAST, input); }
	 * 
	 * private void setupListeners() { submitTextButton.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent click) {
	 * enterButtonClicked(); } }); input.addActionListener(new ActionListener(){
	 * public void actionPerformed(ActionEvent Enter){ enterButtonClicked(); }}
	 * ); } public void setPicture(String picture){ this.picture = picture;
	 * this.remove(pictureDisplay);
	 * layout.removeLayoutComponent(pictureDisplay); ImageIcon image = new
	 * ImageIcon(getClass().getResource(picture)); this.pictureDisplay = new
	 * JLabel(image); layout.putConstraint(SpringLayout.NORTH, pictureDisplay,
	 * 5, SpringLayout.SOUTH, mainDialog);
	 * layout.putConstraint(SpringLayout.WEST, pictureDisplay,
	 * ((width/2)-(image.getIconWidth()/2)), SpringLayout.WEST, this);
	 * layout.putConstraint(SpringLayout.SOUTH, pictureDisplay, 0,
	 * SpringLayout.SOUTH, this); this.add(pictureDisplay); } public int
	 * getMyKnownWith() { return width; } public String getPicture(){ return
	 * picture; } public void setWidth(int width){ this.width = width; } private
	 * void enterButtonClicked(){ if(!input.getText().equals("")){ Conversation
	 * = controller.Chat(input.getText()) + Conversation; input.setText("");
	 * mainDialog.setText(Conversation); }
	 * 
	 * }
	 */

}
