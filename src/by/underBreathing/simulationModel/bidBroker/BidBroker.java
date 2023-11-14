package by.underBreathing.simulationModel.bidBroker;

import by.underBreathing.simulationModel.model.Bid;
import by.underBreathing.simulationModel.statistic.ModelTimer;
import by.underBreathing.simulationModel.statistic.Note;
import by.underBreathing.simulationModel.statistic.SpecialEvents;
import by.underBreathing.simulationModel.statistic.Statistic;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class BidBroker {
    private final Queue<Bid> messageQueue;
    private final int maxStoredBids;
    public BidBroker(final int maxStoredMessages){
        this.messageQueue = new LinkedList<>();
        this.maxStoredBids = maxStoredMessages;
    }

    public synchronized void put(final Bid bid){
        if(messageQueue.size() >= maxStoredBids){
            System.out.println(messageQueue.poll() + " ушла в отказ");
                messageQueue.add(bid);
               // wait();
        }
        else {
            this.messageQueue.add(bid);


            notifyAll();
        }
        Statistic.recordAnEvent(new Note(SpecialEvents.GENERATION, ModelTimer.getCurrentTime(),bid.toString()));
        //System.out.println(bid + " received in buffer ");
    }

    public synchronized Bid get(){
        while(messageQueue.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                currentThread().interrupt();
                throw new RuntimeException();
            }
        }
        final Bid bid =  messageQueue.poll();
        //System.out.println(bid + " was taken from the buffer");
        return bid;
    }
}
