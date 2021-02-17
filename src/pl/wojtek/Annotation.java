package pl.wojtek;


import java.text.SimpleDateFormat;

public class Annotation {
    private String annotation;
    private String dateOfAdding;

    public String getAnnotation() {
        return annotation;
    }

    public String getDateOfAdding() {
        return dateOfAdding;
    }



    @Override
    public String toString() {
        return annotation + " " + dateOfAdding;
    }


    public Annotation(String annotation) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        this.annotation = annotation;
        dateOfAdding = formatter.format(System.currentTimeMillis());
    }

    public Annotation(String annotation, String date) {

        this.annotation = annotation;
        this.dateOfAdding = date;
    }

    public int calculateLetters(){
       return annotation.length();
    }



}