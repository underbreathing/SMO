package by.underBreathing.simulationModel.statistic;

public class ModelTimer {
    private static long startTime;

    public static void initializeStartTime(){
        startTime = System.currentTimeMillis();
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }
}
