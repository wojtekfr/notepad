package pl.wojtek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnnotationTest {

    Annotation testAnnotation = new Annotation("test");



    @Test
    public void testGetAnnotation() {
        Assert.assertEquals(testAnnotation.getAnnotation(), "test");
    }

    @Test
    public void testCalculateLetters(){
        Assert.assertEquals(testAnnotation.calculateLetters(),4);
    }

}
