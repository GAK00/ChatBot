package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.DefaultCaret;

import chat.controller.ChatController;

// Referenced classes of package chat.view:
//            ChatOptionsFrame

public class ChatPanel extends JPanel
{
	private ChatOptionsFrame currentOptionsPanel;
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
		scrool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrool.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		input = new JTextField();
		Conversation = "";
		input.setDisabledTextColor(Color.BLACK);
		input.setSelectedTextColor(Color.BLACK);
		mainDialog.setDisabledTextColor(Color.BLACK);
		setupPanel();
		setupLayout();
		setupListeners();
	}



	/**
	 * sets up panel
	 */
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
		mainDialog.setText((new StringBuilder(String.valueOf(controller.getGreeting()))).append(",").append(" My Name Is ChatBot").toString());
		DefaultCaret caret = (DefaultCaret) mainDialog.getCaret();
		caret.setUpdatePolicy(1);
	}

	/**
	 * sets up layout
	 */
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

	/**
	 * sets up Listeners
	 */
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

	/**
	 * sets the picture
	 * 
	 * @param picture
	 *            path to picture
	 */
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

	/**
	 * fired when the enterButton is clicked
	 */
	private void enterButtonClicked()
	{
		if (!input.getText().equals(""))
		{
			Conversation = controller.format(Conversation);
			Conversation = (new StringBuilder(String.valueOf(controller.Chat(input.getText())))).append(Conversation).toString();
			mainDialog.setCaretPosition(mainDialog.getCaretPosition());
			input.setText("");
			mainDialog.setText(Conversation);
		}
	}
	public void append (String toAppend)
	{
		Conversation = toAppend +"\n"+Conversation;
		mainDialog.setText(Conversation);
	}
	/**
	 * creates the options menu
	 */
	private void createOptionsMenu()
	{
		if (currentOptionsPanel == null)
		{
			currentOptionsPanel = new ChatOptionsFrame(controller, this);
		}
	}

	/**
	 * ran when ChatOptions is closed
	 */
	public void ChatOptionsClosed()
	{
		currentOptionsPanel = null;
	}

	public String getConversation()
	{
		return Conversation;
	}

	public void setConversation(String Conversation)
	{
		this.Conversation = Conversation;
	}

}
