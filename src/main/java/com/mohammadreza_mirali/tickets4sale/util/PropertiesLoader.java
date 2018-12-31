package com.mohammadreza_mirali.tickets4sale.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is a util class for loading properties from property file
 */
public class PropertiesLoader {
    public static Properties loadProperties(String resourceFileName) throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}
