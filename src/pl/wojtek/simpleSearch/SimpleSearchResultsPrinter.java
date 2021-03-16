package pl.wojtek.simpleSearch;

import java.util.HashMap;
import java.util.Map;

public class SimpleSearchResultsPrinter {

    Map<String, String> simpleSearchResults;

    public SimpleSearchResultsPrinter(Map<String, String> simpleSearchResults) {
        this.simpleSearchResults = simpleSearchResults;
    }

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
