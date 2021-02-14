package pl.wojtek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notes {


    private String notesName;
    private Map<String, String> notes;
    private ArrayList<Annotation> annotations;
    Input input = new Input();
    private int numberOfLettersInAllAnnotations;

    public Input getInput() {
        return input;
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
