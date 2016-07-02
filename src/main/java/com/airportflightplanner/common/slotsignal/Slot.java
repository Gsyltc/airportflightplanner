/* @(#)Slot.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import java.util.Observable;
import java.util.Observer;

import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author DCNS
 *
 */
public class Slot implements Observer {
    /** */
    private final String       topicName;
    /** */
    private final SlotReceiver receiver;
    /** */
    private SlotAction         slotAction;

    /**
     *
     * @param topicName
     * @param receiver
     */
    public Slot(final String topicName, final SlotReceiver receiver) {
        this.topicName = topicName;
        this.receiver = receiver;
        SignalModels.addSlot(this);
    }

    /**
     * @return
     *
     */
    SlotReceiver getReceiver() {
        return receiver;
    }

    /**
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(final Observable o, final Object arg) {
        slotAction.doAction(arg);
    }

    /**
     *
     * @return
     */
    String getTopicName() {
        return topicName;
    }

    /**
     *
     * @param slotAction
     */
    public void setSlotAction(final SlotAction slotAction) {
        this.slotAction = slotAction;

    }

}
