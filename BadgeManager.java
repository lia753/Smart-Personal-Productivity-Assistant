import java.util.ArrayList;
import java.util.List;

public class BadgeManager {

    public List<String> getBadges(int completedTasks, int streak) {

        List<String> badges = new ArrayList<>();

        if (completedTasks >= 1)
            badges.add("🏅 First Task Completed");

        if (completedTasks >= 10)
            badges.add("🥉 Bronze Achiever");

        if (completedTasks >= 25)
            badges.add("🥈 Silver Achiever");

        if (completedTasks >= 50)
            badges.add("🥇 Gold Achiever");

        if (streak >= 7)
            badges.add("🔥 7-Day Streak");

        if (completedTasks >= 100 && streak >= 30)
            badges.add("👑 Productivity Master");

        return badges;

    }

}