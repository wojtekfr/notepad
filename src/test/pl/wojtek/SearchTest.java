package pl.wojtek;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

public class SearchTest extends TestCase {

    Notes notes;
    Annotation mockAnnotation, mockAnnotation1;
    Input mockInput;
    Search mockSearch;
    NotesPrinter mockNotesPrinter;

    @Test
    public void testSearchAll() {
        notes = new Notes("testowa");
        Search search = new Search(notes);
        Searcher mockSearcher = mock(Searcher.class);
        Searcher mockSearcher1 = mock(Searcher.class);
        ArrayList<String> testList = new ArrayList<>();
        testList.add("pierwszy");
        testList.add("drugi");
        ArrayList<String> testList1 = new ArrayList<>();
        testList1.add("drugapierwszy");
        search.addNewExistingSearcher("first", mockSearcher);
        search.addNewExistingSearcher("second", mockSearcher1);
        expect(mockSearcher.findNotes(notes)).andReturn(testList);
        expect(mockSearcher1.findNotes(notes)).andReturn(testList1);
        replay(mockSearcher);
        replay(mockSearcher1);
        search.searchAll();
        verify(mockSearcher);
        verify(mockSearcher1);

    }
}