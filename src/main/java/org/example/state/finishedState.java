package org.example.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class finishedState extends conveyorState {
    private static final Logger logger = LogManager.getLogger(finishedState.class);

    @Override
    public void inProcess() {
        logger.info("Conveyor has FINISHED processing");
    }
}
