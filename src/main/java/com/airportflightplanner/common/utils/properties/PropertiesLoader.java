/* @(#)PropertiesLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.utils.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Goubaud Sylvain
 *
 */
public class PropertiesLoader {
    /** */
    private String     fileName = "application.properties";
    /** */
    private static Properties prop;
    /** The logger of this class. */
    private static final Log  LOGGER   = LogFactory.getLog(PropertiesLoader.class);



    /**
     *
     */
    public void init() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            if (inputStream != null) {
                prop = new Properties();
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }
        } catch (IOException e) {
            LOGGER.error("Error while loading the configuration file", e);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getPropertyByName(final String key) {
        if (null != prop) {
            return prop.getProperty(key);
        }
        return "";
    }

    /**
     *
     * @param fileName
     */
    public void setPropertiesFile(final String fileName) {
        this.fileName = fileName;
    }
}
