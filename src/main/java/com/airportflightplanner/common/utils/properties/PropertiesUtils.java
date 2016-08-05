/*
 * @(#)PropertiesUtils.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
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
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.common.slotsignal.TopicName;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
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
    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtils.class);
    /** files. */
    private List<String> fileNames;
    /** map of attached slots. */
    private final Map<String, Slot> slotsMap = new ConcurrentHashMap<String, Slot>();

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

    /**
     * {@inheritDoc}.
     */
    @Override
    public Slot attachSlot(final String topicName) {
        // Nothing to do or to return
        return null;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void createSlots() {
        // LA CREATION DES SLOTS DOIT ETRE MANUELLE POUR NE PAS CASSER L'ARCHI.

        final Slot airportSlot = new Slot(TopicName.UPDATE_AIRPORT_TOPIC, PropertiesUtils.class.getSimpleName());
        airportSlot.registerSlot();
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
        slotsMap.put(airportSlot.getTopicName(), airportSlot);

        final Slot googleSlot = new Slot(TopicName.GOOGLE_PARAMETERS_TOPIC, PropertiesUtils.class.getSimpleName());
        googleSlot.registerSlot();
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

        slotsMap.put(googleSlot.getTopicName(), googleSlot);
    }

    /**
     * @return the fileNames
     */
    private List<String> getFileNames() {
        return Collections.unmodifiableList(fileNames);
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
        fileNames = newFileNames;
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

    /**
     * {@inheritDoc}.
     */
    @Override
    public Slot findSlot(final String topicName) {
        return slotsMap.get(topicName);
    }
    
    /**
     * @return the slotsMap
     */
    public Map<String, Slot> getSlotsMap() {
        return slotsMap;
    }
}
