package pl.wojtek.search;

import pl.wojtek.Notes;

import java.util.List;

public interface Searcher {
    String searcherName = null;
    List<String> findNotes(Notes notes);
}
