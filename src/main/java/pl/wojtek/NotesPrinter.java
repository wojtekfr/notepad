package pl.wojtek;

public class NotesPrinter {
    Notes notesForPrinting;

    public NotesPrinter(Notes notesForPrinting) {
        this.notesForPrinting = notesForPrinting;
    }

    public void setNotesForPrinting(Notes notesForPrinting) {
        this.notesForPrinting = notesForPrinting;
    }



    public void printNotes() {
        System.out.println("Your notes in " + notesForPrinting.getNotesName() + " :");
        for (String note : notesForPrinting.getNotes().keySet()) {
            System.out.println(note + "   " + notesForPrinting.getSpecificNote(note));
        }
        System.out.println("Annotations:");
        notesForPrinting.getAnnotations().forEach(annotation -> System.out.println(annotation.toString()));
    }
}
