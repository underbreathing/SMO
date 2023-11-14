package by.underBreathing.simulationModel.statistic;

public class Note {
    SpecialEvents typeOfSpecialEvent;
    long time;
    String bid;
    String fromDevice;
    public Note(final SpecialEvents typeOfSpecialEvent,final long time, String bid,String fromDevice){
        this.typeOfSpecialEvent = typeOfSpecialEvent;
        this.time = time;
        this.bid = bid;
        this.fromDevice = fromDevice;
    }

    public Note(final SpecialEvents typeOfSpecialEvent,final long time, String bid){
        this.typeOfSpecialEvent = typeOfSpecialEvent;
        this.time = time;
        this.bid = bid;
        this.fromDevice = "";
    }

    @Override
    public String toString() {
        if(fromDevice.isEmpty()){
            return typeOfSpecialEvent + " " + bid + " " + time;
        }
        else{
            return typeOfSpecialEvent + " " + bid + " " + time + " device " + fromDevice;
        }
    }
}
