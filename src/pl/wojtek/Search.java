package pl.wojtek;

import java.util.ArrayList;
import java.util.List;

public class Search {
    List<Searcher> searchers = new ArrayList<>();
    Notes notesForSearching;

    public Search(Notes notes) {
        SearcherByKey searcherByKey = new SearcherByKey("inn");
        searchers.add(searcherByKey);
        notesForSearching = notes;
    }

    public void searchAll() {
        for (Searcher searcher : searchers) {
            ResultsPrinter resultsPrinter = new ResultsPrinter(searcher.findNotes(notesForSearching));
            resultsPrinter.printResults();
        }
    }


}
