package pl.wojtek;

public class NotesSetPrinter {

    public void printNotesSet(NotesSets notesSets){
        System.out.println();
        System.out.println("Existing notes sets: ");
        for (Notes notes : notesSets.getNotesList()) {
            if (notes == notesSets.getCurrentNotes()) {
                System.out.println(" >>> " + notes.getNotesName());
            } else {
                System.out.println("     " + notes.getNotesName());
            }
        }
        System.out.println();
    }
}