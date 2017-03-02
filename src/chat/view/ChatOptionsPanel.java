package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import chat.controller.ChatController;

public class ChatOptionsPanel extends JPanel
{
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private JButton setColor;
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private SpringLayout layout;
	private int red;
	private int green;
	private int blue;
	private ChatPanel parent;
	private ChatController controller;
	private JButton searchTwitter;
	private JButton sendTweet;
	private JButton save;
	private JButton load;

	public ChatOptionsPanel(ChatController controller, ChatPanel parent)
	{
		super();
		this.controller = controller;
		this.parent = parent;
		red = parent.getBackground().getRed();
		green = parent.getBackground().getGreen();
		blue = parent.getBackground().getBlue();
		layout = new SpringLayout();
		redSlider = new JSlider(0, 255, red);
		greenSlider = new JSlider(0, 255, green);
		blueSlider = new JSlider(0, 255, blue);
		redLabel = new JLabel("Red");
		greenLabel = new JLabel("Green");
		blueLabel = new JLabel("Blue");
		setColor = new JButton("Set Color");
		setColor.setOpaque(true);
		setColor.setBorder(new LineBorder(Color.BLACK));
		setColor.setBackground(new Color(red, green, blue));
		this.searchTwitter = new JButton("Search Twitter");
		layout.putConstraint(SpringLayout.NORTH, searchTwitter, 28, SpringLayout.SOUTH, blueSlider);
		layout.putConstraint(SpringLayout.WEST, searchTwitter, 0, SpringLayout.WEST, redSlider);
		this.sendTweet = new JButton("Send Tweet");
		layout.putConstraint(SpringLayout.NORTH, sendTweet, 0, SpringLayout.NORTH, searchTwitter);
		layout.putConstraint(SpringLayout.EAST, sendTweet, -10, SpringLayout.EAST, this);
		this.save = new JButton("Save");
		layout.putConstraint(SpringLayout.SOUTH, save, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, save, 0, SpringLayout.EAST, sendTweet);
		this.load = new JButton("Load");
		layout.putConstraint(SpringLayout.NORTH, load, 0, SpringLayout.NORTH, save);
		layout.putConstraint(SpringLayout.EAST, load, -6, SpringLayout.WEST, save);
		setupPanel();
		setupLayout();
		setupListeners();
	}

	/**
	 * sets up the panel
	 */
	private void setupPanel()
	{
		setLayout(layout);
		add(redSlider);
		add(greenSlider);
		add(blueSlider);
		add(setColor);
		add(redLabel);
		add(greenLabel);
		add(blueLabel);
		add(searchTwitter);
		add(sendTweet);
		add(save);
		add(load);
	}

	/**
	 * sets up the layout
	 */
	private void setupLayout()
	{
		layout.putConstraint("North", setColor, 0, "North", greenLabel);
		layout.putConstraint("South", setColor, -200, "South", this);
		layout.putConstraint("South", blueLabel, -169, "South", this);
		layout.putConstraint("West", redSlider, 10, "West", this);
		layout.putConstraint("West", greenSlider, 0, "West", redSlider);
		layout.putConstraint("East", blueSlider, 0, "East", redSlider);
		layout.putConstraint("North", redSlider, 6, "South", redLabel);
		layout.putConstraint("West", redLabel, 10, "West", this);
		layout.putConstraint("South", redLabel, -38, "North", greenLabel);
		layout.putConstraint("West", greenLabel, 10, "West", this);
		layout.putConstraint("North", blueSlider, 6, "South", blueLabel);
		layout.putConstraint("South", greenLabel, -38, "North", blueLabel);
		layout.putConstraint("West", blueLabel, 10, "West", this);
		layout.putConstraint("North", greenSlider, 21, "North", setColor);
		layout.putConstraint("West", setColor, 203, "East", greenLabel);
		layout.putConstraint("East", setColor, -118, "East", this);
	}

	/**
	 * sets up the listeners
	 */
	private void setupListeners()
	{
		redSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				red = redSlider.getValue();
				previewColor();
			}

		});
		greenSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				green = greenSlider.getValue();
				previewColor();
			}

		});
		blueSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				blue = blueSlider.getValue();
				previewColor();
			}

		});
		setColor.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setChatWindowColor();
			}
		});
	}

	/**
	 * changes the buttons cor
	 */
	private void previewColor()
	{
		setColor.setBackground(new Color(red, green, blue));
	}

	/**
	 * sets the chatPanels backgroudColor
	 */
	private void setChatWindowColor()
	{
		parent.setBackground(new Color(red, green, blue));
		controller.saveSettings(new Color(red, green, blue));

	}

}
