package pl.wojtek.search;

import java.util.HashMap;
import java.util.Map;

public class SimpleSearchResultsPrinter {

    public SimpleSearchResultsPrinter(Map<String, String> simpleSearchResults) {
        this.simpleSearchResults = simpleSearchResults;
    }

    Map<String, String> simpleSearchResults = new HashMap<>();

    public void printSimpleSearchResults() {
        if (simpleSearchResults.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            System.out.println("Found entries: ");
            for (String key : simpleSearchResults.keySet()) {
                //System.out.println(key + " : " + simpleSearchResults.get(key));
                System.out.printf("%-25s %-25s\n", key,simpleSearchResults.get(key));
            }

        }
    }
}
