package by.underBreathing.simulationModel.deviceManager;

import by.underBreathing.simulationModel.bidBroker.BidBroker;
import by.underBreathing.simulationModel.consumer.Consumer;
import by.underBreathing.simulationModel.model.Bid;

import java.util.ArrayList;
import java.util.Optional;

public class DeviceManager implements Runnable {

    private final BidBroker bidBroker;
    public DeviceManager(final BidBroker bidBroker){
        this.bidBroker = bidBroker;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            Consumer freeConsumer = DeviceRing.getFreeDevice();
            if(freeConsumer != null){
                final Bid receivedBid = bidBroker.get();
                //на прибор передавать не буду, т.к прибор ничего не будет делать с заявкой
                freeConsumer.setCurrentBid(receivedBid);
                Thread consumer = new Thread(freeConsumer);//запускаем прибор на работу в надежде что пока он спит
                //его переменна isBusy будет true
                consumer.start();
            }

        }
    }

    public static class DeviceRing{
        private static int devicePointer = 0;
        private static int size;
        private final static ArrayList<Consumer> deviceArrayList = new ArrayList<>();

        //возможно придется отладить эту функцию
        public static Consumer getFreeDevice(){//return null if all devices is busy now
            for(int i = 0; i < size;++i){
                Consumer currentDevice = deviceArrayList.get(devicePointer);
                if(!currentDevice.isBusy){//если не занят - передвигаем и возвращаем прибор
                    movePointer();
                    return currentDevice;
                }
                else{//если занят - передвигаем
                    movePointer();
                }

            }
            return null;
        }

        private static void movePointer(){//передвинуть указатель (по кольцу)
            ++devicePointer;
            if(devicePointer == size){
                devicePointer = 0;
            }
        }
        public static void addDevice(Consumer device){
            deviceArrayList.add(device);
            ++size;
        }
    }


}
