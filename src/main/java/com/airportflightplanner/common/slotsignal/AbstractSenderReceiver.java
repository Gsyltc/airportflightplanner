/* @(#)AbstractSenderReceiver.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public class AbstractSenderReceiver implements SlotReceiver {

    /**
     *
     */
    public void init() {
        attachSignal();
        attachSlotAction();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        // To Ovverride if needed

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        // To Ovverride if needed

    }

}
