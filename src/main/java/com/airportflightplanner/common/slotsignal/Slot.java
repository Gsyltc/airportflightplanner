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
 * @author Goubaud Sylvain
 *
 */
public class Slot<E extends Object> implements Observer {
    /** */
    private final String       topicName;
    /** */
    private final SlotReceiver receiver;
    /** */
    private SlotAction<E>      slotAction;

    /**
     * A slot can listen an event fire by a signal
     *
     * @param topicName
     *            define the topic to listen
     * @param receiver
     *            the receiver is the object with the action to run when a
     *            signal is received
     */
    public Slot(final String topicName, final SlotReceiver receiver) {
        this.topicName = topicName;
        this.receiver = receiver;
        SignalModels.addSlot(this);
    }

    /**
     * @return the receiver
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
        slotAction.doAction((E) arg);
    }

    /**
     * Return the topic name of the slot
     *
     * @return the topis
     */
    String getTopicName() {
        return topicName;
    }

    /**
     * Define the action for the slot
     *
     * @param slotAction
     */
    public void setSlotAction(final SlotAction<E> slotAction) {
        this.slotAction = slotAction;

    }

}
