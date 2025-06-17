package org.example;

import org.example.barrier.conveyorBarrier;
import org.example.config.configLoader;
import org.example.conveyor.conveyor;
import org.example.semaphore.operationSemaphore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws IOException {
        configLoader config = new configLoader("config.properties");
        int conveyorCount = config.getConveyorCount();
        int permits = config.getSemaphorePermits();

        conveyorBarrier barrier = new conveyorBarrier(conveyorCount);
        operationSemaphore semaphore = new operationSemaphore(permits);

        ExecutorService executor = Executors.newFixedThreadPool(conveyorCount);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 1; i <= conveyorCount; i++) {
            conveyor conveyor = new conveyor("Conveyor-" + i, barrier, semaphore);
            futures.add(executor.submit(conveyor));
        }

        futures.forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}