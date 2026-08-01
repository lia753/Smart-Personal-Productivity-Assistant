import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MessageBubble extends JPanel {

    public MessageBubble(String message, boolean isUser) {

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new FlowLayout(
                isUser ? FlowLayout.RIGHT : FlowLayout.LEFT,
                15,
                8));

        wrapper.setOpaque(false);

        BubblePanel bubble = new BubblePanel(
                isUser ? Theme.USER_BUBBLE : Theme.BOT_BUBBLE);

        bubble.setLayout(new BorderLayout());

        JLabel label = new JLabel(
                "<html><div style='width:260px;'>" + message + "</div></html>");

        label.setForeground(Theme.TEXT);
        label.setFont(Theme.CHAT_FONT);

        bubble.add(label, BorderLayout.CENTER);

        wrapper.add(bubble);

        add(wrapper, BorderLayout.CENTER);
    }

    private static class BubblePanel extends JPanel {

        private final Color color;

        public BubblePanel(Color color) {
            this.color = color;
            setOpaque(false);
            setBorder(new EmptyBorder(12, 16, 12, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    25,
                    25);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}