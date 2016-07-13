/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
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
            for (Path path : stream) {
                Path fileName = path.getFileName();
                if (null != fileName) {
                    AIRPORTS.add(fileName.toString());
                }
            }
        } catch (IOException e) {
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
