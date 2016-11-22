
package chat.view;

import chat.controller.ChatController;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Referenced classes of package chat.view:
//            ChatOptionsPanel, ChatPanel

public class ChatOptionsFrame extends JFrame
{
private ChatPanel parent;
    public ChatOptionsFrame(ChatController controller, ChatPanel parent)
    {
        this.controller = controller;
        this.parent = parent;
        panel = new ChatOptionsPanel(controller, parent);
        setResizable(false);
        setup();
        setupListeners();
        
    }

    private void setup()
    {
        setTitle("Options");
        GraphicsDevice Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = Screen.getDisplayMode().getWidth();
        int screenHeight = Screen.getDisplayMode().getHeight();
        windowWidth = (int)((double)screenWidth - (double)screenWidth / 1.5D);
        windowHeight = (int)((double)screenHeight - (double)screenHeight / 1.5D);
        setSize(windowWidth, windowHeight);
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;
        setLocation(posX, posY);
        setContentPane(panel);
        setVisible(true);
    }

    public ChatOptionsPanel getPanel()
    {
        return panel;
    }
    private void setupListeners()
    {
    	this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	     
    	            closing();
    	        
    	    }
    	});
    }
    private void closing()
    {
    	parent.ChatOptionsClosed();
    	this.dispose();
    }

    private ChatController controller;
    private ChatOptionsPanel panel;
    private int windowWidth;
    private int windowHeight;
}
