import java.awt.*;
import javax.swing.*;

public class SidebarPanel extends JPanel {

    private JButton chatButton;
    private JButton taskButton;
    private JButton notesButton;
    private JButton dashboardButton;
    private JButton settingsButton;

    public SidebarPanel() {

        setPreferredSize(new Dimension(170, 0));
        setBackground(new Color(28, 28, 28));

        setLayout(new GridLayout(10, 1, 10, 10));

        setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        chatButton = createButton("💬 Chat");
        taskButton = createButton("📋 Tasks");
        notesButton = createButton("📝 Notes");
        dashboardButton = createButton("📊 Dashboard");
        settingsButton = createButton("⚙ Settings");

        add(chatButton);
        add(taskButton);
        add(notesButton);
        add(dashboardButton);
        add(settingsButton);
    }

    private JButton createButton(String text){

        JButton button = new JButton(text);

        button.setFocusPainted(false);

        button.setFont(new Font("Segoe UI", Font.BOLD,15));

        button.setBackground(new Color(50,50,50));

        button.setForeground(Color.WHITE);

        return button;
    }

    public JButton getChatButton(){
        return chatButton;
    }

    public JButton getTaskButton(){
        return taskButton;
    }

    public JButton getNotesButton(){
        return notesButton;
    }

    public JButton getDashboardButton(){
        return dashboardButton;
    }

    public JButton getSettingsButton(){
        return settingsButton;
    }

}