package pl.wojtek;

import java.io.*;
import java.util.Scanner;

public class IOOperations {
    NotesSet notesSet;
    String token;
    Input input = new Input();
    File file = new File("notes.txt");
    String[] tokenSplitted;

    public IOOperations(NotesSet notesSet) {
        this.notesSet = notesSet;
    }

    public void save() throws IOException {
        System.out.println("Content of notes.txt will be replaced with current notes sets");
        if (input.askForDecision()) {

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, false));

            for (Notes notes : notesSet.getNotesList()) {
                printWriter.println("n%" + notes.getNotesName());
                for (String note : notes.getNotes().keySet()) {
                    printWriter.println("o%" + note + "%" + notes.getSpecificNote(note));
                }
                for (Annotation annotation : notes.getAnnotations()) {
                    printWriter.println("a%" + annotation.getAnnotation() + "%" + annotation.getDateOfAdding());
                }
            }
            printWriter.close();
            System.out.println("Notes sets where saved to file notes.txt");
        }
    }

    public void load()  {
        System.out.println("All current notes sets will be will be replaced with content of notes.txt");
        if (input.askForDecision()) {
            try {
                if (!checkIfFileContainsAnyNotesSet()) {
                    System.out.println("File notes.txt does not contain any correct notes sets");
                } else {
                    notesSet.getNotesList().clear();
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        token = scanner.nextLine();
                        tokenSplitted = token.split("%");
                        if (token.charAt(0) == 'n') {
                            try {
                                readNotesName();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (token.charAt(0) == 'o') {
                            try {
                                readNote();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (token.charAt(0) == 'a') {
                            try {
                                readAnnotation();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Incorrect line with no content found");
                        }
                    }
                    scanner.close();
                    System.out.println("file loaded");
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public boolean checkIfFileContainsAnyNotesSet() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            token = scanner.nextLine();
            tokenSplitted = token.split("%");
            if (token.charAt(0) == 'n') {
                try {
                    readNotesName();
                    return true;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }

    public void readNotesName() throws IOException {
        if (tokenSplitted.length != 2) {
            throw new IOException("Incorrect name line found");
        }
        Notes notesToAdd = new Notes(token.substring(2));
        notesSet.addNotesToSet(notesToAdd);
        notesSet.setCurrentNotes(notesToAdd);
    }


    public void readNote() throws IOException {
        if (tokenSplitted.length != 3) {
            throw new IOException("Incorrect note line found");
        }
        notesSet.getCurrentNotes().addNote(tokenSplitted[1], tokenSplitted[2]);
    }


    public void readAnnotation() throws IOException {
        if (tokenSplitted.length != 3) {
            throw new IOException("Annotation line is not correct");
        }
        notesSet.getCurrentNotes().addAnnotation(new Annotation(tokenSplitted[1], tokenSplitted[2]));
    }
}