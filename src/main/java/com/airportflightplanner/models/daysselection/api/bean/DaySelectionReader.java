/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.models.daysselection.api.bean;

import java.io.Serializable;

/**
 * @author Goubaud Sylvain
 *
 */
public interface DaySelectionReader extends Serializable {

    /**
     * @return the monday
     */
    boolean isMonday();

    /**
     * @return the tuesday
     */
    boolean isTuesday();

    /**
     * @return the wednesday
     */
    boolean isWednesday();

    /**
     * @return the thrusday
     */
    boolean isThrusday();

    /**
     * @return the friday
     */
    boolean isFriday();

    /**
     * @return the saturday
     */
    boolean isSaturday();

    /**
     * @return the sunday
     */
    boolean isSunday();

}
