/* @(#)SignalModels.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Goubaud Sylvain
 *
 */
public class SignalModels {
    /** The logger of this class. */
    private static final Log                    LOGGER  = LogFactory.getLog(SignalModels.class);

    /**
     *
     */
    private static final Map<String, Signal>    signals = new ConcurrentHashMap<String, Signal>();
    /**
    *
    */
    private static final Map<String, Set<Slot>> slots   = new ConcurrentHashMap<String, Set<Slot>>();

    /**
     * Create a signal
     *
     * @param signal
     */
    public static void createSignal(final Signal signal) {
        if (!signals.containsKey(signal.getTopicName())) {
            signals.put(signal.getTopicName(), signal);
            attachSlots(signal.getTopicName());
        } else {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error while creating signal : " + signal.getTopicName() + " already defined");
            }
        }
    }

    /**
     * Attach a slot to a signal
     *
     * @param topicName
     */
    public static void attachSlots(final String topicName) {
        Signal signal = signals.get(topicName);
        if (null != signal) {
            Set<Slot> slotsSet = slots.get(topicName);
            if (null != slotsSet) {
                for (Slot slot : slotsSet) {
                    signal.addObserver(slot);
                }
            }
        }
    }

    /**
     * Add a slot to the slot list
     *
     * @param slot
     */
    static void addSlot(final Slot slot) {
        String topic = slot.getTopicName();
        if (slots.containsKey(topic)) {
            slots.get(topic).add(slot);
        } else {
            HashSet<Slot> slotsSet = new HashSet<Slot>();
            slotsSet.add(slot);
            slots.put(topic, slotsSet);
        }

        attachSlots(topic);
    }
}
