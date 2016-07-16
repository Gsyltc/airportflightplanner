/*
 * @(#)SelectionSlot.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.slotsignal;

import java.util.Observable;
import java.util.Observer;

import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 * @param <E>
 *
 */
public class SelectionSlot<E extends Object> implements Observer {
    /** */
    private final String            topicName;
    /** */
    private final SlotReceiver      receiver;
    /** */
    private transient SlotAction<E> slotAction;

    /**
     * A slot can listen an event fire by a signal.
     *
     * @param newTopicName
     *            define the topic to listen.
     * @param newReceiver
     *            the receiver is the object with the action to run when a
     *            signal is received.
     */
    public SelectionSlot(final String newTopicName, final SlotReceiver newReceiver) {
        this.topicName = newTopicName;
        this.receiver = newReceiver;
        SignalModels.addSlot(this);
    }

    /**
     * @return the receiver
     *
     */
    public SlotReceiver getReceiver() {
        return receiver;
    }

    /**
     *
     * @param observable
     * @param arg
     */
    @Override
    public void update(final Observable observable, final Object arg) {
        slotAction.doAction((E) arg);
    }

    /**
     * Return the topic name of the slot
     *
     * @return the topis
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Define the action for the slot
     *
     * @param newSlotAction
     */
    public void setSlotAction(final SlotAction<E> newSlotAction) {
        this.slotAction = newSlotAction;

    }

}
