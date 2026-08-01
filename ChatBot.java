import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatBot extends JFrame {

    private SidebarPanel sidebar;

    private ChatPage chatPage;
    private TaskPanel taskPanel;
    private NotesPanel notesPanel;
    private DashboardPanel dashboardPanel;

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public ChatBot() {

        setTitle("🤖 Smart Personal Productivity Assistant");

        setSize(1000,700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // ================= HEADER =================

        add(new HeaderPanel(), BorderLayout.NORTH);

        // ================= SIDEBAR =================

        sidebar = new SidebarPanel();

        add(sidebar, BorderLayout.WEST);

        // ================= CARD LAYOUT =================

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        chatPage = new ChatPage();

        taskPanel = new TaskPanel();

        notesPanel = new NotesPanel();

        dashboardPanel = new DashboardPanel();

        contentPanel.add(chatPage, "CHAT");

        contentPanel.add(taskPanel, "TASKS");

        contentPanel.add(notesPanel, "NOTES");

        contentPanel.add(dashboardPanel, "DASHBOARD");

        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "CHAT");

        // ================= SIDEBAR EVENTS =================

        sidebar.getChatButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(contentPanel, "CHAT");

            }

        });

        sidebar.getTaskButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                taskPanel.loadTasks();

                cardLayout.show(contentPanel, "TASKS");

            }

        });

        sidebar.getNotesButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(contentPanel, "NOTES");

            }

        });

        sidebar.getDashboardButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(contentPanel, "DASHBOARD");

            }

        });

        sidebar.getSettingsButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        ChatBot.this,
                        "Settings page coming soon!");

            }

        });

        setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new ChatBot();

            }

        });

    }
}