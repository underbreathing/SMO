package by.underBreathing.simulationModel.statistic;

import java.util.ArrayList;

public class Statistic {
    private static ArrayList<Note> notes = new ArrayList<>();

    public static synchronized void  recordAnEvent(final Note note){
        notes.add(note);
    }

    public static void outputInformation(){
        for(Note note : notes){
            System.out.println(note);
        }
    }
}
