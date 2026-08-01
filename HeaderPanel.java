import java.awt.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {

        setLayout(new BorderLayout());

        setBackground(new Color(24,24,24));

        setPreferredSize(new Dimension(0,70));

        JLabel title = new JLabel("🤖 Smart Personal Productivity Assistant");

        title.setForeground(Color.WHITE);

        title.setFont(new Font("Segoe UI", Font.BOLD,20));

        JLabel status = new JLabel("🟢 Online");

        status.setForeground(new Color(0,200,83));

        status.setFont(new Font("Segoe UI",Font.PLAIN,15));

        add(title,BorderLayout.WEST);

        add(status,BorderLayout.EAST);

        setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

    }

}