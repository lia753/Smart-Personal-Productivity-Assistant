import java.util.Random;

public class ResponseData {

    private Random random = new Random();

    private String[] greetings = {

            "Hello! 😊",
            "Hi there! 👋",
            "Hey! Nice to see you!",
            "Greetings! 😄",
            "Welcome back!"
    };

    private String[] jokes = {

            "Why do Java developers wear glasses? Because they don't C# 😂",

            "Debugging is like being the detective in a crime movie where you're also the criminal 😂",

            "Why did the computer get cold? It forgot to close Windows 😂",

            "Why was the Java book sad? It had too many exceptions 😂",

            "Programmer: A machine that turns coffee into code ☕"
    };

    private String[] motivation = {

            "Success comes from consistency.",

            "Small progress every day leads to big success.",

            "Never stop learning.",

            "Discipline beats motivation.",

            "Your future is created by what you do today."
    };

    public String randomGreeting(){

        return greetings[random.nextInt(greetings.length)];

    }

    public String randomJoke(){

        return jokes[random.nextInt(jokes.length)];

    }

    public String randomMotivation(){

        return motivation[random.nextInt(motivation.length)];

    }

}