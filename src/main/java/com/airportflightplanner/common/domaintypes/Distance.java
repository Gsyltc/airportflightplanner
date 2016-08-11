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

import javax.measure.quantity.Length;
import javax.measure.quantity.Quantity;
import javax.measure.unit.NonSI;
import javax.measure.unit.Unit;

/**
 * This interface represents a distance traveled. The system unit for this
 * quantity is "nm" (nautical miles).
 *
 * @author Goubaud Sylvain
 */
public interface Distance extends Quantity {
    
    
    /**
     * Holds the SI unit (SystÃ¨me International d'UnitÃ©s) for this quantity.
     */
    public final static Unit<Length> UNIT = NonSI.NAUTICAL_MILE;
    
}
