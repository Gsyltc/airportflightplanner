/* @(#)CommonProperties.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.utils.properties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Goubaud Sylvain
 *
 */
public class CommonProperties {

    /** */
    public static final Path ROUTES_DIRECTORY = Paths.get(PropertiesLoader.getPropertyByName("Application.xplane.directory") + //
            PropertiesLoader.getPropertyByName("Application.xplane.routes.directory"));
}
