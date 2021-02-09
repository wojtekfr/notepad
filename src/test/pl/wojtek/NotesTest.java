package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wojtek.Notes;
public class NotesTest {
    Notes notes;

    @Before
    public void initTests(){
        notes = new Notes("testowa");
    }

    @Test
    public void testNotesNameisCorrect()
    {
            Assert.assertEquals("testowa", notes.getNotesName());
    }

    @Test
    public void testAddNewNote(){
    notes.addNote("testowy klucz", "testowa wartosc");
    notes.addNote("testowy klucz", "testowa wartosc");
    Assert.assertEquals("testowa wartosc", notes.getSpecificNote("testowy klucz"));
    }

}
