/*
 * @(#)Signal.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.slotsignal;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Goubaud Sylvain
 *
 */
public class Signal extends Observable {

    /** */
    private final String                 topicName;
    /** */
    private final List<SelectionSlot<?>> slotList = new CopyOnWriteArrayList<SelectionSlot<?>>();

    /**
     *
     * @param topicName
     */
    public Signal(final String topicName) {
        super();
        this.topicName = topicName;
    }

    /**
     *
     * @return
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     *
     * @param object
     */
    public void fireSignal(final Object object) {
        setChanged();
        notifyObservers(object);
    }

    /**
     *
     * @param slot
     */
    public void createSignal(final SelectionSlot<?> slot) {
        slotList.add(slot);
        addObserver(slot);
    }

    /**
     * Return all the slot attached to the signal.
     *
     * @return the slotList
     */
    public List<SelectionSlot<?>> getSlotList() {
        return Collections.unmodifiableList(slotList);
    }
}
