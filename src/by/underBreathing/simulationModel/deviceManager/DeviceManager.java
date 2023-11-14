package by.underBreathing.simulationModel.deviceManager;

import by.underBreathing.simulationModel.bidBroker.BidBroker;
import by.underBreathing.simulationModel.consumer.Consumer;
import by.underBreathing.simulationModel.model.Bid;

import java.util.ArrayList;
import java.util.Optional;

public class DeviceManager implements Runnable {

    DeviceRing deviceRing;
    private final BidBroker bidBroker;
    public DeviceManager(final BidBroker bidBroker, final DeviceRing deviceRing){
        this.bidBroker = bidBroker;
        this.deviceRing = deviceRing;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            if(deviceRing.countOfFreeDevices > 0) {
                Consumer freeConsumer = deviceRing.getFreeDevice();
                //if (freeConsumer != null) {
                final Bid receivedBid = bidBroker.get();
                Thread consumer = new Thread(freeConsumer);
                deviceRing.countOfFreeDevices--;
                freeConsumer.start(consumer, receivedBid);//он сам себя запускает с установкой карент-заявки
                // }
            }

        }
    }

    public void addConsumer(Consumer consumer){
        deviceRing.addDevice(consumer);
    }




}
