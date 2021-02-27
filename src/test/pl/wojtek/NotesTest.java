package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class NotesTest {
    Notes notes;
    Annotation mockAnnotation, mockAnnotation1;

    @Before
    public void initTests(){
        notes = new Notes("testowa");

        mockAnnotation = mock (Annotation.class);
        mockAnnotation1 = mock(Annotation.class);
    }

    @Test
    public void testUpdateNumberOfLettersinAllAnnotations(){
        expect(mockAnnotation.calculateLetters()).andReturn(3);
        expect(mockAnnotation1.calculateLetters()).andReturn(5);
        replay(mockAnnotation);
        replay(mockAnnotation1);
        notes.addAnnotation(mockAnnotation);
        notes.addAnnotation(mockAnnotation1);
        notes.updateNumberOfLettersInAllAnnotations();
        Assert.assertEquals(notes.getNumberOfLettersInAllAnnotations(),8);
        verify(mockAnnotation);
        verify(mockAnnotation1);
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

    @Test
    public void testGetAnnotations(){
        Annotation annotation = new Annotation("test");
        notes.addAnnotation(annotation);
        Assert.assertEquals(notes.getAnnotations().get(notes.getAnnotations().size()-1).getAnnotation(),"test");
    }

}
