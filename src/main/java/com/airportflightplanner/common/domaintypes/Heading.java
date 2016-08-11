/*
 * @(#)Distance.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 9 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.domaintypes;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Quantity;
import javax.measure.unit.NonSI;
import javax.measure.unit.Unit;

/**
 * This interface represents a heading angle. The system unit for this quantity
 * is "°" (degree).
 *
 * @author Goubaud Sylvain
 */
public interface Heading extends Quantity {
    
    
    /**
     * Holds the SI unit (SystÃ¨me International d'UnitÃ©s) for this quantity.
     */
    public final static Unit<Angle> UNIT = NonSI.DEGREE_ANGLE;
    
}
