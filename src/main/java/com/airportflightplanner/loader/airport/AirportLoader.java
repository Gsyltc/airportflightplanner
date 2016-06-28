/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.loader.airport;

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
public class AirportLoader {
    /** The logger of this class. */
    private static final Log          LOGGER   = LogFactory.getLog(AirportLoader.class);
    /** */
    private static final List<String> AIRPORTS = new ArrayList<String>();

    /**
     *
     */
    public void loadAirports() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY)) {
            for (Path path : stream) {
                AIRPORTS.add(path.getFileName().toString());
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
