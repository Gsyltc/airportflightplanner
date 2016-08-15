/*
 * @(#)BankingAngle.java
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

import javax.measure.quantity.Angle;
import javax.measure.unit.Unit;

import fr.gsyltc.framework.domaintypes.DomainType;

/**
 * This class represents a heading angle. The system unit for this quantity is
 * "°" (degree).
 *
 * @author Goubaud Sylvain
 */
public class BankingAngle extends DomainType<Angle> {
    
    
    /**
    *
    */
    private static final long serialVersionUID = 7196558909459902910L;
    
    /**
     * @param value
     * @param unit
     */
    public BankingAngle(final Double value, final Unit<Angle> unit) {
        super(value, unit);
    }
    
    /**
     *
     * @return
     */
    @Override
    public Unit<Angle> getSIUnit() {
        return Angle.UNIT;
    }
}
