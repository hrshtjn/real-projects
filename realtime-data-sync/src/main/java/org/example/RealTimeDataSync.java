package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RealTimeDataSync {
    public static void main(String[] args) {
        DataSynchronizer sync = new DataSynchronizer();

        // Let's pretend we're getting real-time updates from somewhere
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> sync.updateData("Key1", "Value1"));
        executorService.execute(() -> sync.updateData("Key2", "Value2"));
        executorService.execute(() -> sync.updateData("Key3", "Value3"));

        // Synchronize data every 5 seconds
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        scheduledExecutor.scheduleWithFixedDelay(sync::synchronizeData, 0, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(20000); // Running for a short time for demonstration sake
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executorService.shutdown();
        scheduledExecutor.shutdown();
    }
}
