package pl.wojtek;

import java.util.List;

public interface
Searcher {
    String searcherName = null;

    //public String getSearcherName();
    public List<String> findNotes(Notes notes);
}
