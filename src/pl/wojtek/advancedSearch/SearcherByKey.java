package pl.wojtek.advancedSearch;

import pl.wojtek.Notes;

import java.util.ArrayList;
import java.util.List;

public class SearcherByKey implements Searcher {
    String keyToFind;
    String searcherName = "search by key";

    public String getSearcherName() {
        return searcherName;
    }

    public SearcherByKey(String keyToFind) {
        this.keyToFind = keyToFind;
    }

    public List<String> findNotes(Notes notes) {
        List<String> foundKeys = new ArrayList<>();
        for (String key : notes.getNotes().keySet()) {
            if (key.contains(keyToFind)) {
                foundKeys.add(key);
            }
        }
        return foundKeys;
    }
}
