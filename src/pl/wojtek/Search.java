package pl.wojtek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Search {

    Map<String, Searcher> searchers = new HashMap<>();
    Notes notesForSearching;



    public Search(Notes notesForSearching) {
        this.notesForSearching = notesForSearching;
        // predefiniowane wyszukiwarki są mało użyteczne, ale zostały dodane w celu przećwiczenia różnych sposobów ich tworzenia

        // dodaj wyszukiwarkę zdefiniowaną w osobnej klasie
        SearcherByKey searcherByKey = new SearcherByKey("inn");
        searchers.put("searcher from normal class", searcherByKey);

        // dodaj wyszukiwarkę zdefiniowaną w klasie anonimowej
        searchers.put("searcher from anonymus class", new Searcher() {

            @Override
            public List<String> findNotes(Notes notes) {
                List<String> foundKeys = new ArrayList<>();
                for (String keyToFind : notes.getNotes().keySet()) {
                    if (keyToFind.toLowerCase().contains("wife")) {
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
                if (keyToFind.toLowerCase().contains("tel")) {
                    foundKeys.add(keyToFind);
                }
            }
            return foundKeys;
        });

        // dodaj wyszukiwarkę zdefiniowaną jako strumień

        searchers.put("searcher from stream", notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("tel"))
                .filter(note -> note.contains("wife"))
                .collect(Collectors.toList()));

        // wyszukowarka jako strumień, dodatkowo zmieniająca wartość każdego wywiltrowanego elementu (na duże znaki)
        searchers.put("searcher from stream with mapping", notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("tel"))
                .filter(note -> note.contains("wife"))
                .map(note -> note.toUpperCase())
                .collect(Collectors.toList()));
    }

    public void searchAll() {
        // iteruje przez wszystkie wyszukiwarki wykonująć wyszukanie wg reguł zdefiniowanych w ich instancjach
        for (String searcherKey : searchers.keySet()) {
            Searcher searcher = searchers.get(searcherKey);
            List<String> searchResults = searcher.findNotes(notesForSearching);
            SearchResultsPrinter searchResultsPrinter = new SearchResultsPrinter(searcherKey, searchResults);
            searchResultsPrinter.printResults();
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


