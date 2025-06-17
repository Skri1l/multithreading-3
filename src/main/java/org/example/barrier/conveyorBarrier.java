package org.example.barrier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class conveyorBarrier {
    private static final Logger logger = LogManager.getLogger(conveyorBarrier.class);
    private final CyclicBarrier barrier;

    public conveyorBarrier(int count) {
        this.barrier = new CyclicBarrier(count);
    }

    public void awaitBarrier(String name) {
        try {
            logger.info("{} waiting for barrie", name);
            barrier.await();
            logger.info("{} waiting for barrie", name);
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            logger.error("{} has a problem w barrier {}", name, e.getMessage());
        }
    }

    public int getParties() {
        return barrier.getParties();
    }
}
