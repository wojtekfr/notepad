package pl.wojtek;

public class Main {
    public static Notes currentNotes;

    public Main() {

    }

    public static void main(String[] args) {
        Input input = new Input();
        Notes defaultNotes = new Notes("Default");
        Output output = new Output();

        currentNotes = defaultNotes;

        // test data
        currentNotes.addNote("My telephone", "555 555 555");
        currentNotes.addNote("Wife birthday", "01.01.1980");
        currentNotes.addNewAnnotation("test annotation");
        currentNotes.addNewAnnotation("ww");
        currentNotes.addNewAnnotation("cc");

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
                    currentNotes.updateNumberOfLettersinAllAnnotations();
                    System.out.println(currentNotes.getNumberOfLettersInAllAnnotations());
                    break;
                case "6":
                    currentNotes.changeName();
                    break;
                case "7":

                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);


    }
}
