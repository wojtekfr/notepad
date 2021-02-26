package pl.wojtek;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Search search = new Search();
        IOOperations iOOperations = new IOOperations();
        Input input = new Input();
        Output output = new Output();
        Crawler crawler = new Crawler();

        NotesSet notesSet = new NotesSet(search, iOOperations);
        Notes defaultNotes = new Notes("Default");
        notesSet.addNotesToSet(defaultNotes);
        //TODO połączyć w jedną metodę
        notesSet.setCurrentNotes(defaultNotes);
        notesSet.updateCurrentNotes();

        // test data
        notesSet.getCurrentNotes().addNote("inna notka", "bardzo gupia");
        notesSet.getCurrentNotes().addNote("My telephone", "555 555 555");
        notesSet.getCurrentNotes().addNote("Wife birthday", "01.01.1980");
        notesSet.getCurrentNotes().addNote("inn", "wwww");
        notesSet.getCurrentNotes().addNote("wife inna tel", "podwójna");
        notesSet.getCurrentNotes().addNote("strumien", "ze strumienia");
        notesSet.getCurrentNotes().createNewAnnotation("super gupia anotacja");
        notesSet.getCurrentNotes().createNewAnnotation("test annotation");
        notesSet.getCurrentNotes().createNewAnnotation("ww");
        notesSet.getCurrentNotes().createNewAnnotation("cc");

        // main loop
        Boolean needToExit = false;
        do {
            output.showMenu();
            switch (input.getMenuItem()) {
                case "1":
                    output.printNotes(notesSet.getCurrentNotes());
                    break;
                case "2":
                    notesSet.getCurrentNotes().createNewNote();
                    break;
                case "3":
                    notesSet.getCurrentNotes().editNote();
                    break;
                case "4":
                    notesSet.getCurrentNotes().removeNote();
                    break;
                case "5":
                    notesSet.getCurrentNotes().createNewAnnotation();
                    break;
                case "6":
                    notesSet.getCurrentNotes().updateNumberOfLettersinAllAnnotations();
                    System.out.println(notesSet.getCurrentNotes().getNumberOfLettersInAllAnnotations());
                    break;
                case "7":
                    notesSet.getCurrentNotes().changeName();
                    break;
                case "8":
                    notesSet.changeActiveNotesSet();
                    break;
                case "9":
                    notesSet.newNotesSet();
                    break;
                case "10":
                    notesSet.removeNotesSet();
                case "11":
                    iOOperations.save();
                    break;
                case "12":
                    iOOperations.load();
                    break;
                case "13":
                    crawler.crawl();
                    break;
                case "14":
                    search.searchAll();
                    break;
                case "15":
                    search.showSearchers();
                    break;
                case "16":
                    search.addNewSearcherByKey();
                    break;
                case "17":
                    search.addNewSearcherByValue();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);


    }

}
