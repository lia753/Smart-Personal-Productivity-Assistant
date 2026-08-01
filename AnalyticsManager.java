import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsManager {

    private static final String FILE_NAME = "analytics.txt";

    private Map<String, Integer> analytics = new HashMap<>();

    public AnalyticsManager() {

        load();

    }

    private void load() {

        analytics.clear();

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            return;

        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 2) {

                    analytics.put(parts[0],
                            Integer.parseInt(parts[1]));

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void save() {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String date : analytics.keySet()) {

                bw.write(date + "," + analytics.get(date));

                bw.newLine();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void taskCompletedToday() {

        String today = LocalDate.now().toString();

        analytics.put(today,
                analytics.getOrDefault(today, 0) + 1);

        save();

    }

    public int[] getWeeklyData() {

        load();

        int[] data = new int[7];

        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {

            LocalDate day = today.minusDays(6 - i);

            String key = day.toString();

            data[i] = analytics.getOrDefault(key, 0);

        }

        return data;

    }

}