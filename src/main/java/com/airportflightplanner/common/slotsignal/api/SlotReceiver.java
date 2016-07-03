/* @(#)Slot.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal.api;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SlotReceiver {

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
