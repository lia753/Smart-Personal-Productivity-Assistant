public class Calculator {

    public String calculate(String input) {

        input = input.replace("calculate", "").trim();

        try {

            if (input.contains("+")) {

                String[] parts = input.split("\\+");

                double a = Double.parseDouble(parts[0].trim());
                double b = Double.parseDouble(parts[1].trim());

                return "Answer = " + (a + b);
            }

            if (input.contains("-")) {

                String[] parts = input.split("-");

                double a = Double.parseDouble(parts[0].trim());
                double b = Double.parseDouble(parts[1].trim());

                return "Answer = " + (a - b);
            }

            if (input.contains("*")) {

                String[] parts = input.split("\\*");

                double a = Double.parseDouble(parts[0].trim());
                double b = Double.parseDouble(parts[1].trim());

                return "Answer = " + (a * b);
            }

            if (input.contains("/")) {

                String[] parts = input.split("/");

                double a = Double.parseDouble(parts[0].trim());
                double b = Double.parseDouble(parts[1].trim());

                if (b == 0)
                    return "Cannot divide by zero.";

                return "Answer = " + (a / b);
            }

            return "Example: calculate 25+17";

        } catch (Exception e) {

            return "Invalid calculation.";

        }

    }

}