package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

public class NotesTest {
    Notes notes;
    Annotation mockAnnotation, mockAnnotation1;
    Input mockInput;
    Search mockSearch;

    @Before
    public void initTests() {
        notes = new Notes("testowa");
        mockAnnotation = mock(Annotation.class);
        mockAnnotation1 = mock(Annotation.class);
        mockInput = mock(Input.class);
        mockSearch = mock(Search.class);}

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
    public void testGetNotes() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "bb");
        notes.setNotes(map);
        Map<String, String> map2 = new HashMap<>();
        Assert.assertEquals(notes.getNotes(), map);
    }

    @Test
    public void testCreateNewAnnotationFromUserInput() {
        expect(mockInput.enterString("annotation")).andReturn("www");
        replay(mockInput);
        notes.createNewAnnotation(mockInput);
        Assert.assertEquals(notes.getAnnotations().get(0).getAnnotation(), "www");
        verify(mockInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIncorrectNewAnnotation() {
        notes.createNewAnnotation("e%w");
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

    public void testTestGetNotes() {
    }

    public void testSetNotes() {
    }

    public void testAddNewNoteEnteredByUser() {
    }

    public void testRemoveNote() {
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
        notes.setSearch(mockSearch);
        mockSearch.searchAll();
        expectLastCall();
        replay(mockSearch);
        notes.searchAll();
        verify(mockSearch);
    }
    @Test
    public void testShowSearchers() {
        notes.setSearch(mockSearch);
        mockSearch.showSearchers();
        expectLastCall();
        replay(mockSearch);
        notes.showSearchers();
        verify(mockSearch);

    }
    @Test
    public void testAddNewSearcherByKey() {
        notes.setSearch(mockSearch);
        mockSearch.addNewSearcherByKey();
        expectLastCall();
        replay(mockSearch);
        notes.addNewSearcherByKey();
        verify(mockSearch);
    }
    @Test
    public void testAddNewSearcherByValue() {
        notes.setSearch(mockSearch);
        mockSearch.addNewSearcherByValue();
        expectLastCall();
        replay(mockSearch);
        notes.addNewSearcherByValue();
        verify(mockSearch);
    }

    public void testPrintNotes() {
    }
}
