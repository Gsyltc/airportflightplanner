/*
 * @(#)DaysSelectionModel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.models.daysselection;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionWriter;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class DaysSelectionModel extends Model implements DaySelectionWriter {
    /** */
    private boolean monday;
    /** */
    private boolean tuesday;
    /** */
    private boolean wednesday;
    /** */
    private boolean thrusday;
    /** */
    private boolean friday;
    /** */
    private boolean saturday;
    /** */
    private boolean sunday;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isMonday() {
        return monday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isTuesday() {
        return tuesday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isWednesday() {
        return wednesday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isThrusday() {
        return thrusday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isFriday() {
        return friday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSaturday() {
        return saturday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSunday() {
        return sunday;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setMonday(final boolean value) {
        final boolean oldValue = isMonday();
        if (monday != value) {
            monday = value;
            firePropertyChange(DaySelectionProperties.MONDAY, oldValue, monday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setTuesday(final boolean value) {
        final boolean oldValue = isTuesday();
        if (tuesday != value) {
            tuesday = value;
            firePropertyChange(DaySelectionProperties.TUESDAY, oldValue, tuesday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setWednesday(final boolean value) {
        final boolean oldValue = isWednesday();
        if (wednesday != value) {
            wednesday = value;
            firePropertyChange(DaySelectionProperties.WEDNESDAY, oldValue, wednesday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setThrusday(final boolean value) {
        final boolean oldValue = isThrusday();
        if (thrusday != value) {
            thrusday = value;
            firePropertyChange(DaySelectionProperties.THRUSDAY, oldValue, thrusday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFriday(final boolean value) {
        final boolean oldValue = isFriday();
        if (friday != value) {
            friday = value;
            firePropertyChange(DaySelectionProperties.FRIDAY, oldValue, friday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSaturday(final boolean value) {
        final boolean oldValue = isSaturday();
        if (saturday != value) {
            saturday = value;
            firePropertyChange(DaySelectionProperties.SATURDAY, oldValue, saturday);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSunday(final boolean value) {
        final boolean oldValue = isSunday();
        if (sunday != value) {
            sunday = value;
            firePropertyChange(DaySelectionProperties.SUNDAY, oldValue, sunday);
        }
    }
}
