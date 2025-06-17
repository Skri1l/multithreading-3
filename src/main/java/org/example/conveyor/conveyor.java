package org.example.conveyor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.barrier.conveyorBarrier;
import org.example.semaphore.operationSemaphore;
import org.example.state.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class conveyor implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(conveyor.class);

    private String name;
    private conveyorState state;
    private conveyorBarrier barrier;
    private operationSemaphore semaphore;

    public conveyor(String name, conveyorBarrier barrier, operationSemaphore semaphore) {
        this.name = name;
        this.barrier = barrier;
        this.semaphore = semaphore;
        this.state = new readyState();
    }

    @Override
    public Void call() throws Exception {
        setState(new readyState());
        logger.info("{} in {} state", getName(), getState().getClass().getSimpleName());
        getState().inProcess();
        TimeUnit.SECONDS.sleep(1);

        getBarrier().awaitBarrier(getName());

        getSemaphore().acquire(getName());
        try {
            setState(new workingState());
            logger.info("{} in {} state", getName(), getState().getClass().getSimpleName());
            getState().inProcess();
            TimeUnit.SECONDS.sleep(2);

            setState(new finishedState());
            logger.info("{} in {} state", getName(), getState().getClass().getSimpleName());
            getState().inProcess();
        } finally {
            getSemaphore().release(getName());
        }

        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public conveyorState getState() {
        return state;
    }

    public void setState(conveyorState state) {
        this.state = state;
    }

    public conveyorBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(conveyorBarrier barrier) {
        this.barrier = barrier;
    }

    public operationSemaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(operationSemaphore semaphore) {
        this.semaphore = semaphore;
    }
}
