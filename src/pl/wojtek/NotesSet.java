package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class NotesSet {
    private List<Notes> notesSet = new ArrayList<Notes>();
    private Notes currentNotes;
    Input input = new Input();
    NotesSetPrinter notesSetPrinter = new NotesSetPrinter();

    public NotesSet(Search search, IOOperations iOOperations) {
        this.search = search;
        this.iOOperations = iOOperations;
    }

    private Search search;
    private IOOperations iOOperations;

    public void updateCurrentNotes() {
        search.setNotesForSearching(currentNotes);
        iOOperations.setNotes(currentNotes);
    }

    public Notes getCurrentNotes() {
        return currentNotes;
    }

    public void setCurrentNotes(Notes currentNotes) {
        this.currentNotes = currentNotes;
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
        // TODO dodać sprawdzenie unikalności nazwy
        String newNotesSetName = input.enterString("Enter name of notes set");
        Notes newNoteSet = new Notes(newNotesSetName);
        notesSet.add(newNoteSet);
        System.out.println(newNotesSetName + " added");
    }

    public void changeActiveNotesSet() {
        notesSetPrinter.printNotesSet(this);
        String lookedNotesSetName = input.enterString("new notes set name");
        Notes foundNotes = findNotesSet(lookedNotesSetName);
        if (foundNotes != null) {
            currentNotes = foundNotes;
            updateCurrentNotes();
            System.out.println("Notes set chaned to " + currentNotes.getNotesName());
        } else
            System.out.println("No such notes found");
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
    }
}