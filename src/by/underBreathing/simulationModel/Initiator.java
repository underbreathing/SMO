package by.underBreathing.simulationModel;

import by.underBreathing.simulationModel.bidBroker.BidBroker;
import by.underBreathing.simulationModel.consumer.Consumer;
import by.underBreathing.simulationModel.deviceManager.DeviceManager;
import by.underBreathing.simulationModel.statistic.ModelTimer;
import by.underBreathing.simulationModel.producer.Producer;
import by.underBreathing.simulationModel.statistic.Statistic;

import java.util.Arrays;
import java.util.Scanner;

public class Initiator {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите продолжительность эксперимента в секундах : ");
        Scanner scanner = new Scanner(System.in);
        long timeOfExperimentMillis = scanner.nextLong() * 1000;
        ModelTimer.initializeStartTime();
        final BidBroker bidBroker = new BidBroker(2);
        DeviceManager.DeviceRing.addDevice(new Consumer("1",1500));
        //DeviceManager.DeviceRing.addDevice(new Consumer(3000));

        final Thread firstProducer = new Thread(new Producer(2000,bidBroker));
        final Thread secondProducer = new Thread(new Producer(3000,bidBroker));
        final Thread deviceManager = new Thread(new DeviceManager(bidBroker));
        startThreads(firstProducer,secondProducer,deviceManager);

        Thread.sleep(timeOfExperimentMillis);

        interruptThreads(firstProducer,secondProducer);

        Thread.sleep(8000);//чтобы приборы доработали
        deviceManager.interrupt();
        System.out.println("Подождали, теперь выводим статистику генерации заявок - > ");
        Statistic.outputInformation();
    }
    private static void interruptThreads(final Thread... threads){
        Arrays.stream(threads).forEach(Thread::interrupt);
    }

    private static void startThreads(final Thread... threads){
        Arrays.stream(threads).forEach(Thread::start);
    }
}
