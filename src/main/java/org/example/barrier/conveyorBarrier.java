package org.example.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class conveyorBarrier {
    private final CyclicBarrier barrier;

    public conveyorBarrier(int count) {
        this.barrier = new CyclicBarrier(count);
    }

    public void awaitBarrier(String name) {
        try {
            System.out.println(name + " ждет у барьера...");
            barrier.await();
            System.out.println(name + " прошел барьер.");
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}