package pl.wojtek;


import java.text.SimpleDateFormat;

public class Annotation {
    public String getAnnotation() {
        return annotation;
    }

    private String annotation;
    private String dateOfAdding;

    @Override
    public String toString() {
        return annotation + " " + dateOfAdding;
    }


    public Annotation(String annotation) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        this.annotation = annotation;
        dateOfAdding = formatter.format(System.currentTimeMillis());
    }

    public int calculateLetters(){
       return annotation.length();
    }



}