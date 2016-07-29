/*
 * @(#)PropertiesUtils.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 29 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.slotsignal.TopicName;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.common.SlotsProvider;
import fr.gsyltc.framework.slotsignals.slotreceiver.api.SlotReceiver;
import fr.gsyltc.framework.slotsignals.slots.Slot;

/**
 * @author Goubaud Sylvain
 *
 */
public class PropertiesUtils implements SlotReceiver {
    
    
    /** */
    private static final String USER_CONFIG_PATH = "config/";
    /** */
    private static Properties userProperties = new Properties();
    /** */
    private static Properties applicationProperties = new Properties();
    /** */
    private static final List<Properties> PROPERTIES = new ArrayList<Properties>();
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(PropertiesUtils.class);

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

        if (result.isEmpty() && LOGGER.isErrorEnabled()) {
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

    /** List of slots. */
    private List<Slot> slots;

    /** files. */
    private List<String> fileNames;

    /**
     * {@inheritDoc}.
     */
    @Override
    public Slot attachSlot(final String topicName) {
        return SlotsProvider.findSlotBySlotName(topicName + "." + PropertiesUtils.class.getSimpleName());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void createSlots() {
        final Slot airportSlot = attachSlot(TopicName.UPDATE_AIRPORT_TOPIC);
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

        // final Slot googleSlot = new Slot(TopicName.GOOGLE_PARAMETERS_TOPIC,
        // getClass().getSimpleName());
        // googleSlot.setSlotAction(new SlotAction<Map<String, String>>() {
        //
        //
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public void doAction(final Map<String, String> arg) {
        // setPropertyByName(CommonProperties.GOOGLE_MAPTYPE,
        // arg.get(CommonProperties.GOOGLE_MAPTYPE));
        // setPropertyByName(CommonProperties.GOOGLE_ZOOM_FACTOR,
        // arg.get(CommonProperties.GOOGLE_ZOOM_FACTOR));
        // setPropertyByName(CommonProperties.GOOGLE_POLYLINE_COLOR,
        // arg.get(CommonProperties.GOOGLE_POLYLINE_COLOR));
        // setPropertyByName(CommonProperties.GOOGLE_POLYLINE_WIDTH,
        // arg.get(CommonProperties.GOOGLE_POLYLINE_WIDTH));
        // updateProperties();
        // }
        //
        // });
    }

    /**
     * @return the fileNames
     */
    private List<String> getFileNames() {
        return Collections.unmodifiableList(this.fileNames);
    }

    /**
     * Initialize properties.
     */
    public void init() {
        createSlots();
        PROPERTIES.add(userProperties);
        PROPERTIES.add(applicationProperties);

        for (final String fileName : getFileNames()) {
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
                    applicationProperties.load(defaultInputStream);
                } catch (final IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("Error while loading the default configuration file", e);
                    }
                }
            }
        }
    }

    /**
     *
     * @param newFileNames
     *            List of resources files.
     */
    public void setFileNames(final List<String> newFileNames) {
        this.fileNames = newFileNames;
    }

    /**
     * @param slots
     *            the slots to set
     */
    public void setSlots(final List<Slot> slots) {
        this.slots = slots;
    }

    /**
     * Update the user properties fles.
     */
    protected void updateProperties() {
        final File file = new File(USER_CONFIG_PATH + "application.properties");
        try (FileOutputStream propertiesStream = new FileOutputStream(file)) {
            userProperties.store(propertiesStream, "");
        } catch (final IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error while writing application properties", e);
            }
        }
    }
}
