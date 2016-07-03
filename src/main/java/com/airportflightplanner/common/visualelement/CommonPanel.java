/* @(#)CommonPanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.visualelement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.SignalModels;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class CommonPanel extends JPanel implements SlotReceiver {
    /** */
    public Map<String, Signal> signals = new ConcurrentHashMap<String, Signal>();
    /** */
    public Map<String, Slot>   slots   = new ConcurrentHashMap<String, Slot>();

    /**
     *
     */
    protected void build() {
        attachSignal();
        attachSlotAction();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        // To overide if needed
    }

    @Override
    public void attachSignal(){
        // To override if needed
    }

    /**
     *
     * @param topicName
     * @param signal
     */
    protected final void createSignal(final String topicName, final Signal signal) {
        SignalModels.createSignal(signal);
        signals.put(topicName, signal);
    }

    /**
     *
     * @param topicName
     * @return
     */
    protected final Signal findSignal(final String topicName) {
        return signals.get(topicName);
    }
}
