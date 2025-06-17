package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configLoader {
    private final Properties properties = new Properties();

    public configLoader(String resourceName) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new IOException("Файл конфигурации не найден: " + resourceName);
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
