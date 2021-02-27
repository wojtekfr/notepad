package pl.wojtek;

import java.io.*;
import java.util.Scanner;

public class IOOperations {
    Notes notes;
    String token;
    Input input = new Input();

    public void save() throws IOException {
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
        System.out.println(notes.getNotesName() + " was saved to file notes.txt");
    }

    public void load() {
        System.out.println(notes.getNotesName() + " will be replaced with content of file notes.txt");
        if (input.askForDecision()) {
            File file = new File("notes.txt");
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    token = scanner.nextLine();
                    if (token.charAt(0) == 'n') {
                        try {
                            readNotesName();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (token.charAt(0) == 'o') {
                        try {
                            readNote();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (token.charAt(0) == 'a') {
                        try {
                            readAnnotation();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                scanner.close();
                System.out.println("file loaded");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void readNotesName() throws IOException {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 2) {
            throw new IOException("Incorrect name line found");
        }
        notes.setNotesName(token.substring(2));
    }


    public void readNote() throws IOException {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 3) {
            throw new IOException("Incorrect note line found");
        }
        notes.addNote(tokenSplitted[1], tokenSplitted[2]);
    }


    public void readAnnotation() throws IOException {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 3) {
            throw new IOException("Annotation line is not correct");
        }
        notes.addAnnotation(new Annotation(tokenSplitted[1], tokenSplitted[2]));
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}