package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class NotesSet {
    private List<Notes> notesSet = new ArrayList<Notes>();
    private Notes currentNotes;
    Input input = new Input();
    NotesSetPrinter notesSetPrinter = new NotesSetPrinter();
    private Search search;
    private IOOperations iOOperations;

    public NotesSet(Search search, IOOperations iOOperations) {
        this.search = search;
        this.iOOperations = iOOperations;
    }

    public Notes getCurrentNotes() {
        return currentNotes;
    }

    public void setCurrentNotes(Notes currentNotes) {
        this.currentNotes = currentNotes;
        search.setNotesForSearching(currentNotes);
        iOOperations.setNotes(currentNotes);
    }

    public List<Notes> getNotesSet() {
        return notesSet;
    }

    public void setNotesSet(List<Notes> notesSet) {
        this.notesSet = notesSet;
    }

    public void addNotesToSet(Notes notes) {
        notesSet.add(notes);
    }

    public void removeNotesFromSet(Notes notes) {
        notesSet.remove(notes);
    }

    public void newNotesSet() {
        try {
            String newNotesSetName = input.enterString("Enter name of notes set");
            if (doesNoteSetExist(newNotesSetName)) {
                System.out.println("Such name already exists");
            } else {
                Notes newNoteSet = new Notes(newNotesSetName);
                notesSet.add(newNoteSet);
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
                return;
            } else {
                if (doesNoteSetExist(name)) {
                    System.out.println("Such notes set already exists");
                } else {
                    currentNotes.setNotesName(name);
                }
            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    public boolean doesNoteSetExist(String proposedNotesSetName) {

        for (Notes notes : notesSet) {
            if (notes.getNotesName().equals(proposedNotesSetName)) {
                return true;
            }
        }
        return false;
    }

    public Notes findNotesSet(String lookedNotesSetName) {
        for (Notes notes : notesSet) {
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
                notesSet.remove(foundNotes);
                System.out.println(toRemoveNotesSetName + " removed");
            } else {
                System.out.println("No such notes found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}