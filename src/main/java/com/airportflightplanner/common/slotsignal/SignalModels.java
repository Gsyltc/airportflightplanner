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

/**
 * @author Goubaud Sylvain
 *
 */
public final class SignalModels {

    /**
     *
     */
    private static final Map<String, Signal>       SIGNALS = new ConcurrentHashMap<String, Signal>();
    /**
     *
     */
    private static final Map<String, Set<SelectionSlot<?>>> SLOTS   = new ConcurrentHashMap<String, Set<SelectionSlot<?>>>();

    /**
     * Protect constructor.
     */
    private SignalModels() {
        // Utils class
    }

    /**
     * Attach a signal.
     *
     * @param signal
     *            signal to attach.
     */
    public static void createSignal(final Signal signal) {
        if (!SIGNALS.containsKey(signal.getTopicName())) {
            SIGNALS.put(signal.getTopicName(), signal);
            attachSlots(signal.getTopicName());
        }
    }

    /**
     * Attach a slot to a signal.
     *
     * @param topicName
     *            the topic to listen for the signal.
     */
    public static void attachSlots(final String topicName) {
        final Signal signal = SIGNALS.get(topicName);
        if (null != signal) {
            final Set<SelectionSlot<?>> slotsSet = SLOTS.get(topicName);
            if (null != slotsSet) {
                for (final SelectionSlot<?> slot : slotsSet) {
                    signal.addObserver(slot);
                }
            }
        }
    }

    /**
     * Add a slot to the slot list.
     *
     * @param slot
     *            the slot to add.
     */
    public static void addSlot(final SelectionSlot<?> slot) {
        final String topic = slot.getTopicName();
        if (SLOTS.containsKey(topic)) {
            SLOTS.get(topic).add(slot);
        } else {
            final HashSet<SelectionSlot<?>> slotsSet = new HashSet<SelectionSlot<?>>();
            slotsSet.add(slot);
            SLOTS.put(topic, slotsSet);
        }

        attachSlots(topic);
    }
}
