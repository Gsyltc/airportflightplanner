/*
 * @(#)Distance.java
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

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;

import fr.gsyltc.framework.domaintypes.DomainType;

/**
 * This class represents a distance traveled. The system unit for this quantity
 * is "mer" (meter).
 *
 * @author Goubaud Sylvain
 */
public class Distance extends DomainType<Length> {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 7196558909459902910L;
    
    /**
     * @param value
     * @param unit
     */
    public Distance(final Double value, final Unit<Length> unit) {
        super(value, unit);
    }
    
    /**
     * @param value
     * @param unit
     */
    public Distance(final Integer value, final Unit<Length> unit) {
        super(value.doubleValue(), unit);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public Unit<Length> getSIUnit() {
        return Length.UNIT;
    }
    
}
