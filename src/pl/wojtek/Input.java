package pl.wojtek;

import java.io.InputStream;
import java.util.Scanner;

public class Input {
    Scanner scanner;

    //TODO do zastanowienia czy definiowanie inputstream jest potrzebne, chyba jednak tego nigdzie w testach nie wykorzystam
    public Input(InputStream in) {
        scanner = new Scanner(System.in);
    }



    // znak % jest używany jako znacznik przy zapisywaniu plików więc nie jest dopuszczalny do wproadzenia
    String notAllowedSymbolMessage = "% symbol is not allowed";

    // uniwersalna metoda do pobierania od użytkownika dowolnych wartości tekstowych
    public String enterString(String message) {
        System.out.print("Enter " + message + " : ");
        String enteredString = scanner.nextLine();
        if (doesStringIncludesNotAllowedSymbol(enteredString)) {
            throw new IllegalArgumentException(notAllowedSymbolMessage);
        }
        return enteredString;
    }

    // metoda do pytania użytkownika o potwierdzenie akcji
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

    public boolean doesStringIncludesNotAllowedSymbol(String stringForChecking) {
        if (stringForChecking.contains("%")) {
            return true;
        } else {
            return false;
        }
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
                if (doesStringIncludesNotAllowedSymbol(key)) {
                    throw new IllegalArgumentException(notAllowedSymbolMessage);
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
                if (doesStringIncludesNotAllowedSymbol(value)) {
                    throw new IllegalArgumentException(notAllowedSymbolMessage);
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
