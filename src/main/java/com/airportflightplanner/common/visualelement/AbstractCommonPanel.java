/* @(#)CommonPanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.visualelement;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.SignalModels;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class AbstractCommonPanel extends JPanel implements SlotReceiver {
    /**
     *
     */
    private static final long                     serialVersionUID = -9041291520151245877L;
    /** */
    protected transient Map<String, Signal>       signals          = new ConcurrentHashMap<String, Signal>();
    /** */
    protected Map<String, SelectionSlot<? extends Object>> slots            = new ConcurrentHashMap<String, SelectionSlot<? extends Object>>();
    /** */
    protected Map<String, ? extends Object>       attributeMap     = new ConcurrentHashMap<String, Object>();
    /** The logger of this class. */
    private static final Log                      LOGGER           = LogFactory.getLog(AbstractCommonPanel.class);

    /**
     *
     */
    protected void constructPanel() {
        attachSignal();
        attachSlotAction();
        build();
    }

    /**
     *
     */
    protected abstract void build();

    /**
     * Method to override. {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Attach Action");
        }
    }

    /**
     * Method to override. {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Attach Signal");
        }
    }

    /**
     * For Spring Injection ONLY - Need to be Oeverrided at demand. *
     *
     * @param newSlots
     *            List of Slots to attach.
     */
    public void setSlots(final List<? extends Object> newSlots) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : Set Slots : " + newSlots);
        }
    }

    /**
     *
     * @param topicName
     *            thez topic.
     * @param signal
     *            the signal.
     */
    protected final void createSignal(final String topicName, final Signal signal) {
        SignalModels.createSignal(signal);
        signals.put(topicName, signal);
    }

    /**
     *
     * @param topicName
     *            the topic atteched to a signal.
     * @return the signal.
     */
    public final Signal findSignal(final String topicName) {
        return signals.get(topicName);
    }
}
