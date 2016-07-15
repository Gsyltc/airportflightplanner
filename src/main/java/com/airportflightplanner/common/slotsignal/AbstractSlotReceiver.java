/* @(#)AbstractSenderReceiver.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class AbstractSlotReceiver implements SlotReceiver {
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(AbstractSlotReceiver.class);

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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Attach Slot Action - " + getClass().getSimpleName());
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Attach Signal - " + getClass().getSimpleName());
        }
    }

    /**
     * For Spring Injection ONLY - Need to be Oeverrided at demand
     *
     * @param slots
     *            List of Slots to attach
     */
    public void setSlots(final List<SelectionSlot<? extends Object>> slots) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Set Slot - " + getClass().getSimpleName());
        }
    }

}
