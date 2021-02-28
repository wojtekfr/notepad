package pl.wojtek;

public class NotesSetPrinter {

    public void printNotesSet(NotesSet notesSet){
        System.out.println();
        System.out.println("Existing notes sets: ");
        for (Notes notes : notesSet.getNotesList()) {
            if (notes == notesSet.getCurrentNotes()) {
                System.out.println(" >>> " + notes.getNotesName());
            } else {
                System.out.println("     " + notes.getNotesName());
            }
        }
        System.out.println();
    }
}