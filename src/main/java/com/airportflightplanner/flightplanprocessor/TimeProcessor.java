/* @(#)TimeProcessor.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanprocessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

/**
 * @author Goubaud Sylvain
 *
 */
public class TimeProcessor {
    /** The logger of this class. */
    private static final Log          LOGGER           = LogFactory.getLog(TimeProcessor.class);

    /** */
    private static final DateTimeZone CURRENT_TIMEZONE = DateTimeZone.getDefault();

    /**
     *
     * @param utcTime
     * @return
     */
    public static LocalTime convertUtcToCurrentTimeZone(final LocalTime utcTime) {
        DateTime currentDt = utcTime.toDateTimeToday(DateTimeZone.UTC);
        LocalTime current = currentDt.toDateTime(CURRENT_TIMEZONE).toLocalTime();
        return current;
    }

    /**
     *
     * @param utcTimeString
     * @return
     */
    public static LocalTime convertUtcToCurrentTimeZone(final String utcTimeString) {
        try {
            LocalTime utcTime = LocalTime.parse(utcTimeString);
            return convertUtcToCurrentTimeZone(utcTime);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Time format not valid");
        }
        return null;
    }
}
