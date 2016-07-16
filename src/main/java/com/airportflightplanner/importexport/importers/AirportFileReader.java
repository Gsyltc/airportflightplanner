/*
 * @(#)AirportFileReader.java
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class AirportFileReader {
    /** The logger of this class. */
    private static final Log          LOGGER   = LogFactory.getLog(AirportFileReader.class);
    /** */
    private static final List<String> AIRPORTS = new ArrayList<String>();

    /**
     *
     */
    public void init() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY)) {
            for (final Path path : stream) {
                final Path fileName = path.getFileName();
                if (null != fileName) {
                    AIRPORTS.add(fileName.toString());
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     *
     * @return
     */
    public static List<String> getAirports() {
        return Collections.unmodifiableList(AIRPORTS);
    }
}
