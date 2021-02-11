package pl.wojtek;

import java.util.Locale;
import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String enterString(String message){
        System.out.print("Enter " + message + " ");
        return scanner.nextLine();
    }

    public boolean askForDecision() {
        do {
            System.out.println("Y / N ?");
            String decision = scanner.nextLine().toLowerCase();
            if (decision.equals("y")) {
                return true;
            } else if (decision.equals("n")) {
                return false;
            }
        } while (true);
    }

    public String enterKey() {
        String key;
        Boolean isKeyCorrect;

        do {
            isKeyCorrect = true;
            System.out.print("Enter key: ");
            key = scanner.nextLine();
            try {
                if (key.isBlank() || key.isEmpty()) {
                    throw new IllegalArgumentException("Key cannon be empty or blank");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                isKeyCorrect = false;
            }
        } while (!isKeyCorrect);
        return key;
    }

    public String enterValue() {
        String value;
        Boolean isValueCorrect;

        do {
            isValueCorrect = true;
            System.out.print("Enter value: ");
            value = scanner.nextLine();
            try {
                if (value.isEmpty()) {
                    throw new IllegalArgumentException("Value can not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                isValueCorrect = false;
            }
        } while (!isValueCorrect);
        return value;
    }


    public String getMenuItem() {

        return scanner.nextLine();
    }
}
