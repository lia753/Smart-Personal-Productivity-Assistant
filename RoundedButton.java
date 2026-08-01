import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private final Color backgroundColor;

    public RoundedButton(String text, Color color) {
        super(text);

        this.backgroundColor = color;

        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(110, 42));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(backgroundColor.darker());
        } else if (getModel().isRollover()) {
            g2.setColor(backgroundColor.brighter());
        } else {
            g2.setColor(backgroundColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g);
        g2.dispose();
    }
}