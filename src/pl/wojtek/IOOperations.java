package pl.wojtek;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOOperations {
    Notes notes;
    String token;

    public IOOperations(Notes notes) {
        this.notes = notes;
    }


    public void save() throws Exception {
        File file = new File("notes.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, false));
        printWriter.println("n%" + notes.getNotesName());
        for (String note : notes.getNotes().keySet()) {
            printWriter.println("o%" + note + "%" + notes.getSpecificNote(note));
        }

        for (Annotation annotation : notes.getAnnotations()) {
            printWriter.println("a%" + annotation.getAnnotation() + "%" + annotation.getDateOfAdding());
        }
        printWriter.close();
    }


    public void load() throws Exception {
        File file = new File("notes.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            token = scanner.nextLine();
            if (token.charAt(0) == 'n') {
                try {
                    readNotesName();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (token.charAt(0) == 'o') {
                try {
                    readNote();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (token.charAt(0) == 'a') {
                try {
                    readAnnotation();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    public void readNotesName() {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 2) {
            throw new IllegalArgumentException("Incorrect name line found");
        }
        notes.setNotesName(token.substring(2));
    }


    public void readNote() {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 3) {
            throw new IllegalArgumentException("Incorrect note line found");
        }
        notes.addNote(tokenSplitted[1], tokenSplitted[2]);
    }


    public void readAnnotation() {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 3) {
            throw new IllegalArgumentException("Annotation line is not correct");
        }
        notes.addAnnotation(new Annotation(tokenSplitted[1], tokenSplitted[2]));
    }

    public void setNotes(Notes notes) {
    }
}