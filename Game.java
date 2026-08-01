import java.util.Random;

public class Game {

    private Random random = new Random();

    public String playRockPaperScissors(String userChoice) {

        String[] choices = {"rock", "paper", "scissors"};

        String botChoice = choices[random.nextInt(3)];

        userChoice = userChoice.toLowerCase();

        String result;

        if (userChoice.equals(botChoice)) {

            result = "It's a Draw!";

        } else if ((userChoice.equals("rock") && botChoice.equals("scissors"))
                || (userChoice.equals("paper") && botChoice.equals("rock"))
                || (userChoice.equals("scissors") && botChoice.equals("paper"))) {

            result = "🎉 You Win!";

        } else {

            result = "🤖 Bot Wins!";

        }

        return """
Bot chose : %s

%s
""".formatted(botChoice, result);

    }

}