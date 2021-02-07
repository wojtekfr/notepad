package pl.wojtek;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String getNewKey() {
        String key;
        Boolean isKeyCorrect;

        do {
            isKeyCorrect = true;
            System.out.print("Enter new key: ");
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

    public String getNewValue() {
        String value;
        Boolean isValueCorrect;

        do {
            isValueCorrect = true;
            System.out.print("Enter new value: ");
            value = scanner.nextLine();
            try {
                if (value.isEmpty()) {
                    throw new IllegalArgumentException("Value cannon be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                isValueCorrect = false;
            }
        } while (!isValueCorrect);
        return value;


    }

}
