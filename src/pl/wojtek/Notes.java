package pl.wojtek;

import java.util.HashMap;
import java.util.Map;

public class Notes {

    public Notes(String notesName) {
        this.notesName = notesName;
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

    Map<String, String> notes = new HashMap();

    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }

    String notesName;

}
