package org.example.conveyor;

import org.example.barrier.conveyorBarrier;
import org.example.semaphore.operationSemaphore;
import org.example.state.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class conveyor implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(conveyor.class);
    private final String name;
    private conveyorState state;
    private final conveyorBarrier barrier;
    private final operationSemaphore semaphore;

    public conveyor(String name, conveyorBarrier barrier, operationSemaphore semaphore) {
        this.name = name;
        this.barrier = barrier;
        this.semaphore = semaphore;
        this.state = new readyState();
    }

    @Override
    public Void call() throws Exception {
        logger.info(name + " в состоянии Ready");
        state.handle();
        TimeUnit.SECONDS.sleep(1);

        barrier.awaitBarrier(name);

        semaphore.acquire(name);
        try {
            state = new workingState();
            logger.info(name + " переходит в состояние Working");
            state.handle();
            TimeUnit.SECONDS.sleep(2);
        } finally {
            semaphore.release(name);
        }

        return null;
    }
}
