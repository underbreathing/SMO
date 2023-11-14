package by.underBreathing.simulationModel.statistic;

public class Note {
    SpecialEvents typeOfSpecialEvent;
    long time;
    String bid;
    int numberOfDevice;
    long timeOfProcessing;
    public Note(final SpecialEvents typeOfSpecialEvent,final long time, String bid,int numberOfDevice,long timeOfProcessing){
        this.typeOfSpecialEvent = typeOfSpecialEvent;
        this.time = time;
        this.bid = bid;
        this.numberOfDevice = numberOfDevice;
        this.timeOfProcessing = timeOfProcessing;
    }

    public Note(final SpecialEvents typeOfSpecialEvent,final long time, String bid){
        this.typeOfSpecialEvent = typeOfSpecialEvent;
        this.time = time;
        this.bid = bid;
        this.numberOfDevice = 0;
    }

    @Override
    public String toString() {
        if(numberOfDevice == 0 ){
            return typeOfSpecialEvent + " " + bid + " " + time ;
        }
        else{
            return typeOfSpecialEvent + " " + bid + " " + time + " device " + numberOfDevice + " time of processing = " + timeOfProcessing;
        }
    }
}
