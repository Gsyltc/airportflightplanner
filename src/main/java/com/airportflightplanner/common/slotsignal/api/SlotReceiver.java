/* @(#)Slot.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal.api;

import java.io.Serializable;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SlotReceiver extends Serializable{

    /**
     *
     */
    void attachSlotAction();

    /**
     *
     *
     */
    void attachSignal();

}
