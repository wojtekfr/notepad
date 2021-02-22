package pl.wojtek;

import java.util.List;

public class ResultsPrinter {
    private List<String> results;
    String searcherName;

    public ResultsPrinter(List<String> results) {
        this.results = results;
        //this.searcherName = searcherName;
    }

    public void printResults() {
        System.out.println("Search results: ");
        for (String key : results) {
            System.out.println("found key : " + key);
        }
    }
}
