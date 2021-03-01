package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnnotationTest {

    Annotation testAnnotation = new Annotation("test");
    Annotation testAnnotationWithLongConstructor = new Annotation("tekst", "data");


    @Test
    public void testGetAnnotation() {
        Assert.assertEquals(testAnnotation.getAnnotation(), "test");
    }

    @Test
    public void testCalculateLetters() {
        Assert.assertEquals(testAnnotation.calculateLetters(), 4);
    }

    @Test
    public void testGetFromAnnotationWithLongConstructor() {
        Assert.assertEquals(testAnnotationWithLongConstructor.getDateOfAdding(), "data");
        Assert.assertEquals(testAnnotationWithLongConstructor.getAnnotation(),"tekst");
    }

    @Test
    public void testTestToString() {
        Assert.assertEquals(testAnnotationWithLongConstructor.toString(),"tekst data");
    }
}
