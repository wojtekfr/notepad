package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class SearcherByKey implements Searcher {
    private String keyToFind;


    public SearcherByKey(String key) {
        keyToFind = key;

    }

    public List<String> findNotes(Notes notes) {
        List<String> foundKeys = new ArrayList<>();
        for (String key : notes.getNotes().keySet()) {
            if (key.contains(keyToFind)) {
                foundKeys.add(keyToFind);
            }
        }

        return foundKeys;

    }
}
