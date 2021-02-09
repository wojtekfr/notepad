package pl.wojtek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notes {

    private String notesName;
    private Map<String, String> notes;
    private ArrayList<Annotation> annotations;

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    public void newAnnotation(String annotationText){
        Annotation annotation = new Annotation(annotationText);
        annotations.add(annotation);
    }


    public Notes(String notesName) {
        this.notesName = notesName;
        annotations = new ArrayList<>();
        notes = new HashMap<String, String>();
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    public void addNote(String key, String value) {
        notes.put(key, value);
    }

    public void removeNote(String key) {
        notes.remove(key);
    }

    public String getSpecificNote(String key) {
        return notes.get(key);
    }



    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }



}
