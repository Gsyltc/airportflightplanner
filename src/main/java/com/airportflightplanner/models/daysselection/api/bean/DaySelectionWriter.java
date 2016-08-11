/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.models.daysselection.api.bean;

/**
 * @author Goubaud Sylvain
 *
 */
public interface DaySelectionWriter extends DaySelectionReader {

    /**
     * @param monday
     *            the monday to set
     */
    void setMonday(boolean monday);

    /**
     * @param tuesday
     *            the tuesday to set
     */
    void setTuesday(boolean tuesday);

    /**
     * @param wednesday
     *            the wednesday to set
     */
    void setWednesday(boolean wednesday);

    /**
     * @param thrusday
     *            the thrusday to set
     */
    void setThrusday(boolean thrusday);

    /**
     * @param friday
     *            the friday to set
     */
    void setFriday(boolean friday);

    /**
     * @param saturday
     *            the saturday to set
     */
    void setSaturday(boolean saturday);

    /**
     * @param sunday
     *            the sunday to set
     */
    void setSunday(boolean sunday);

}
