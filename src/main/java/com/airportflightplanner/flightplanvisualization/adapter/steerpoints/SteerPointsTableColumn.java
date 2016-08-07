/*
 * @(#)SteerPointsTableColumn.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import fr.gsyltc.framework.utils.internationalizer.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum SteerPointsTableColumn {
    /** */
    LATITUDE(0),
    /** */
    LONGITUDE(1),
    /** */
    VELOCITY(2),
    /** */
    ALTITUDE(3);

    /** */
    private int value;
    /** */
    private static final String PREFIX = "SteerPointsTableColumn.";

    /**
     *
     * @param column
     * @return
     */
    public static int getColumnNumber(final SteerPointsTableColumn column) {
        return column.value;
        
    }
    
    /**
     * Get the internationalized name.
     *
     * @param columnIndex
     * @return
     */
    public static String getName(final int columnIndex) {
        return Internationalizer.getI18String(PREFIX + valueOf(columnIndex).name());
    }
    
    /**
     *
     * @param columnNumber
     * @return
     */
    public static SteerPointsTableColumn valueOf(final int columnNumber) {
        SteerPointsTableColumn result = null;
        for (final SteerPointsTableColumn column : SteerPointsTableColumn.values()) {
            if (column.ordinal() == columnNumber) {
                result = column;
            }
        }
        return result;
    }
    
    /**
     *
     * @param newValue
     */
    SteerPointsTableColumn(final int newValue) {
        value = newValue;
    }
}
