import java.io.*;
import java.time.LocalDate;

public class StreakManager {

    private static final String FILE = "streak.txt";

    private int streak;

    private LocalDate lastCompletedDate;

    public StreakManager() {

        load();

    }

    private void load() {

        File file = new File(FILE);

        if (!file.exists()) {

            streak = 0;
            lastCompletedDate = null;

            return;
        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            streak = Integer.parseInt(br.readLine());

            String date = br.readLine();

            if (date != null && !date.isEmpty()) {

                lastCompletedDate = LocalDate.parse(date);

            }

        } catch (Exception e) {

            streak = 0;

            lastCompletedDate = null;

        }

    }

    private void save() {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(FILE))) {

            bw.write(String.valueOf(streak));

            bw.newLine();

            if (lastCompletedDate != null) {

                bw.write(lastCompletedDate.toString());

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void taskCompletedToday() {

        LocalDate today = LocalDate.now();

        if (lastCompletedDate == null) {

            streak = 1;

        } else if (lastCompletedDate.equals(today)) {

            return;

        } else if (lastCompletedDate.plusDays(1).equals(today)) {

            streak++;

        } else {

            streak = 1;

        }

        lastCompletedDate = today;

        save();

    }

    public int getStreak() {

        return streak;

    }

}