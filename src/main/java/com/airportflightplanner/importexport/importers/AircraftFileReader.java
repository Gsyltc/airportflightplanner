/*
 * @(#)AircraftFileReader.java
 *
 * Goubaud Sylvain - 2016.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.api.adapter.AircraftTypeAdapter;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftFileReader {
    /** The logger of this class. */
    private static final Log              LOGGER = LogFactory.getLog(AircraftFileReader.class);
    /** */
    private transient AircraftTypeAdapter adapter;

    /**
     *
     */
    public void init() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.AIRCRAFT_DIRECTORY)) {
            for (final Path path : stream) {
                final Path fileName = path.getFileName();
                if (null != fileName && fileName.toString().endsWith("txt") && !fileName.toString().contains("BASE")) {
                    final String ariCraftTmp = fileName.toString().replace(".txt", "");
                    adapter.addLivery(ariCraftTmp);
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     * @param adapter
     *            the adapter to set
     */
    public void setAdapter(final AircraftTypeAdapter adapter) {
        this.adapter = adapter;
    }
}
