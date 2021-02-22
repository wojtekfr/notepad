package pl.wojtek;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    List<Searcher> searchers = new ArrayList<>();
    Notes notesForSearching;

    public Search(Notes notes) {
        notesForSearching = notes;


        // dodaj wyszukiwarkę zdefiniowaną w osobnej klasie
        SearcherByKey searcherByKey = new SearcherByKey("inn");
        searchers.add(searcherByKey);

        // dodaj wyszukiwarkę zdefiniowaną w klasie anonimowej
        searchers.add(new Searcher() {

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
        searchers.add((notes1) -> {
            List<String> foundKeys = new ArrayList<>();
            for (String keyToFind : notes1.getNotes().keySet()) {
                if (keyToFind.toLowerCase().contains("tel")) {
                    foundKeys.add(keyToFind);
                }
            }
            return foundKeys;
        });

        // dodaj wyszukiwarkę zdefiniowaną jako strumień

        searchers.add(notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("tel"))
                .filter(note -> note.contains("wife"))
                .collect(Collectors.toList()));

        // wyszukowarka jako strumień, dodatkowo zmieniająca wartość każdego wywiltrowanego elementu (na duże znaki)
        searchers.add(notes2 -> notes2.getNotes().keySet().stream()
                .filter(note -> note.contains("tel"))
                .filter(note -> note.contains("wife"))
                .map(note -> note.toUpperCase())
                .collect(Collectors.toList()));



    }

    public void searchAll() {
        // iteruje przez wszystkie wyszukiwarki wykonująć wyszukanie wg reguł zdefiniowanych w ich instancjach
        for (Searcher searcher : searchers) {
            List<String> searchResults = searcher.findNotes(notesForSearching);
            ResultsPrinter resultsPrinter = new ResultsPrinter(searchResults);
            resultsPrinter.printResults();
        }
    }


}
