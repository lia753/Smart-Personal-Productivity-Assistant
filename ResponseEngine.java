import java.time.LocalDate;
import java.time.LocalTime;

public class ResponseEngine {

    private String userName = "";

    private Calculator calculator = new Calculator();
    private Game game = new Game();
    private ResponseData data = new ResponseData();
    private TaskManager taskManager = new TaskManager();

    public String getResponse(String message) {

        String lower = message.toLowerCase().trim();

        // ---------------- TASK MANAGER ----------------

        if (lower.startsWith("add task ")) {

            String task = message.substring(9).trim();

            if (task.isEmpty()) {
                return "Please enter a task.";
            }

            return taskManager.addTask(task);
        }

        if (lower.equals("show tasks")) {
            return taskManager.showTasks();
        }

        if (lower.startsWith("complete task ")) {

            try {

                int index = Integer.parseInt(message.substring(14).trim());

                return taskManager.completeTask(index);

            } catch (Exception e) {

                return "Please enter a valid task number.";

            }

        }

        if (lower.startsWith("delete task ")) {

            try {

                int index = Integer.parseInt(message.substring(12).trim());

                return taskManager.deleteTask(index);

            } catch (Exception e) {

                return "Please enter a valid task number.";

            }

        }

        // ---------------- CALCULATOR ----------------

        if (lower.startsWith("calculate")) {
            return calculator.calculate(message);
        }

        // ---------------- GAME ----------------

        if (lower.equals("rock") ||
            lower.equals("paper") ||
            lower.equals("scissors")) {

            return game.playRockPaperScissors(lower);
        }

        // ---------------- NAME ----------------

        if (lower.startsWith("my name is ")) {

            userName = message.substring(11).trim();

            return "Nice to meet you, " + userName + " 😊";
        }

        if (lower.equals("who am i")) {

            if (userName.isEmpty()) {
                return "I don't know your name yet.";
            }

            return "You are " + userName + ".";
        }

        // ---------------- GREETINGS ----------------

        if (lower.contains("hello") ||
            lower.contains("hi") ||
            lower.contains("hey")) {

            return data.randomGreeting();
        }

        // ---------------- TIME & DATE ----------------

        if (lower.contains("time")) {
            return "Current Time: " + LocalTime.now().withNano(0);
        }

        if (lower.contains("date")) {
            return "Today's Date: " + LocalDate.now();
        }

        // ---------------- JOKES ----------------

        if (lower.contains("joke")) {
            return data.randomJoke();
        }

        // ---------------- MOTIVATION ----------------

        if (lower.contains("motivation") ||
            lower.contains("quote")) {

            return data.randomMotivation();
        }

        // ---------------- HELP ----------------

        if (lower.equals("help")) {

            return """
==================== COMMANDS ====================

👋 General
hello
my name is Likitha
who am i
time
date

🧮 Calculator
calculate 25+50
calculate 45*6

🎮 Game
rock
paper
scissors

😂 Fun
joke
motivation

📋 Task Manager
add task Finish DSA
show tasks
complete task 1
delete task 1

==================================================
""";
        }

        // ---------------- DEFAULT ----------------

        return "Sorry, I didn't understand that.\nType 'help' to see all available commands.";
    }
}