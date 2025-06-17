package org.example.semaphore;

import java.util.concurrent.Semaphore;

public class operationSemaphore {
    private final Semaphore semaphore;

    public operationSemaphore(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void acquire(String name) throws InterruptedException {
        System.out.println(name + " пытается получить доступ к ресурсу...");
        semaphore.acquire();
        System.out.println(name + " получил доступ к ресурсу.");
    }

    public void release(String name) {
        semaphore.release();
        System.out.println(name + " освободил ресурс.");
    }
}
