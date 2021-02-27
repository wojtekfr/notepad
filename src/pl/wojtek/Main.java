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
        IOOperations iOOperations = new IOOperations();
        Input input = new Input();
        MenuPrinter menuPrinter = new MenuPrinter();
        NotesPrinter notesPrinter = new NotesPrinter();

        NotesSet notesSet = new NotesSet(search, iOOperations);
        Notes defaultNotes = new Notes("Default");
        notesSet.addNotesToSet(defaultNotes);
        notesSet.setCurrentNotes(defaultNotes);

        // test data
        notesSet.getCurrentNotes().addNote("inna notka", "treść inej notatki");
        notesSet.getCurrentNotes().addNote("My telephone", "555 555 555");
        notesSet.getCurrentNotes().addNote("Wife birthday", "01.01.1980");
        notesSet.getCurrentNotes().addNote("inn", "wwww");
        notesSet.getCurrentNotes().addNote("wife inna tel", "podwójna");
        notesSet.getCurrentNotes().addNote("strumien", "ze strumienia");
        notesSet.getCurrentNotes().createNewAnnotation("super głupia anotacja");
        notesSet.getCurrentNotes().createNewAnnotation("test annotation");
        notesSet.getCurrentNotes().createNewAnnotation("ww");
        notesSet.getCurrentNotes().createNewAnnotation("cc");

        // main loop
        boolean needToExit = false;
        do {
            menuPrinter.printMenu();
            switch (input.getMenuItem()) {
                case "1":
                    notesPrinter.printNotes(notesSet.getCurrentNotes());
                    break;
                case "2":
                    notesSet.getCurrentNotes().addNewNoteEnteredByUser();
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
                    // mało użyteczne ale dodane na potrzeby ćwiczenia tworzenia testów
                    notesSet.getCurrentNotes().updateNumberOfLettersInAllAnnotations();
                    System.out.println(notesSet.getCurrentNotes().getNumberOfLettersInAllAnnotations());
                    break;
                case "7":
                    notesSet.changeName();
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
