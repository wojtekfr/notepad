package pl.wojtek.simpleSearch;

import pl.wojtek.Input;
import pl.wojtek.Notes;

import java.util.HashMap;
import java.util.Map;

public class SimpleSearch {
    private Map<String, String> simpleSearchResults = new HashMap<>();
    private Map<String, String> notes;
    private final Notes notesForSearching;
    private Input input = new Input();


    public SimpleSearch(Notes notesForSearching) {
        this.notesForSearching = notesForSearching;

    }

    public void selectSimpleSearchOperation() {
        int typeOfSearch = selectTypeOfSearch();
        if (typeOfSearch == 0) {
            return;
        } else if (typeOfSearch == 1) {
            simpleSearchResults = simpleSearchByKey();

        } else if (typeOfSearch == 2) {
            simpleSearchResults = simpleSearchByValue();
        }
        SimpleSearchResultsPrinter searchResultsPrinter = new SimpleSearchResultsPrinter(simpleSearchResults);
        searchResultsPrinter.printSimpleSearchResults();
    }


    private int selectTypeOfSearch() {
        do {
            System.out.println("1 - Search by key");
            System.out.println("2 - Search by value");
            System.out.println("0 - Exit to main menu");
            switch (input.getMenuItem()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "0":
                    return 0;
            }
        } while (true);

    }

    private Map<String, String> simpleSearchByKey() {
        notes = notesForSearching.getNotes();
        Map<String, String> searchResults = new HashMap<>();
        String keyForSearching = input.enterKey();
        for (String key : notes.keySet()) {
            if (key.toLowerCase().contains(keyForSearching.toLowerCase())) {
                searchResults.put(key, notes.get(key));
            }
        }
        return searchResults;
    }

    private Map<String, String> simpleSearchByValue() {
        notes = notesForSearching.getNotes();
        Map<String, String> searchResults = new HashMap<>();
        String valueForSearching = input.enterValue();
        for (String key : notes.keySet()) {
            if (notes.get(key).toLowerCase().contains(valueForSearching.toLowerCase())) {
                searchResults.put(key, notes.get(key));
            }
        }
        return searchResults;
    }
}
