package pl.wojtek;

public class UserInterface {

    public void printNotes(Notes notes){
        System.out.println("Your notes:");
        for (String note:notes.getNotes().keySet()){
            System.out.println(note + "   " + notes.getSpecificNote(note));
        }
    }

}
