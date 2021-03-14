package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wojtek.search.SearchUsingSearcher;

import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

public class NotesTest {
    Notes notes;
    Annotation mockAnnotation, mockAnnotation1;
    Input mockInput;
    SearchUsingSearcher mockSearchUsingSearcher;
    NotesPrinter mockNotesPrinter;

    @Before
    public void initTests() {
        notes = new Notes("testowa");
        mockAnnotation = mock(Annotation.class);
        mockAnnotation1 = mock(Annotation.class);
        mockInput = mock(Input.class);
        mockSearchUsingSearcher = mock(SearchUsingSearcher.class);
        mockNotesPrinter = mock(NotesPrinter.class);
    }

    @Test
    public void testUpdateNumberOfLettersinAllAnnotations() {
        expect(mockAnnotation.calculateLetters()).andReturn(3);
        expect(mockAnnotation1.calculateLetters()).andReturn(5);
        replay(mockAnnotation);
        replay(mockAnnotation1);
        notes.addAnnotation(mockAnnotation);
        notes.addAnnotation(mockAnnotation1);
        notes.updateNumberOfLettersInAllAnnotations();
        Assert.assertEquals(notes.getNumberOfLettersInAllAnnotations(), 8);
        verify(mockAnnotation);
        verify(mockAnnotation1);
    }

    @Test
    public void testNotesNameisCorrect() {
        Assert.assertEquals("testowa", notes.getNotesName());
    }

    @Test
    public void testAddNote() {
        notes.addNote("testowy klucz", "testowa wartosc");
        notes.addNote("testowy klucz", "testowa wartosc");
        Assert.assertEquals("testowa wartosc", notes.getSpecificNote("testowy klucz"));
    }

    @Test
    public void testGetAnnotations() {
        Annotation annotation = new Annotation("test");
        notes.addAnnotation(annotation);
        Assert.assertEquals(notes.getAnnotations().get(notes.getAnnotations().size() - 1).getAnnotation(), "test");
    }


    @Test
    public void testCreateNewAnnotation() {
        notes.createNewAnnotation("testowa");
        Assert.assertEquals(notes.getAnnotations().get(0).getAnnotation(), "testowa");
    }

    @Test
    public void testCreateNewAnnotationThatIsNotCorrectFromUserInput() {
        expect(mockInput.enterString("annotation")).andThrow(new IllegalArgumentException("exception message"));
        replay(mockInput);
        notes.createNewAnnotation(mockInput);
        //Assert.assertEquals(null, notes.getAnnotations().get(0).getAnnotation());
    }



    @Test
    public void testCreateNewAnnotationFromUserInput() {
        notes.setInput(mockInput);
        expect(mockInput.enterString("annotation")).andReturn("annotation text");
        replay(mockInput);
        notes.createNewAnnotation(mockInput);
        Assert.assertEquals(notes.getAnnotations().get(0).getAnnotation(), "annotation text");
        verify(mockInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIncorrectNewAnnotation() {
        notes.createNewAnnotation("e%w");
    }

    @Test
    public void testGetNotes() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "bb");
        notes.setNotes(map);
        Map<String, String> map2 = new HashMap<>();
        Assert.assertEquals(notes.getNotes(), map);
    }


    @Test
    public void testCreateIncorrectNewAnnotationFromUserInput() {
        expect(mockInput.enterString("annotation")).andReturn("%");
        replay(mockInput);
        notes.createNewAnnotation(mockInput);
        Assert.assertEquals(notes.getAnnotations().get(0).getAnnotation(), "%");
        System.out.println(notes.getAnnotations().get(0).getAnnotation());
        verify(mockInput);
    }

    @Test
    public void testGetNumberOfLettersInAllAnnotationsWhenNull() {
        Notes notes = new Notes("name");
        Assert.assertEquals(0, notes.getNumberOfLettersInAllAnnotations());
    }

    @Test
    public void testUpdateNumberOfLettersInAllAnnotations() {
        notes.addAnnotation(mockAnnotation);
        notes.addAnnotation(mockAnnotation1);
        expect(mockAnnotation.calculateLetters()).andReturn(3);
        expect(mockAnnotation1.calculateLetters()).andReturn(2);
        replay(mockAnnotation);
        replay(mockAnnotation1);
        notes.updateNumberOfLettersInAllAnnotations();
        Assert.assertEquals(5, notes.getNumberOfLettersInAllAnnotations());
        verify(mockAnnotation);
    }

    @Test
    public void testAddAnnotation() {
        mockInput = mock(Input.class);
        notes.setInput(mockInput);
        expect(mockInput.enterKey()).andReturn("klucz");
        expect(mockInput.enterValue()).andReturn("wartosc");
        replay(mockInput);
        notes.addNewNoteEnteredByUser();
        Assert.assertEquals(notes.getSpecificNote("klucz"), "wartosc");
        verify(mockInput);
    }


    @Test
    public void testRemoveNote() {
        notes.setInput(mockInput);
        notes.addNote("klucz", "jakas wartosc");
        expect(mockInput.enterKey()).andReturn("klucz");
        expect(mockInput.askForDecision()).andReturn(true);
        replay(mockInput);
        notes.removeNote();
        Assert.assertEquals(null,notes.getSpecificNote("klucz"));
        verify(mockInput);
    }

    @Test
    public void testRemoveNoteThatDoNotExist() {
        notes.setInput(mockInput);
        notes.addNote("klucz", "jakas wartosc");
        expect(mockInput.enterKey()).andReturn("xklucz");
        replay(mockInput);
        notes.removeNote();
        verify(mockInput);
    }

    @Test
    public void testRemoveNoteUserDoNotConfirm() {
        notes.setInput(mockInput);
        notes.addNote("klucz", "jakas wartosc");
        expect(mockInput.enterKey()).andReturn("klucz");
        expect(mockInput.askForDecision()).andReturn(false);
        replay(mockInput);
        notes.removeNote();
        Assert.assertEquals("jakas wartosc",notes.getSpecificNote("klucz"));
        verify(mockInput);
    }


    public void testGetSpecificNote() {
    }

    public void testGetNotesName() {
    }

    @Test
    public void testSetNotesName() {
        notes.setNotesName("nazwa nowa");
        Assert.assertEquals(notes.getNotesName(), "nazwa nowa");
    }

    @Test
    public void testEditNote() {
        notes.setInput(mockInput);
        notes.addNote("klucz", "stara wartosc");
        expect(mockInput.enterKey()).andReturn("klucz");
        expect(mockInput.enterValue()).andReturn("nowa wartosc");
        replay(mockInput);
        notes.editNote();
        Assert.assertEquals(notes.getSpecificNote("klucz"), "nowa wartosc");
        verify(mockInput);
    }

    @Test
    public void testEditNoteNotExistingKey() {
        notes.setInput(mockInput);
        notes.addNote("klucz", "stara wartosc");
        expect(mockInput.enterKey()).andReturn("zly klucz");
        replay(mockInput);
        notes.editNote();
        Assert.assertEquals(notes.getSpecificNote("klucz"), "stara wartosc");
        verify(mockInput);
    }

    @Test
    public void testSearchAll() {
        notes.setSearch(mockSearchUsingSearcher);
        mockSearchUsingSearcher.searchAll();
        expectLastCall();
        replay(mockSearchUsingSearcher);
        notes.runAllExistingSearchers();
        verify(mockSearchUsingSearcher);
    }

    @Test
    public void testShowSearchers() {
        notes.setSearch(mockSearchUsingSearcher);
        mockSearchUsingSearcher.showSearchers();
        expectLastCall();
        replay(mockSearchUsingSearcher);
        notes.showSearchers();
        verify(mockSearchUsingSearcher);

    }

    @Test
    public void testAddNewSearcherByKey() {
        notes.setSearch(mockSearchUsingSearcher);
        mockSearchUsingSearcher.addNewSearcherByKey();
        expectLastCall();
        replay(mockSearchUsingSearcher);
        notes.addNewSearcherByKey();
        verify(mockSearchUsingSearcher);
    }

    @Test
    public void testAddNewSearcherByValue() {
        notes.setSearch(mockSearchUsingSearcher);
        mockSearchUsingSearcher.addNewSearcherByValue();
        expectLastCall();
        replay(mockSearchUsingSearcher);
        notes.addNewSearcherByValue();
        verify(mockSearchUsingSearcher);
    }

    @Test
    public void testPrintNotes() {
        notes.setNotesPrinter(mockNotesPrinter);
        mockNotesPrinter.printNotes();
        expectLastCall();
        replay(mockNotesPrinter);
        notes.printNotes();
        verify(mockNotesPrinter);
    }
}
