package pl.wojtek;

import java.io.IOException;

// celem aplikacji jest przechowywanie notatek w dwóch formach - klucza i wartości (np do notowania ważnych dat)
// oraz luźnych adnotacji (zwykłe pole tekstowe, dla niego dodatkowo dodawana jest automatycznie data dodana)
// architektura aplikacji od dołu:
// Annotation - tekst wraz z datą dodania. Reprezentowana przez obiekt klasy Annotation
// Note - para ciągów value i key. Reprezentowana przez element w Mapie
// Notes - obiekt klasy Notes:
//      grupuje obiekty typu Annotation (w ramach listy_Annotations)
//      zawiera mapę Notes przechowującą Note
//      posiada ciąg NotesName
// NotesSet - główny obiekt, może istnieć tylko jedna jego instancja.
//      Pozwala przechowywać wiele notatek (Notes)
//      Przechowuje, który obiekt Notes jest aktywny (na nim wykonywana jest większość operacji)
//      W jego ramach tworzone są obiekty klas wykonujących operacje na nim (Search i NotesPrinter)
//      Search i NotesSetPrinter muszą wiedzieć jaki Notes jest aktywny - więc są uaktualniane po każdej zmianie aktywnych Notes



public class Main {

    public static void main(String[] args) throws IOException {
        Search search = new Search();

        Input input = new Input();
        MenuPrinter menuPrinter = new MenuPrinter();
        NotesPrinter notesPrinter = new NotesPrinter();

        NotesSets notesSets = new NotesSets(search);
        Notes defaultNotes = new Notes("Default");
        notesSets.addNotesToSet(defaultNotes);
        IOOperations iOOperations = new IOOperations(notesSets);
        notesSets.setCurrentNotes(defaultNotes);

        // test data
        notesSets.getCurrentNotes().addNote("inna notka", "treść inej notatki");
        notesSets.getCurrentNotes().addNote("My telephone", "555 555 555");
        notesSets.getCurrentNotes().addNote("Wife birthday", "01.01.1980");
        notesSets.getCurrentNotes().addNote("inn", "wwww");
        notesSets.getCurrentNotes().addNote("wife inna tel", "podwójna");
        notesSets.getCurrentNotes().addNote("strumien", "ze strumienia");
        notesSets.getCurrentNotes().createNewAnnotation("super głupia anotacja");
        notesSets.getCurrentNotes().createNewAnnotation("test annotation");
        notesSets.getCurrentNotes().createNewAnnotation("ww");
        notesSets.getCurrentNotes().createNewAnnotation("cc");

        // main loop
        boolean needToExit = false;
        do {
            menuPrinter.printMenu();
            switch (input.getMenuItem()) {
                case "1":
                    notesPrinter.printNotes(notesSets.getCurrentNotes());
                    break;
                case "2":
                    notesSets.getCurrentNotes().addNewNoteEnteredByUser();
                    break;
                case "3":
                    notesSets.getCurrentNotes().editNote();
                    break;
                case "4":
                    notesSets.getCurrentNotes().removeNote();
                    break;
                case "5":
                    notesSets.getCurrentNotes().createNewAnnotation();
                    break;
                case "6":
                    // mało użyteczne ale dodane na potrzeby ćwiczenia tworzenia testów
                    notesSets.getCurrentNotes().updateNumberOfLettersInAllAnnotations();
                    System.out.println(notesSets.getCurrentNotes().getNumberOfLettersInAllAnnotations());
                    break;
                case "7":
                    notesSets.changeName();
                    break;
                case "8":
                    notesSets.changeActiveNotesSet();
                    break;
                case "9":
                    notesSets.newNotesSet();
                    break;
                case "10":
                    notesSets.removeNotesSet();
                case "11":
                    iOOperations.save();
                    break;
                case "12":
                    iOOperations.load();
                    break;
                case "13":
                    search.searchAll();
                    break;
                case "14":
                    search.showSearchers();
                    break;
                case "15":
                    search.addNewSearcherByKey();
                    break;
                case "16":
                    search.addNewSearcherByValue();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);
    }
}
