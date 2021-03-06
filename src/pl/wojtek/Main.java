package pl.wojtek;

import java.io.IOException;
import java.util.Scanner;

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
        Input input = new Input(System.in);
        Scanner scanner = new Scanner(System.in);
        MenuPrinter menuPrinter = new MenuPrinter();

        // tworzy jedyną instancję NotesSet
        NotesSet notesSet = new NotesSet();

        // tworzy pierwszą instancję Notes - aplikacja musi mieć przynajmniej jedną
        Notes defaultNotes = new Notes("Default");
        notesSet.addNotesToSet(defaultNotes);

        // iOOperations działa zawsze na całym NotesSet więc tworzony jest dopiero tutaj, podając notesSet w konstruktorze
        IOOperations iOOperations = new IOOperations(notesSet);

        // Dla prawidłowego działania funkcji pracujących na konkretnym zbiorze notatek i adnotacji (instancja obiektu Notes)
        // niezbędne jest zdefiniowanie bieżacych notatek.
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
            switch (input.getMenuItem(scanner)) {
                case "1":
                    notesSet.getCurrentNotes().printNotes();
                    break;
                case "2":
                    // tu mam zgryz. Żeby z maina wywołać metodę na konretnej instancji Notes, muszę najpierw pobrać tą instancję z notesSet
                    // pytanie czy to eleganckie rozwiązanie. Z poziomu maina pewnie byłoby lepiej mieć tą metodę w notesSet, która by przekazywała
                    // polecenie druku, niżej, do konkretnej instancji Notes. Ale to by powodowało koniecznośc posiadania w notesSet zestawu metod,
                    // które nic nie robią, poza wywołaniem tej samej metody niżej (w Notes).
                    // Zrobiłem tak za to, w Notes dla Search i NotesPrinter, bo tam żeby wywołać drukowanie z main, trzeba by
                    // pobrać instancję Notes z NotesSet, a potem dla tego Notes, pobrać NotesPrinter - to już za długi łańcuszek.
                    // ale nie wygląda mi to na zgrabne rozwiązanie, więc pewnie jest lepszy sposób
                    notesSet.getCurrentNotes().addNewNoteEnteredByUser();
                    break;
                case "3":
                    notesSet.getCurrentNotes().editNote();
                    break;
                case "4":
                    notesSet.getCurrentNotes().removeNote();
                    break;
                case "5":
                    notesSet.getCurrentNotes().createNewAnnotation(input);
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
                    notesSet.getCurrentNotes().searchAll();
                    break;
                case "14":
                    notesSet.getCurrentNotes().showSearchers();
                    break;
                case "15":
                    notesSet.getCurrentNotes().addNewSearcherByKey();
                    break;
                case "16":
                    notesSet.getCurrentNotes().addNewSearcherByValue();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    needToExit = true;
            }
        } while (!needToExit);
    }
}