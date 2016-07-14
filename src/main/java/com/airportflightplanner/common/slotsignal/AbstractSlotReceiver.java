/* @(#)AbstractSenderReceiver.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import java.util.List;

import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public class AbstractSlotReceiver implements SlotReceiver {

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

    /**
     * For Spring Injection ONLY - Need to be Oeverrided at demand
     *
     * @param slots
     *            List of Slots to attach
     */
    public void setSlots(final List<SelectionSlot<? extends Object>> slots) {
        // To override if needed
    }

}
