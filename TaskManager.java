import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final String FILE_NAME = "tasks.txt";

    private final List<String> tasks = new ArrayList<>();

    private final StreakManager streakManager;

    private final AnalyticsManager analyticsManager;

    public TaskManager() {

        streakManager = new StreakManager();

        analyticsManager = new AnalyticsManager();

        loadTasks();

    }

    private void loadTasks() {

        tasks.clear();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    tasks.add(line);

                }

            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    private void saveTasks() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String task : tasks) {

                writer.write(task);
                writer.newLine();

            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public void reload() {

        loadTasks();

    }

    public List<String> getTasks() {

        return tasks;

    }

    public int getTotalTasks() {

        return tasks.size();

    }

    public int getCompletedTasks() {

        int count = 0;

        for (String task : tasks) {

            if (task.startsWith("[x]")) {

                count++;

            }

        }

        return count;

    }

    public int getPendingTasks() {

        return getTotalTasks() - getCompletedTasks();

    }

    public int getCurrentStreak() {

        return streakManager.getStreak();

    }

    public int[] getWeeklyAnalytics() {

        return analyticsManager.getWeeklyData();

    }

    public String addTask(String task) {

        if (task == null || task.trim().isEmpty()) {

            return "❌ Task cannot be empty.";

        }

        tasks.add("[ ] " + task.trim());

        saveTasks();

        return "✅ Task Added";

    }

    public String deleteTask(int index) {

        if (index < 1 || index > tasks.size()) {

            return "❌ Invalid Task Number";

        }

        tasks.remove(index - 1);

        saveTasks();

        return "🗑️ Task Deleted";

    }

    public String completeTask(int index) {

        if (index < 1 || index > tasks.size()) {

            return "❌ Invalid Task Number";

        }

        String task = tasks.get(index - 1);

        if (task.startsWith("[x]")) {

            return "Task already completed.";

        }

        tasks.set(index - 1, task.replace("[ ]", "[x]"));

        saveTasks();

        // Update streak
        streakManager.taskCompletedToday();

        // Update analytics
        analyticsManager.taskCompletedToday();

        return "🎉 Task Completed\n🔥 Current Streak : "
                + streakManager.getStreak() + " day(s)";

    }

    public String showTasks() {

        reload();

        if (tasks.isEmpty()) {

            return "📋 No Tasks Available";

        }

        StringBuilder sb = new StringBuilder();

        sb.append("=========== TASKS ===========\n\n");

        for (int i = 0; i < tasks.size(); i++) {

            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");

        }

        sb.append("\n");

        sb.append("Total : ").append(getTotalTasks()).append("\n");

        sb.append("Completed : ").append(getCompletedTasks()).append("\n");

        sb.append("Pending : ").append(getPendingTasks()).append("\n");

        sb.append("🔥 Current Streak : ")
                .append(getCurrentStreak())
                .append(" day(s)");

        return sb.toString();

    }

}