package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class NotesSet {
    private final List<Notes> notesList = new ArrayList<>();
    private Notes currentNotes;
    private final Input input = new Input();
    private final NotesSetPrinter notesSetPrinter = new NotesSetPrinter();

    public Notes getCurrentNotes() {
        return currentNotes;
    }

    public void setCurrentNotes(Notes currentNotes) {
        this.currentNotes = currentNotes;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }


    public void addNotesToSet(Notes notes) {
        notesList.add(notes);
    }

    public void newNotesSet() {
        try {
            String newNotesSetName = input.enterString("Enter name of notes set");
            if (doesNoteSetExist(newNotesSetName)) {
                System.out.println("Such name already exists");
            } else {
                Notes newNoteSet = new Notes(newNotesSetName);
                notesList.add(newNoteSet);
                System.out.println(newNotesSetName + " added");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeActiveNotesSet() {
        notesSetPrinter.printNotesSet(this);
        try {
            String lookedNotesSetName = input.enterString("new notes set name");
            Notes foundNotes = findNotesSet(lookedNotesSetName);
            if (foundNotes != null) {
                setCurrentNotes(foundNotes);
                System.out.println("Notes set changed to " + currentNotes.getNotesName());
            } else
                System.out.println("No such notes found");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeName() {
        System.out.println("Current name: " + currentNotes.getNotesName());
        try {
            String name = input.enterString("new name");
            if (name.isEmpty()) {
                System.out.println("Name not changed");
            } else {
                if (doesNoteSetExist(name)) {
                    System.out.println("Such notes set already exists");
                } else {
                    currentNotes.setNotesName(name);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean doesNoteSetExist(String proposedNotesSetName) {
        for (Notes notes : notesList) {
            if (notes.getNotesName().equals(proposedNotesSetName)) {
                return true;
            }
        }
        return false;
    }

    public Notes findNotesSet(String lookedNotesSetName) {
        for (Notes notes : notesList) {
            if (notes.getNotesName().equals(lookedNotesSetName)) {
                return notes;
            }
        }
        return null;
    }

    public void removeNotesSet() {
        notesSetPrinter.printNotesSet(this);
        try {
            String toRemoveNotesSetName = input.enterString("name of note set to remove");

            Notes foundNotes = findNotesSet(toRemoveNotesSetName);

            if (currentNotes == foundNotes) {
                System.out.println("You can't remove current notes set");
            } else if (foundNotes != null) {
                notesList.remove(foundNotes);
                System.out.println(toRemoveNotesSetName + " removed");
            } else {
                System.out.println("No such notes found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}