package pl.wojtek;

import java.util.List;

public interface Searcher {
    String searcherName = null;
    List<String> findNotes(Notes notes);
}
