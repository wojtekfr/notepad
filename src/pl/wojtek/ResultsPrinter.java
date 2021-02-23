package pl.wojtek;

import java.util.List;

public class ResultsPrinter {
    private String searcherKey;
    private List<String> results;



    public ResultsPrinter(String searcherKey, List<String> results) {
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
