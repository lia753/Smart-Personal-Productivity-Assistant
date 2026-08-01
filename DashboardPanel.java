import java.awt.*;
import java.util.List;
import javax.swing.*;

public class DashboardPanel extends JPanel {

    private JLabel totalLabel;
    private JLabel completedLabel;
    private JLabel pendingLabel;
    private JLabel percentageLabel;
    private JLabel scoreLabel;

    private JProgressBar progressBar;

    private JTextArea badgeArea;

    private WeeklyChartPanel chartPanel;

    private RoundedButton refreshButton;
    private RoundedButton exportButton;

    private TaskManager manager;
    private BadgeManager badgeManager;
    private ReportManager reportManager;

    public DashboardPanel() {

        manager = new TaskManager();
        badgeManager = new BadgeManager();
        reportManager = new ReportManager();

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("📊 Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(20,20));
        content.setBackground(Theme.BACKGROUND);

        // ================= Statistics =================

        JPanel stats = new JPanel(new GridLayout(3,2,15,15));
        stats.setBackground(Theme.BACKGROUND);
        stats.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        totalLabel = createCard();
        completedLabel = createCard();
        pendingLabel = createCard();
        percentageLabel = createCard();
        scoreLabel = createCard();

        progressBar = new JProgressBar(0,100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        progressBar.setBackground(new Color(45,45,45));
        progressBar.setForeground(new Color(34,197,94));

        stats.add(totalLabel);
        stats.add(completedLabel);
        stats.add(pendingLabel);
        stats.add(percentageLabel);
        stats.add(scoreLabel);
        stats.add(progressBar);

        content.add(stats, BorderLayout.NORTH);

        // ================= Weekly Chart =================

        chartPanel = new WeeklyChartPanel();

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Theme.BACKGROUND);
        center.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        center.add(chartPanel, BorderLayout.CENTER);

        content.add(center, BorderLayout.CENTER);

        // ================= Badges =================

        badgeArea = new JTextArea();
        badgeArea.setEditable(false);
        badgeArea.setFont(new Font("Segoe UI", Font.BOLD, 15));
        badgeArea.setBackground(new Color(45,45,45));
        badgeArea.setForeground(Color.WHITE);
        badgeArea.setMargin(new Insets(10,10,10,10));

        JScrollPane badgeScroll = new JScrollPane(badgeArea);
        badgeScroll.setPreferredSize(new Dimension(250,150));

        content.add(badgeScroll, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);

        // ================= Buttons =================

        refreshButton = new RoundedButton("Refresh",
                new Color(59,130,246));

        exportButton = new RoundedButton("Export",
                new Color(168,85,247));

        JPanel bottom = new JPanel();
        bottom.setBackground(Theme.BACKGROUND);

        bottom.add(refreshButton);
        bottom.add(exportButton);

        add(bottom, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> refreshDashboard());

        exportButton.addActionListener(e -> {

            String message =
                    reportManager.exportReport(manager, badgeManager);

            JOptionPane.showMessageDialog(this, message);

        });

        refreshDashboard();
    }

    private JLabel createCard() {

        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(45,45,45));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));

        return label;
    }

    public void refreshDashboard() {

        manager.reload();

        int total = manager.getTotalTasks();
        int completed = manager.getCompletedTasks();
        int pending = manager.getPendingTasks();
        int streak = manager.getCurrentStreak();

        double percent = 0;

        if (total > 0) {
            percent = (completed * 100.0) / total;
        }

        totalLabel.setText("📋 Total : " + total);
        completedLabel.setText("✅ Completed : " + completed);
        pendingLabel.setText("⌛ Pending : " + pending);
        percentageLabel.setText(String.format("📈 %.1f%%", percent));

        progressBar.setValue((int) percent);
        progressBar.setString((int) percent + "% Completed");

        int score = (int) percent + (streak * 2);

        if (score > 100)
            score = 100;

        scoreLabel.setText("🎯 Score : " + score + "/100");

        List<String> badges =
                badgeManager.getBadges(completed, streak);

        StringBuilder sb = new StringBuilder();

        sb.append("🏆 ACHIEVEMENTS\n\n");

        if (badges.isEmpty()) {

            sb.append("No badges unlocked.");

        } else {

            for (String badge : badges) {

                sb.append(badge).append("\n");

            }

        }

        badgeArea.setText(sb.toString());

        chartPanel.setData(manager.getWeeklyAnalytics());

    }

}