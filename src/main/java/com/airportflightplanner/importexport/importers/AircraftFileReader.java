/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.importexport.importers;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.adapter.AircraftTypeAdapter;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftFileReader {
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(AircraftFileReader.class);

    /**
     *
     */
    public void init() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.AIRCRAFT_DIRECTORY)) {
            for (final Path path : stream) {
                Path fileName = path.getFileName();
                if ((null != fileName) && fileName.toString().endsWith("txt") && !(fileName.toString().contains("BASE"))) {
                    final String ariCraftTmp = fileName.toString().replace(".txt", "");
                    AircraftTypeAdapter.addLivery(ariCraftTmp);
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }
}
