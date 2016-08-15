/*
 * @(#)Speed.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.domaintypes;

import java.util.regex.Pattern;

import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;

import fr.gsyltc.framework.domaintypes.DomainType;

/**
 * This class represents a speed. The system unit for this quantity is "m/s"
 * (meter per seconds).
 *
 * @author Goubaud Sylvain
 */
public class Speed extends DomainType<Velocity> {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 7196558909459902910L;

    /**
     * @param value
     * @param unit
     */
    public Speed(final Double value, final Unit<Velocity> unit) {
        super(value, unit);
        setPattern(Pattern.compile("^\\d{1,3}$"));
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public Unit<Velocity> getSIUnit() {
        return Velocity.UNIT;
    }

}
