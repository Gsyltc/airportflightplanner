/*
 * @(#)CommonProperties.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 2 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.properties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Goubaud Sylvain
 *
 */
public final class CommonProperties {
    
    
    /** */
    public static final Path ROUTES_DIRECTORY = Paths.get(PropertiesUtils.getPropertyByName("Application.xplane.directory") + //
            PropertiesUtils.getPropertyByName("Application.xplane.routes.directory"));
    /** */
    public static final Path AIRCRAFT_DIRECTORY = Paths.get(PropertiesUtils.getPropertyByName("Application.xplane.directory") + //
            PropertiesUtils.getPropertyByName("Application.xplane.aircraft.directory"));
    /** */
    public static final String GOOGLE_MAPTYPE = "Application.default.google.mapType";
    /** */
    public static final String GOOGLE_KEY = "Application.default.google.apiKey";
    /** */
    public static final String GOOGLE_ZOOM_FACTOR = "Application.default.google.zoomFactor";
    /** */
    public static final String GOOGLE_POLYLINE_COLOR = "Application.default.google.polyline.color";
    /** */
    public static final String GOOGLE_POLYLINE_WIDTH = "Application.default.google.polilyne.width";
    
    /** */
    public static final String DEFAULT_AIRPORT = "Application.xplane.default.airport";
    
    /**
     *
     */
    private CommonProperties() {
        //
    }
    
    /**
     *
     * @param key
     *            .
     * @return .
     */
    public static String getPropertyValue(final String key) {
        return PropertiesUtils.getPropertyByName(key);
    }
    
}
