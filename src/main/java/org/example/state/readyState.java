package org.example.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class readyState extends conveyorState {

    private static final Logger logger = LogManager.getLogger(readyState.class);
    @Override
    public void inProcess() {
        logger.info("Conveyor is READY");
    }
}
