package pl.wojtek;

public class Main {
    public static Notes currentNotes;

    public Main() {

    }

    public static void main(String[] args) {
        Input input = new Input();
        Notes defaultNotes = new Notes();
        UserInterface userInterface = new UserInterface();

        currentNotes = defaultNotes;

        currentNotes.addNote("My telephone", "555 555 555");
        currentNotes.addNote("Wife birthday", "01.01.1980");
        userInterface.printNotes(currentNotes);

        Notes secondNotes = new Notes();
        currentNotes = secondNotes;
        userInterface.printNotes(currentNotes);


        currentNotes = defaultNotes;
        userInterface.printNotes(currentNotes);

        currentNotes.addNote(input.getNewKey(), input.getNewValue());
        userInterface.printNotes(currentNotes);


    }
}
