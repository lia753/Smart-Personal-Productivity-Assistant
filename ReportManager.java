import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReportManager {

    public String exportReport(TaskManager manager,
                               BadgeManager badgeManager) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("Productivity_Report.txt"));

            int total = manager.getTotalTasks();
            int completed = manager.getCompletedTasks();
            int pending = manager.getPendingTasks();
            int streak = manager.getCurrentStreak();

            double percentage = 0;

            if (total != 0) {
                percentage = (completed * 100.0) / total;
            }

            writer.write("SMART PRODUCTIVITY REPORT");
            writer.newLine();
            writer.write("====================================");
            writer.newLine();
            writer.newLine();

            writer.write("Date : " + LocalDate.now());
            writer.newLine();
            writer.newLine();

            writer.write("Total Tasks : " + total);
            writer.newLine();

            writer.write("Completed : " + completed);
            writer.newLine();

            writer.write("Pending : " + pending);
            writer.newLine();

            writer.write(String.format("Completion : %.1f%%", percentage));
            writer.newLine();

            writer.write("Current Streak : " + streak + " day(s)");
            writer.newLine();
            writer.newLine();

            writer.write("ACHIEVEMENTS");
            writer.newLine();
            writer.write("---------------------------");
            writer.newLine();

            List<String> badges =
                    badgeManager.getBadges(completed, streak);

            if (badges.isEmpty()) {

                writer.write("No badges unlocked.");

            } else {

                for (String badge : badges) {

                    writer.write(badge);
                    writer.newLine();

                }

            }

            writer.close();

            return "✅ Report exported successfully!\n\nSaved as:\nProductivity_Report.txt";

        } catch (IOException e) {

            return "❌ Error exporting report.";

        }

    }

}