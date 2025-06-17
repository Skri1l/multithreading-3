package org.example.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class workingState extends conveyorState {
    private static final Logger logger = LogManager.getLogger(workingState.class);
    @Override
    public void inProcess() {
        logger.info("Conveyor is WORKING");
    }
}