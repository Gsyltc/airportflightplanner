/* @(#)PropertiesLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.slotsignal.AbstractSlotReceiver;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;

/**
 * @author Goubaud Sylvain
 *
 */
public class PropertiesUtils extends AbstractSlotReceiver {
    /** */
    private String            fileName = "application.properties";
    /** */
    private static Properties prop;
    /** The logger of this class. */
    private static final Log  LOGGER   = LogFactory.getLog(PropertiesUtils.class);

    /**
     *
     */
    @Override
    public void init() {
        super.init();
        prop = new Properties();
        File configFile = new File("config/" + fileName);
        if (!configFile.exists()) {
            try (InputStream defaultInputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
                prop.load(defaultInputStream);
            } catch (IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Error while loading the default configuration file", e);
                }
            }
        } else {
            try (InputStream fileInputStream = new FileInputStream("config/" + fileName)) {
                prop.load(fileInputStream);
            } catch (IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Error while loading the current configuration file", e);
                }
            }

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
     * @param key
     * @param value
     */
    public static void setPropertyByName(final String key, final String value) {
        if (null != prop) {
            prop.setProperty(key, value);
        }
    }

    /**
     *
     * @param fileName
     */
    public void setPropertiesFile(final String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     */
    protected void updateProperties() {
        File file = new File("config/" + fileName);
        try (FileOutputStream propertiesStream = new FileOutputStream(file)) {
            prop.store(propertiesStream, "");
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error while writing application properties", e);
            }
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        Slot<String> airportSlot = new Slot<String>(TopicName.UPDATE_AIRPORT_TOPIC, this);
        airportSlot.setSlotAction(new SlotAction<String>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final String arg) {
                setPropertyByName(CommonProperties.DEFAULT_AIRPORT, arg);
                updateProperties();
            }

        });

        Slot<Map<String, String>> googleSlot = new Slot<Map<String, String>>(TopicName.GOOGLE_PARAMETERS, this);
        googleSlot.setSlotAction(new SlotAction<Map<String, String>>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final Map<String, String> arg) {
                setPropertyByName(CommonProperties.GOOGLE_KEY, arg.get(CommonProperties.GOOGLE_KEY));
                setPropertyByName(CommonProperties.GOOGLE_MAPTYPE, arg.get(CommonProperties.GOOGLE_MAPTYPE));
                setPropertyByName(CommonProperties.GOOGLE_ZOOM_FACTOR, arg.get(CommonProperties.GOOGLE_ZOOM_FACTOR));
                updateProperties();
            }

        });
    }

    @Override
    public void attachSignal() {
        // TODO Auto-generated method stub

    }
}
