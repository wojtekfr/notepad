package pl.wojtek;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Notes {


    private String notesName;
    private Map<String, String> notes;
    private ArrayList<Annotation> annotations;
    Input input = new Input();
    private int numberOfLettersInAllAnnotations;

    public void save() throws Exception {
        File file = new File("notes.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, false));
        printWriter.println("n%" + this.getNotesName());
        for (String note : this.getNotes().keySet()) {
            printWriter.println("o%" + note + "%" + this.getSpecificNote(note));
        }

        for (Annotation annotation : this.getAnnotations()) {
            printWriter.println("a%" + annotation.getAnnotation() + "%" + annotation.getDateOfAdding());
        }
        printWriter.close();
    }


    public void load() throws Exception {
        File file = new File("notes.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String token = scanner.nextLine();
            if (token.charAt(0) == 'n') {
                String[] tokenSplitted = token.split("%");
                if (tokenSplitted.length != 2) {
                    System.out.println("Incorrect name line found");

                } else {
                    notesName = token.substring(2);
                }

            }
            if (token.charAt(0) == 'o') {
                String[] tokenSplitted = token.split("%");
                if (tokenSplitted.length != 3) {
                    System.out.println("Incorrect note line found");
                } else {
                    notes.put(tokenSplitted[1], tokenSplitted[2]);
                }
            }
            if (token.charAt(0) == 'a') {
                try {
                    readAnnotation(token);
                } catch (Exception e) {
                    System.out.println("Incorrect annotation line found");
                }
            }
        }
        scanner.close();
    }

    public void readAnnotation(String token) {
        String[] tokenSplitted = token.split("%");
        if (tokenSplitted.length != 3) {
            throw new IllegalArgumentException("Annotation line is not correct");
        }
        Annotation annotation = new Annotation(tokenSplitted[1], tokenSplitted[2]);
        annotations.add(annotation);
    }

    public void changeName() {
        System.out.println("Current name: " + this.getNotesName());
        String name = input.enterString("new name");
        if (name.isEmpty()) {
            System.out.println("Name not changed");
            return;
        } else {
            this.setNotesName(name);
        }
    }

    public int getNumberOfLettersInAllAnnotations() {
        return numberOfLettersInAllAnnotations;
    }


    public void updateNumberOfLettersinAllAnnotations() {
        int numberOfLetters = 0;
        for (Annotation annotation : annotations) {
            numberOfLetters += annotation.calculateLetters();
        }
        this.numberOfLettersInAllAnnotations = numberOfLetters;
    }

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }


    public void createNewAnnotation() {
        Input input = new Input();
        Annotation annotation = new Annotation(input.enterString("annotation"));
        annotations.add(annotation);
    }

    public void createNewAnnotation(String annotationText) {
        Annotation annotation = new Annotation(annotationText);
        annotations.add(annotation);
    }


    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    public Notes(String notesName) {
        this.notesName = notesName;
        annotations = new ArrayList<>();
        notes = new HashMap<String, String>();
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    public void createNewNote() {
        Input input = new Input();
        notes.put(input.enterKey(), input.enterValue());
    }

    public void addNote(String key, String value) {
        notes.put(key, value);
    }

    public void removeNote() {
        System.out.println("Enter key for notes to be removed");
        String key = input.enterKey();
        if (!notes.keySet().contains(key)) {
            System.out.println("Key not found");
            return;
        }
        System.out.println("Do you really whant to remove note: " + key + " " + notes.get(key));
        boolean deceision = input.askForDecision();
        if (deceision) {
            notes.remove(key);
            System.out.println("Note removed");
        } else {
            System.out.println("Note not removed");
        }
    }

    public String getSpecificNote(String key) {
        return notes.get(key);
    }


    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }


    public void editNote() {
        String key = input.enterKey();
        if (!notes.keySet().contains(key)) {
            System.out.println("Key not found");
            return;
        }
        notes.put(key, input.enterValue());
    }
}
