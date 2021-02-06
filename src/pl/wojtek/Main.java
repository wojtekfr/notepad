package pl.wojtek;

public class Main {
    public static Notes currentNotes;
    public Main() {

    }

    public static void main(String[] args) {
        Notes defaultNotes = new Notes();
        UserInterface userInterface = new UserInterface();

        currentNotes = defaultNotes;

        currentNotes.addNote("My telephone", "555 555 555");
        currentNotes.addNote("Wife birthday", "01.01.1980");
        userInterface.printNotes(defaultNotes);


    }
}
