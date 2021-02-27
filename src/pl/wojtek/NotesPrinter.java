package pl.wojtek;

public class NotesPrinter {
    public void printNotes(Notes notes) {
        System.out.println("Your notes in " + notes.getNotesName() + " :");
        for (String note : notes.getNotes().keySet()) {
            System.out.println(note + "   " + notes.getSpecificNote(note));
        }
        System.out.println("Annotations:");
        notes.getAnnotations().forEach(annotation -> System.out.println(annotation.toString()));
    }
}
