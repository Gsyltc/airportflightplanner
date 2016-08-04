/*
 * @(#)TimeUtils.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 4 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.time;

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
public final class TimeUtils {
    
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(TimeUtils.class);
    
    /** */
    private static final DateTimeZone CURRENT_TIMEZONE = DateTimeZone.getDefault();
    
    /** */
    public static final Pattern PATTERN = Pattern.compile("^([0-2]|[0-1][0-9]|2[0-3])((:[0-9])|(:[0-5][0-9]))?");
    
    /** */
    public static final PeriodFormatter PERIOD_DISPLAYER = //
            new PeriodFormatterBuilder().minimumPrintedDigits(2).appendHours().appendSeparator(":") //
                    .minimumPrintedDigits(2).printZeroAlways().appendMinutes().toFormatter();
    
    /** */
    public static final PeriodFormatter FP_PERIOD_DISPLAYER = //
            new PeriodFormatterBuilder().appendHours().appendSuffix(" h ", " h "). //
                    printZeroRarelyLast().appendMinutes().appendSuffix(" m", " m").toFormatter();
    
    /** */
    public static final DateTimeFormatter TIME_DISPLAYER = //
            new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":"). //
                    appendMinuteOfHour(2).toFormatter();
    
    /**
     * Protected Constructor.
     */
    private TimeUtils() {
        //
    }
    
    /**
     *
     * @param utcTime
     * @return
     */
    public static LocalTime convertUtcToCurrentTimeZone(final LocalTime utcTime) {
        final DateTime currentDt = utcTime.toDateTimeToday(DateTimeZone.UTC);
        return currentDt.toDateTime(CURRENT_TIMEZONE).toLocalTime();
    }
    
    /**
     *
     * @param utcTimeString
     * @return
     */
    public static LocalTime convertUtcToCurrentTimeZone(final String utcTimeString) {
        LocalTime result = null;
        try {
            final LocalTime utcTime = getLocalTime(utcTimeString);
            result = convertUtcToCurrentTimeZone(utcTime);
        } catch (final IllegalArgumentException e) {
            LOGGER.info("Time format not valid");
        }
        return result;
    }
    
    /**
     *
     * @param origin
     * @param duration
     * @param isAdded
     * @return
     */
    private static LocalTime calculateByTimeAndDuration(final String origin, final String duration, final boolean isAdded) {
        LocalTime result = null;
        if (isMatch(duration)) {
            final LocalTime originLocalTime = getLocalTime(origin);
            final Period durationLocalTime = Period.parse(duration, PERIOD_DISPLAYER);
            if (null != originLocalTime) {
                if (isAdded) {
                    result = originLocalTime.plus(durationLocalTime);
                } else {
                    result = originLocalTime.minus(durationLocalTime);
                }
            }
        }
        return result;
    }
    
    /**
     *
     * @param value
     * @return
     */
    private static boolean isMatch(final String value) {
        final Matcher matcher = PATTERN.matcher(value);
        return matcher.matches();
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
        String result = "";
        final LocalTime originLocalTime = getLocalTime(origin);
        final LocalTime endLocalTime = getLocalTime(end);
        if (null != originLocalTime && null != endLocalTime) {
            result = Period.fieldDifference(originLocalTime, endLocalTime).toString(PERIOD_DISPLAYER);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Error while calculating duration");
        }
        return result;
    }
    
    /**
     *
     * @param dateString
     * @return
     */
    public static LocalTime getLocalTime(final String dateString) {
        LocalTime result = null;
        if (isMatch(dateString)) {
            result = LocalTime.parse(dateString);
        }
        return result;
    }
}
