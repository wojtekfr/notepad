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
        currentNotes.newAnnotation("test annotation");

        // main loop
        Boolean needToExit = false;
        do {
            output.showMenu();
            switch (input.getMenuItem()){
                case "1":
                    output.printNotes(currentNotes);
                    break;
                case "2":
                    System.out.println("not yet implemented");
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);





    }
}
