package by.underBreathing.simulationModel.consumer;

import by.underBreathing.simulationModel.model.Bid;
import by.underBreathing.simulationModel.statistic.ModelTimer;
import by.underBreathing.simulationModel.statistic.Note;
import by.underBreathing.simulationModel.statistic.SpecialEvents;
import by.underBreathing.simulationModel.statistic.Statistic;

public class Consumer implements Runnable{
    public boolean isBusy;
    private Bid currentBid;
    public String number;
    private final long averageProcessingTime;
    public Consumer(final String number, final long processingTime){
        this.number = number;
        this.isBusy = false;
        this.averageProcessingTime = processingTime;
    }
    @Override
    public void run() {
        try {

            isBusy = true;
            Thread.sleep(generateProcessingTime());
            Statistic.recordAnEvent(new Note(SpecialEvents.DEVICE_RELEASE, ModelTimer.getCurrentTime(),currentBid.toString(),number));
            isBusy = false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private long generateProcessingTime(){//генерация времени по экспоненциальному закону
        return (long) (-averageProcessingTime * Math.log(Math.random()));
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }
}
