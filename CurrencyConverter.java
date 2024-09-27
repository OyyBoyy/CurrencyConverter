import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Remove all non-numeric characters
        String cleanedStr = str.replaceAll("[^0-9.]", "");

        // Check if the cleaned string has at most one decimal point
        if (!cleanedStr.matches("\\d*\\.?\\d+")) {
            return false;
        }

        try {
            // Try parsing the cleaned string to a double
            Double.parseDouble(cleanedStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Available conversions
        HashMap<String, Double> conversions = new HashMap<>();
        conversions.put("USD", 1.0); // US Dollar
        conversions.put("EUR", 1.117576); // Euro
        conversions.put("GBP", 1.341255); // Pound
        conversions.put("INR", 0.011958); // Rupee
        conversions.put("AUD", 0.689981); // Austrailian Dollar
        conversions.put("CAD", 0.742357); // Canadian Dollar
        conversions.put("SGD", 0.779003); // Singapore Dollar
        conversions.put("CHF", 1.181453); // Swiss Franc
        conversions.put("MYR", 0.241259); // Malaysian Ringgit
        conversions.put("JPY", 0.006895); // Japenese Yen
        conversions.put("CNY", 0.142638); // Chinese Yuan Renminbi

        // Initialize variables
        double num = 0;
        String cur1 = "";
        String cur2 = "";
        
        System.out.println("Please enter the conversion in the format: Convert <amount> <from currency> to <to currency>");
        try {
            String str = input.nextLine();
            String[] words = str.split(" ");

            // Iterate through all words in the input
            for (String word : words) {

                // Check for number
                if (isNumeric(word)) {
                    num = Double.parseDouble(word.replaceAll("[^0-9.]", ""));
                }
                // Check available conversions
                else {
                    String currency = word.replaceAll("[^a-zA-Z]", "").toUpperCase();
                    if (conversions.containsKey(currency)) {
                        if (cur1.isEmpty()) {
                            cur1 = currency;
                        } else if (cur2.isEmpty()) {
                            cur2 = currency;
                        }
                    }
                }
            }

            // Check for empty inputs
            if (cur1.isEmpty() || cur2.isEmpty()) {
                System.out.println("Invalid input. Please provide a valid number and two currencies.");
            } else {
                // Perform the conversion and print the result
                double result = Math.round(num * (conversions.get(cur1) / conversions.get(cur2)) * 100.0) / 100.0;
                System.out.printf("%.2f %s = %.2f %s%n", num, cur1, result, cur2);
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
