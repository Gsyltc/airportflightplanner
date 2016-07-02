/* @(#)TimeProcessor.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanprocessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * @author Goubaud Sylvain
 *
 */
public class TimeProcessor {
    /** The logger of this class. */
    private static final Log              LOGGER                      = LogFactory.getLog(TimeProcessor.class);

    /** */
    private static final DateTimeZone     CURRENT_TIMEZONE            = DateTimeZone.getDefault();

    /** */
    public static final Pattern           PATTERN                     = Pattern.compile("^([0-2]|[0-1][0-9]|2[0-3])((:[0-9])|(:[0-5][0-9]))?");

    /** */
    public static final PeriodFormatter   PERIOD_DISPLAYER            = new PeriodFormatterBuilder().minimumPrintedDigits(2).appendHours().appendSeparator(":")   //
            .minimumPrintedDigits(2).printZeroAlways().appendMinutes().toFormatter();

    /** */
    public static final PeriodFormatter   FLIGHTPLAN_PERIOD_DISPLAYER = new PeriodFormatterBuilder().appendHours().appendSuffix(" h ", " h ").                    //
            printZeroRarelyLast().appendMinutes().appendSuffix(" m", " m").toFormatter();

    /** */
    public static final DateTimeFormatter TIME_DISPLAYER              = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").                     //
            appendMinuteOfHour(2).toFormatter();

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
            LocalTime utcTime = getLocalTime(utcTimeString);
            return convertUtcToCurrentTimeZone(utcTime);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Time format not valid");
        }
        return null;
    }

    /**
     *
     * @param origin
     * @param duration
     * @param isAdded
     * @return
     */
    private static LocalTime calculateByTimeAndDuration(final String origin, final String duration, final boolean isAdded) {
        if (isMatch(duration)) {
            LocalTime originLocalTime = getLocalTime(origin);
            Period durationLocalTime = Period.parse(duration, PERIOD_DISPLAYER);
            if (null != originLocalTime) {
                if (isAdded) {
                    return originLocalTime.plus(durationLocalTime);
                }
                return originLocalTime.minus(durationLocalTime);
            }
        }
        return null;
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isMatch(final String value) {
        Matcher m = PATTERN.matcher(value);
        return m.matches();
    }

    /**
     *
     * @param origin
     * @param duration
     * @return
     */
    public static String getStartTime(final String origin, final String duration) {
        return calculateByTimeAndDuration(origin, duration, false).toString(TIME_DISPLAYER);
    }

    /***
     *
     * @param origin
     * @param duration
     * @return
     */
    public static String getEndTime(final String origin, final String duration) {
        return calculateByTimeAndDuration(origin, duration, true).toString(TIME_DISPLAYER);
    }

    /**
     *
     * @param origin
     * @param end
     * @return
     */
    public static String getDuration(final String origin, final String end) {
        LocalTime originLocalTime = getLocalTime(origin);
        LocalTime endLocalTime = getLocalTime(end);
        if ((null != originLocalTime) && (null != endLocalTime)) {
            return Period.fieldDifference(originLocalTime, endLocalTime).toString(PERIOD_DISPLAYER);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Error while calculating duration");
        }
        return null;
    }

    /**
     *
     * @param dateString
     * @return
     */
    public static LocalTime getLocalTime(final String dateString) {
        if (isMatch(dateString)) {
            return LocalTime.parse(dateString);
        }
        return null;
    }
}
