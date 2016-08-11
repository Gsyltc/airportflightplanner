/*
 * @(#)AircraftFileReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 4 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.importexport.importers;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.api.AircraftTypeAdapter;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftFileReader {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(AircraftFileReader.class);

    /** */
    private AircraftTypeAdapter adapter;

    /**
     *
     */
    public void init() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.AIRCRAFT_DIRECTORY)) {
            for (final Path path : stream) {
                final Path fileName = path.getFileName();
                if (null != fileName && fileName.toString().endsWith("txt") && !fileName.toString().contains("BASE")) {
                    final String ariCraftTmp = fileName.toString().replace(".txt", "");
                    getAdapter().addLivery(ariCraftTmp);
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     * @param newAdapter
     *            the adapter to set
     */
    public void setAdapter(final AircraftTypeAdapter newAdapter) {
        adapter = newAdapter;
    }

    /**
     * @return the adapter
     */
    private AircraftTypeAdapter getAdapter() {
        return adapter;
    }
}
