
package chat.view;

import chat.controller.ChatController;
import java.awt.*;
import javax.swing.JFrame;

// Referenced classes of package chat.view:
//            ChatOptionsPanel, ChatPanel

public class ChatOptionsFrame extends JFrame
{

    public ChatOptionsFrame(ChatController controller, ChatPanel parent)
    {
        setDefaultCloseOperation(2);
        this.controller = controller;
        panel = new ChatOptionsPanel(controller, parent);
        setResizable(false);
        setup();
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

    private ChatController controller;
    private ChatOptionsPanel panel;
    private int windowWidth;
    private int windowHeight;
}
