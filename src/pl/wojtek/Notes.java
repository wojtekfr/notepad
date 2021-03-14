package pl.wojtek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notes {

    private String notesName;
    private Map<String, String> notes;
    private ArrayList<Annotation> annotations;
    private int numberOfLettersInAllAnnotations;
    private Input input = new Input(System.in);
    private NotesPrinter notesPrinter = new NotesPrinter(this);
    private SearchUsingSearcher searchUsingSearcher = new SearchUsingSearcher(this);
    private SimpleSearch simpleSearch = new SimpleSearch(this);


    // te 3 setery tylko na potrzeby podmiany inputa, printera i searcha na mocki w testach
    public void setInput(Input input) {
        this.input = input;
    }

    public void setSearch(SearchUsingSearcher searchUsingSearcher) {
        this.searchUsingSearcher = searchUsingSearcher;
    }

    public void setNotesPrinter(NotesPrinter notesPrinter) {
        this.notesPrinter = notesPrinter;
    }

    public Notes(String notesName) {
        this.notesName = notesName;
        annotations = new ArrayList<>();
        notes = new HashMap<String, String>();
    }

    public int getNumberOfLettersInAllAnnotations() {
        return numberOfLettersInAllAnnotations;
    }


    public void updateNumberOfLettersInAllAnnotations() {
        int numberOfLetters = 0;
        for (Annotation annotation : annotations) {
            numberOfLetters += annotation.calculateLetters();
        }
        this.numberOfLettersInAllAnnotations = numberOfLetters;
    }

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    public void createNewAnnotation(Input input) {
        try {
            Annotation annotation = new Annotation(input.enterString("annotation"));
            annotations.add(annotation);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewAnnotation(String annotationText) {
        if (annotationText.contains("%")) {
            throw new IllegalArgumentException();
        }
        Annotation annotation = new Annotation(annotationText);
        annotations.add(annotation);

    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    public void addNewNoteEnteredByUser() {
        notes.put(input.enterKey(), input.enterValue());
    }

    public void addNote(String key, String value) {
        notes.put(key, value);
    }

    public void removeNote() {
        System.out.println("Enter key for notes to be removed");
        String key = input.enterKey();
        if (!this.checkIfNoteExists(key)) {
            System.out.println("Key not found");
            return;
        }
        System.out.println("Do you really whant to remove note: " + key + " " + notes.get(key));
        boolean decision = input.askForDecision();
        if (decision) {
            notes.remove(key);
            System.out.println("Note removed");
        } else {
            System.out.println("Note not removed");
        }
    }

    public String getSpecificNote(String key) {
        return notes.get(key);
    }


    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }


    public void editNote() {
        String key = input.enterKey();
        if (this.checkIfNoteExists(key)) {
            notes.put(key, input.enterValue());
        } else {
            System.out.println("Key not found");
        }
    }


    public boolean checkIfNoteExists(String key) {
        if (notes.keySet().contains(key)) {
            return true;
        }
        return false;
    }

    // metody poniżej służą tylko skaskadowaniu poleceń dotyczących wyszukiwania lub drukowania do instancji Search albo NotesPrinter
    public void runAllExistingSearchers() {
        searchUsingSearcher.searchAll();
    }

    public void showSearchers() {
        searchUsingSearcher.showSearchers();
    }

    public void addNewSearcherByKey() {
        searchUsingSearcher.addNewSearcherByKey();
    }

    public void addNewSearcherByValue() {
        searchUsingSearcher.addNewSearcherByValue();
    }

    public void printNotes() {
        notesPrinter.printNotes();
    }


    public void executeSimpleSearch() {
        simpleSearch.executeSimpleSearch();
    }
}
