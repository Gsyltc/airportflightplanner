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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.slotsignal.AbstractSlotReceiver;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;

/**
 * @author Goubaud Sylvain
 *
 */
public class PropertiesUtils extends AbstractSlotReceiver {
    /** */
    private transient String              fileName              = "application.properties";
    /** */
    private transient static final String USER_CONFIG_PATH      = "config/";
    /** */
    private transient static final String SYSTEM_CONFIG_PATH    = "com/airportflightplanner/common/utils/properties/";
    /** */
    private static Properties             userProperties        = new Properties();
    /** */
    private static Properties             applicationProperties = new Properties();
    /** */
    private static final List<Properties> PROPERTIES            = new ArrayList<Properties>();

    /** The logger of this class. */
    private static final Log              LOGGER                = LogFactory.getLog(PropertiesUtils.class);

    // /**
    // *
    // */
    // public PropertiesUtils() {
    // super();
    // }

    /**
     *
     */
    @Override
    public void init() {
        super.init();
        PROPERTIES.add(userProperties);
        PROPERTIES.add(applicationProperties);

        final File configFile = new File(USER_CONFIG_PATH + fileName);
        if (configFile.exists()) {
            try (InputStream fileInputStream = new FileInputStream(USER_CONFIG_PATH + fileName)) {
                userProperties.load(fileInputStream);
            } catch (final IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Error while loading the current configuration file", e);
                }
            }
        } else {

            try (InputStream defaultInputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
                userProperties.load(defaultInputStream);
            } catch (final IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Error while loading the default configuration file", e);
                }
            }
        }

        try (InputStream defaultInputStream = getClass().getClassLoader().getResourceAsStream(SYSTEM_CONFIG_PATH + fileName)) {
            applicationProperties.load(defaultInputStream);
        } catch (final IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error while loading the default configuration file", e);
            }
        }

    }

    /**
     *
     * @param key
     * @return
     */
    public static String getPropertyByName(final String key) {
        String result = "";
        for (final Properties properties : PROPERTIES) {
            if (properties.containsKey(key)) {
                result = properties.getProperty(key);
                break;
            }
        }

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Error while loading properties. Property " + key + " not found");
        }
        return result;
    }

    /**
     *
     * @param key
     * @param value
     */
    public static void setPropertyByName(final String key, final String value) {
        if (null != userProperties) {
            userProperties.setProperty(key, value);
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
        final File file = new File(USER_CONFIG_PATH + fileName);
        try (FileOutputStream propertiesStream = new FileOutputStream(file)) {
            userProperties.store(propertiesStream, "");
        } catch (final IOException e) {
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
    public final void attachSlotAction() {
        final SelectionSlot<String> airportSlot = new SelectionSlot<String>(TopicName.UPDATE_AIRPORT_TOPIC, this);
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

        final SelectionSlot<Map<String, String>> googleSlot = new SelectionSlot<Map<String, String>>(TopicName.GOOGLE_PARAMETERS, this);
        googleSlot.setSlotAction(new SlotAction<Map<String, String>>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final Map<String, String> arg) {
                setPropertyByName(CommonProperties.GOOGLE_MAPTYPE, arg.get(CommonProperties.GOOGLE_MAPTYPE));
                setPropertyByName(CommonProperties.GOOGLE_ZOOM_FACTOR, arg.get(CommonProperties.GOOGLE_ZOOM_FACTOR));
                setPropertyByName(CommonProperties.GOOGLE_POLYLINE_COLOR, arg.get(CommonProperties.GOOGLE_POLYLINE_COLOR));
                setPropertyByName(CommonProperties.GOOGLE_POLYLINE_WIDTH, arg.get(CommonProperties.GOOGLE_POLYLINE_WIDTH));
                updateProperties();
            }

        });
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        // TODO Auto-generated method stub

    }
}
