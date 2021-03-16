package pl.wojtek.advancedSearch;

import java.util.List;

public class AdvancedSearchResultsPrinter {
    private final String searcherKey;
    private final List<String> results;



    public AdvancedSearchResultsPrinter(String searcherKey, List<String> results) {
        this.searcherKey = searcherKey;
        this.results = results;
    }

    public void printResults() {
        System.out.println("Search results for : " + searcherKey);
        for (String key : results) {
            System.out.println("found: " + key);
        }
    }
}
