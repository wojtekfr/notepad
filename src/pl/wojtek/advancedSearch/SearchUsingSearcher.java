package pl.wojtek.advancedSearch;

import pl.wojtek.Input;
import pl.wojtek.Notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchUsingSearcher {

    Map<String, Searcher> searchers = new HashMap<>();
    Notes notesForSearching;
    Input input = new Input(System.in);

    public void selectAdvancedSearchOperation() {
        boolean needToExit = false;
        do {
            System.out.println();
            System.out.println("Advanced search is an experimental function to practice different ways of adding searchers method");
            System.out.println("It includes predefined searchers created in different ways like lambda or streams. User can also add new searchers manually");
            System.out.println("It is not very functional, in practice better use simple search");
            System.out.println("Select:");
            System.out.println("1 - run all existing searchers");
            System.out.println("2 - show list of existing searchers");
            System.out.println("3 - add new searcher by key");
            System.out.println("4 - add new searcher by value");
            System.out.println("0 - exit to main menu");
            switch (input.getMenuItem()) {
                case "1":
                    runAllExistingSearchers();
                    break;
                case "2":
                    showSearchers();
                    break;
                case "3":
                    addNewSearcherByKey();
                    break;
                case "4":
                    addNewSearcherByValue();
                    break;
                case "0":
                    needToExit = true;
                    break;
            }
        } while (!needToExit);

    }


    public SearchUsingSearcher(Notes notesForSearching) {
        this.notesForSearching = notesForSearching;
        // predefiniowane wyszukiwarki są mało użyteczne, ale zostały dodane w celu przećwiczenia różnych sposobów ich tworzenia

        // dodaj wyszukiwarkę zdefiniowaną w osobnej klasie
        SearcherByKey searcherByKey = new SearcherByKey("tel");
        searchers.put("searcher from normal class", searcherByKey);

        // dodaj wyszukiwarkę zdefiniowaną w klasie anonimowej
        searchers.put("searcher from anonymus class", new Searcher() {

            @Override
            public List<String> findNotes(Notes notes) {
                List<String> foundKeys = new ArrayList<>();
                for (String keyToFind : notes.getNotes().keySet()) {
                    if (keyToFind.toLowerCase().contains("login")) {
                        foundKeys.add(keyToFind);
                    }
                }
                return foundKeys;
            }
        });

        // dodaj wyszukiwarkę zdefiniowaną przez wyrażenie lambda
        searchers.put("searcher from lambda", (notes1) -> {
            List<String> foundKeys = new ArrayList<>();
            for (String keyToFind : notes1.getNotes().keySet()) {
                if (keyToFind.toLowerCase().contains("wife")) {
                    foundKeys.add(keyToFind);
                }
            }
            return foundKeys;
        });

        // dodaj wyszukiwarkę zdefiniowaną jako strumień

        searchers.put("searcher from stream", notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("phone"))
                .filter(note -> note.contains("wife"))
                .collect(Collectors.toList()));

        // wyszukowarka jako strumień, dodatkowo zmieniająca wartość każdego wywiltrowanego elementu (na duże znaki)
        searchers.put("searcher from stream with mapping", notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("phone"))
                .filter(note -> note.contains("wife"))
                .map(note -> note.toUpperCase())
                .collect(Collectors.toList()));
    }

    public void runAllExistingSearchers() {
        // iteruje przez wszystkie wyszukiwarki wykonująć wyszukanie wg reguł zdefiniowanych w ich instancjach
        for (String searcherKey : searchers.keySet()) {
            Searcher searcher = searchers.get(searcherKey);
            List<String> searchResults = searcher.findNotes(notesForSearching);
            AdvancedSearchResultsPrinter advancedSearchResultsPrinter = new AdvancedSearchResultsPrinter(searcherKey, searchResults);
            advancedSearchResultsPrinter.printResults();
        }
    }

    public void showSearchers() {
        for (String key : searchers.keySet()) {
            System.out.println(key);
        }
    }

    // dodaje wyszukiwarki stwożone przez użytkownika. Nie są one tworzone na bazie osobnej klasy ale poprzez strumień
    public void addNewSearcherByKey() {
        Input input = new Input(System.in);
        try {
            String searcherKey = input.enterString("Enter searcher name");

            String searcherRule = input.enterString("Enter string to search for");

            searchers.put(searcherKey, notes -> notes.getNotes().keySet().stream()
                    .filter(note -> note.contains(searcherRule))
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewExistingSearcher(String key,Searcher searcher) {

        searchers.put(key, searcher);
    }

    public void addNewSearcherByValue() {
        Input input = new Input(System.in);
        try {
            String searcherKey = input.enterString("Enter searcher name");
            String searcherRule = input.enterString("Enter string to search for");

            searchers.put(searcherKey, notes -> notes.getNotes().values().stream()
                    .filter(note -> note.contains(searcherRule))
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}


