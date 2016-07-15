/* @(#)Signal.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Goubaud Sylvain
 *
 */
public class Signal extends Observable {

    /** */
    private final String              topicName;
    /** */
    private final List<SelectionSlot<?>> slotList = new CopyOnWriteArrayList<SelectionSlot<?>>();

    /**
     *
     * @param topicName
     */
    public Signal(final String topicName) {
        this.topicName = topicName;
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
}
