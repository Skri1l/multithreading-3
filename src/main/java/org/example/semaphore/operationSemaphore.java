package org.example.semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class operationSemaphore {
    private static final Logger logger = LogManager.getLogger(operationSemaphore.class);
    private final Semaphore semaphore;

    public operationSemaphore(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void acquire(String name) throws InterruptedException {
        logger.info("{} trying to access a resource", name);
        semaphore.acquire();
        logger.info("{} got access to a resource.", name);
    }

    public void release(String name) {
        semaphore.release();
        logger.info("{} released a resource", name);
    }

    public int availablePermits() {
        return semaphore.availablePermits();
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
