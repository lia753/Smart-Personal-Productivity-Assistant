import java.awt.*;
import javax.swing.*;

public class ChatPage extends JPanel {

    private ChatPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private RoundedButton sendButton;
    private RoundedButton clearButton;

    private ResponseEngine engine;

    public ChatPage() {

        engine = new ResponseEngine();

        setLayout(new BorderLayout());

        // ================= CHAT AREA =================

        chatPanel = new ChatPanel();

        scrollPane = new JScrollPane(chatPanel);

        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Theme.BACKGROUND);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // ================= INPUT =================

        inputField = new JTextField();

        inputField.setFont(Theme.CHAT_FONT);
        inputField.setBackground(Theme.INPUT);
        inputField.setForeground(Theme.TEXT);
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(
                BorderFactory.createEmptyBorder(10, 12, 10, 12));

        sendButton =
                new RoundedButton("Send",
                        new Color(34, 197, 94));

        clearButton =
                new RoundedButton("Clear",
                        new Color(239, 68, 68));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 8, 0));

        buttonPanel.setOpaque(false);

        buttonPanel.add(clearButton);
        buttonPanel.add(sendButton);

        JPanel bottom = new JPanel(new BorderLayout(8, 8));

        bottom.setBackground(Theme.BACKGROUND);

        bottom.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(buttonPanel, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        // ================= WELCOME =================

        chatPanel.addBotMessage("Hello! 👋");
        chatPanel.addBotMessage("I'm your Smart Personal Productivity Assistant.");
        chatPanel.addBotMessage("Type 'help' to see everything I can do.");

        // ================= EVENTS =================

        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());

        clearButton.addActionListener(e -> {

            chatPanel.removeAll();

            chatPanel.revalidate();
            chatPanel.repaint();

            chatPanel.addBotMessage("Chat Cleared.");

        });

    }

    private void sendMessage() {

        String message = inputField.getText().trim();

        if (message.isEmpty())
            return;

        chatPanel.addUserMessage(message);

        String response = engine.getResponse(message);

        chatPanel.addBotMessage(response);

        inputField.setText("");

        SwingUtilities.invokeLater(() -> {

            JScrollBar bar = scrollPane.getVerticalScrollBar();

            bar.setValue(bar.getMaximum());

        });

    }

}