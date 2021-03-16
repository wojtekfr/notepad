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

public class Main {

    public static void main(String[] args) throws IOException {
        // tworzy niezbędne obiekty wykorzystywanych klas
        Input input = new Input();
        MenuPrinter menuPrinter = new MenuPrinter();

        // tworzy jedyną instancję NotesSet
        NotesSet notesSet = new NotesSet();

        // tworzy pierwszą instancję Notes - aplikacja musi mieć przynajmniej jedną
        Notes defaultNotes = new Notes("default");
        notesSet.addNotesToSet(defaultNotes);

        // iOOperations działa zawsze na całym NotesSet więc tworzony jest dopiero tutaj, podając notesSet w konstruktorze
        IOOperations iOOperations = new IOOperations(notesSet);

        // Dla prawidłowego działania funkcji pracujących na konkretnym zbiorze notatek i adnotacji (instancja obiektu Notes)
        // niezbędne jest zdefiniowanie bieżacych notatek.
        notesSet.setCurrentNotes(defaultNotes);

        // test data

        notesSet.getCurrentNotes().addNote("car plate", "WF112233");
        notesSet.getCurrentNotes().addNote("John's phone", "555 555 551");
        notesSet.getCurrentNotes().createNewAnnotation("test annotation");

        Notes newNoteSet = new Notes("second");
        notesSet.addNotesToSet(newNoteSet);
        notesSet.setCurrentNotes(newNoteSet);
        notesSet.getCurrentNotes().addNote("superbank login", "mylogin");
        notesSet.getCurrentNotes().addNote("my telephone", "555 555 555");
        notesSet.getCurrentNotes().addNote("wife birthday", "01.01.1980");
        notesSet.getCurrentNotes().addNote("wife phone", "555 555 666");
        notesSet.getCurrentNotes().addNote("stream", "note to be found using stream");
        notesSet.getCurrentNotes().createNewAnnotation("first annotation");
        notesSet.getCurrentNotes().createNewAnnotation("second annotation");


        // pętla z głównym menu
        boolean needToExit = false;
        do {
            menuPrinter.printMenu();
            switch (input.getMenuItem()) {
                case "1":
                    // tu mam zgryz. Żeby z maina wywołać metodę na konretnej instancji Notes, muszę najpierw pobrać tą instancję z notesSet
                    // pytanie czy to eleganckie rozwiązanie. Z poziomu maina pewnie byłoby lepiej mieć tą metodę w notesSet, która by przekazywała
                    // polecenie druku, niżej, do konkretnej instancji Notes. Ale to by powodowało koniecznośc posiadania w NotesSet zestawu metod,
                    // które nic nie robią, poza wywołaniem tej samej metody niżej (w Notes).
                    // Zrobiłem tak za to, w Notes dla Search i NotesPrinter, bo tam żeby wywołać drukowanie z main, trzeba by
                    // pobrać instancję Notes z NotesSet, a potem dla tego Notes, pobrać NotesPrinter - to już za długi łańcuszek.
                    // ale nie wygląda mi to na zgrabne rozwiązanie, więc pewnie jest lepszy sposób
                    notesSet.getCurrentNotes().printNotes();
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
                    System.out.println("Number of letter in all annotations: " +notesSet.getCurrentNotes().getNumberOfLettersInAllAnnotations());
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
                    notesSet.getCurrentNotes().selectSimpleSearchOperation();
                    break;
                case "14":
                    notesSet.getCurrentNotes().selectAdvancedSearchOperation();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);
    }
}