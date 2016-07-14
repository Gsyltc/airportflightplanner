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
    private static final Map<String, Set<Slot<?>>> SLOTS   = new ConcurrentHashMap<String, Set<Slot<?>>>();

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
            final Set<Slot<?>> slotsSet = SLOTS.get(topicName);
            if (null != slotsSet) {
                for (final Slot<?> slot : slotsSet) {
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
    public static void addSlot(final Slot<?> slot) {
        final String topic = slot.getTopicName();
        if (SLOTS.containsKey(topic)) {
            SLOTS.get(topic).add(slot);
        } else {
            final HashSet<Slot<?>> slotsSet = new HashSet<Slot<?>>();
            slotsSet.add(slot);
            SLOTS.put(topic, slotsSet);
        }

        attachSlots(topic);
    }
}
