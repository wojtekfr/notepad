package pl.wojtek.advancedSearch;

import pl.wojtek.Notes;

import java.util.List;

public interface Searcher {
      List<String> findNotes(Notes notes);
}
