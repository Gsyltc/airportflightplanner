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

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Goubaud Sylvain
 *
 */
public class Signal extends Observable implements Serializable {

    /**
     *
     */
    private static final long                serialVersionUID = -3262485442303240084L;
    /** */
    private final String                     topicName;
    /** */
    private List<SelectionSlot<?>> slotList;

    /**
     *
     * @param topicName
     */
    public Signal(final String topicName) {
        super();
        this.topicName = topicName;
        slotList = new CopyOnWriteArrayList<SelectionSlot<?>>();
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

    /**
     * @param slotList
     *            the slotList to set
     */
    protected void setSlotList(final List<SelectionSlot<?>> slotList) {
        this.slotList = slotList;
    }
}
