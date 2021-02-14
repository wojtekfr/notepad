package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Notes currentNotes;
    public static List<Notes> notesSet = new ArrayList<Notes>();

    public Main() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        Input input = new Input();
        Output output = new Output();

        Notes defaultNotes = new Notes("Default");
        notesSet.add(defaultNotes);
        currentNotes = notesSet.get(0);


        currentNotes = defaultNotes;

        // test data
        currentNotes.addNote("My telephone", "555 555 555");
        currentNotes.addNote("Wife birthday", "01.01.1980");
        currentNotes.createNewAnnotation("test annotation");
        currentNotes.createNewAnnotation("ww");
        currentNotes.createNewAnnotation("cc");

        // main loop
        Boolean needToExit = false;
        do {
            output.showMenu();
            switch (input.getMenuItem()) {
                case "1":
                    output.printNotes(currentNotes);
                    break;
                case "2":
                    currentNotes.createNewNote();
                    break;
                case "3":
                    currentNotes.editNote();
                    break;
                case "4":
                    currentNotes.removeNote();
                    break;
                case "5":
                    currentNotes.createNewAnnotation();
                    break;
                case "6":
                    currentNotes.updateNumberOfLettersinAllAnnotations();
                    System.out.println(currentNotes.getNumberOfLettersInAllAnnotations());
                    break;
                case "7":
                    currentNotes.changeName();
                    break;
                case "8":
                    main.changeActiveNotesSet();
                    break;
                case "9":
                    main.newNotesSet();
                    break;
                case "10":
                    main.removeNotesSet();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);


    }

    // Do zastanowienia czy notesSet nie powinien być klasą a nie tylko listą w main
    // teraz procedury jego dotyczące muszę umieszczać w main, a procedury dotyczące
    // pozostałych elementów są w odpowiednich klasach
    public void newNotesSet() {
        // TODO dodać sprawdzenie unikalności nazwy
        Input input = new Input();
        String newNotesSetName = input.enterString("Enter name of notes set");
        Notes newNoteSet = new Notes(newNotesSetName);
        notesSet.add(newNoteSet);
        System.out.println(newNotesSetName + " added");
    }

    public void changeActiveNotesSet() {
        Input input = new Input();
        System.out.println("Existing notes sets: ");
        for (Notes notes : notesSet) {
            if (notes == currentNotes) {
                System.out.println(" >>> " + notes.getNotesName());
            } else {
                System.out.println("     " + notes.getNotesName());
            }
        }

        System.out.println();
        String lookedNotesSetName = input.enterString("new notes set name");

        // TODO wydzielić sprawdzanie czy set istnieje do osobnej metody
        boolean doesNoteSetExist = false;
        Notes lookedNotes = null;
        for (Notes notes : notesSet) {
            if (notes.getNotesName().equals(lookedNotesSetName)) {
                doesNoteSetExist = true;
                lookedNotes = notes;
            }
        }
        if (doesNoteSetExist) {
            currentNotes = lookedNotes;
            System.out.println("Notes set chaned to " + lookedNotes.getNotesName());
        } else {
            System.out.println("No such notes found");
        }
    }

    public void removeNotesSet() {
        System.out.println();
        Input input = new Input();
        System.out.println("Existing notes sets: ");
        for (Notes notes : notesSet) {
            if (notes == currentNotes) {
                System.out.println(" >>> " + notes.getNotesName());
            } else {
                System.out.println("     " + notes.getNotesName());
            }
        }
        System.out.println();
        String toRemoveNotesSetName = input.enterString("name of note set to remove");
        boolean doesNoteSetExist = false;
        Notes lookedNotes = null;
        for (Notes notes : notesSet) {
            if (notes.getNotesName().equals(toRemoveNotesSetName)) {
                doesNoteSetExist = true;
                lookedNotes = notes;
            }
        }
        if (doesNoteSetExist) {
            if (currentNotes == lookedNotes) {
                System.out.println("You can't remove current notes set");
            } else {
                notesSet.remove(lookedNotes);
                System.out.println(toRemoveNotesSetName + " removed");
            }
        } else {
            System.out.println("No such notes found");
        }
    }
}
