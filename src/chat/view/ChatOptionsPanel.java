package chat.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import chat.controller.ChatController;
import chat.model.FileController;

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
	private JComboBox<String> mediaSelector;
	private String currentMedia;
	private JComboBox<String> searchOptions;

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
		this.searchTwitter = new JButton("Search Media");
		this.sendTweet = new JButton("Send Media");
		this.save = new JButton("Save");
		this.load = new JButton("Load");
		String[] options = new String[] { "Twitter", "Facebook" };
		this.mediaSelector = new JComboBox<String>(options);
		String[] optionsSearch = new String[]{"Keyword","User","Category"};
		this.searchOptions = new JComboBox<String>(optionsSearch);
		currentMedia = "Twitter";
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
		add(mediaSelector);
		add(searchOptions);
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
		layout.putConstraint(SpringLayout.NORTH, load, 0, SpringLayout.NORTH, save);
		layout.putConstraint(SpringLayout.EAST, load, -6, SpringLayout.WEST, save);
		layout.putConstraint(SpringLayout.SOUTH, save, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, save, 0, SpringLayout.EAST, sendTweet);
		layout.putConstraint(SpringLayout.NORTH, sendTweet, 0, SpringLayout.NORTH, searchTwitter);
		layout.putConstraint(SpringLayout.EAST, sendTweet, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, searchTwitter, 28, SpringLayout.SOUTH, blueSlider);
		layout.putConstraint(SpringLayout.WEST, searchTwitter, 0, SpringLayout.WEST, redSlider);
		layout.putConstraint(SpringLayout.NORTH, mediaSelector, 1, SpringLayout.NORTH, searchTwitter);
		layout.putConstraint(SpringLayout.WEST, mediaSelector, -170, SpringLayout.WEST, sendTweet);
		layout.putConstraint(SpringLayout.EAST, mediaSelector, -31, SpringLayout.WEST, sendTweet);
		layout.putConstraint(SpringLayout.NORTH, searchOptions, 6, SpringLayout.SOUTH, searchTwitter);
		layout.putConstraint(SpringLayout.WEST, searchOptions, 0, SpringLayout.WEST, redSlider);
		layout.putConstraint(SpringLayout.EAST, searchOptions, 0, SpringLayout.EAST, searchTwitter);
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
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{

				save();
			}
		});
		load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				load();
			}
		});
		sendTweet.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				sendTweet();
			}
		});
		searchTwitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				getTweet();
			}
		});
		mediaSelector.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if (!mediaSelector.getSelectedItem().equals(currentMedia))
				{
					currentMedia = (String) mediaSelector.getSelectedItem();
					controller.toggleMedia();
				}
			}
		});

	}

	private void getTweet()
	{

		// String input = JOptionPane.showInputDialog(this, "Enter twitter
		// user");
		String input = JOptionPane.showInputDialog(this,"Enter query");
		Thread thread = new Thread()

		{
			public void run()
			{

				String results = controller.searchMedia((String)searchOptions.getSelectedItem(), input);
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						parent.append(results);
					};

				});

			}
		};
		
		thread.start();
		Timer timer = new Timer();
		JOptionPane pane = new JOptionPane(controller.getStatus());
		JDialog dialog = pane.createDialog(this, "Loading");
		dialog.setSize(new Dimension(350, 100));
		Thread disposeThread = new Thread()
		{
			public void run()
			{
				while (thread.isAlive() && dialog != null)
				{
					try
					{
						Thread.sleep(100);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				timer.cancel();
				if (dialog != null)
				{
					dialog.dispose();
				}
			}
		};

		TimerTask task = new TimerTask()
		{

			@Override
			public void run()
			{
				pane.setMessage(controller.getStatus());

			}
		};
		timer.schedule(task, 100, 100);
		disposeThread.start();
		dialog.setVisible(true);

	}

	private void sendTweet()
	{
		controller.sendTweet(JOptionPane.showInputDialog("Please Enter you Message"));
	}

	private void save()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Directory Selector");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showDialog(this, "select Directory");
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String directory;
			if (Files.exists(Paths.get(fileChooser.getSelectedFile().toURI())))
			{
				directory = fileChooser.getSelectedFile().getPath();
			} else
			{
				directory = fileChooser.getCurrentDirectory().getPath();
			}
			String fileName = directory + "/" + JOptionPane.showInputDialog("please enter a name for this conversation");
			System.out.println(fileName);
			String time = Integer.toString(LocalDateTime.now().getMinute());
			if (time.length() == 1)
			{
				time = "0" + time;
			}
			String hour;
			String amPm;
			int timeHour = LocalDateTime.now().getHour();
			if (timeHour <= 12)
			{
				hour = Integer.toString(timeHour);
				amPm = "Am";
			} else
			{
				timeHour = timeHour - 12;
				hour = Integer.toString(timeHour);
				amPm = "Pm";

			}
			LocalDateTime current = LocalDateTime.now();
			String timeStamp = current.getMonth() + " " + current.getDayOfMonth() + " At " + hour + "-" + time + amPm;
			FileController.saveFile(controller, fileName + "-" + timeStamp, parent.getConversation());
		}
	}

	private void load()
	{
		String fileName = JOptionPane.showInputDialog("please enter a name for this conversation");
		JOptionPane.showMessageDialog(this, FileController.readFile(controller, fileName));
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
