package pl.wojtek;

public class Output {

    public void printNotes(Notes notes) {
        System.out.println("Your notes in " + notes.getNotesName() + " :");
        for (String note : notes.getNotes().keySet()) {
            System.out.println(note + "   " + notes.getSpecificNote(note));
        }
        System.out.println("Annotations:");
        for (Annotation annotation:notes.getAnnotations()){
            System.out.println(annotation.toString());
        }
    }

    public void showMenu() {
        System.out.println();
        System.out.println("Please select:");
        System.out.println("1 - show notes");
        System.out.println("2 - new note");
        System.out.println("3 - edit existing note");
        System.out.println("4 - remove note");
        System.out.println("5 - change name of current notes set");
        System.out.println("6 - change active note set");
        System.out.println("7 - add new note set");
        System.out.println("8 - remove note set");
        System.out.println("0 - exit");
    }


}
