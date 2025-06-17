package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.conveyor.conveyor;

public class configLoader {
    private static final Logger logger = LogManager.getLogger(configLoader.class);
    private final Properties properties = new Properties();

    public configLoader(String resourceName) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new IOException("Configuration file is missing: " + resourceName);
            }
            properties.load(input);
        }
    }

    public int getConveyorCount() {
        return Integer.parseInt(properties.getProperty("conveyor.count"));
    }

    public int getSemaphorePermits() {
        return Integer.parseInt(properties.getProperty("semaphore.permits"));
    }
}
