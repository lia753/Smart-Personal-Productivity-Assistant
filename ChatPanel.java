import javax.swing.*;

public class ChatPanel extends JPanel {

    public ChatPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(Theme.BACKGROUND);

    }

    public void addBotMessage(String message) {

        add(new MessageBubble("🤖 " + message, false));

        add(Box.createVerticalStrut(10));

        revalidate();
        repaint();

    }

    public void addUserMessage(String message) {

        add(new MessageBubble("👤 " + message, true));

        add(Box.createVerticalStrut(10));

        revalidate();
        repaint();

    }

}