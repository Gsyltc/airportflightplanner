/* @(#)CommonPanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.visualelement;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.api.adapter.common.CommonAdapter;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.SignalModels;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.debug.FormDebugPanel;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class AbstractCommonPanel extends FormDebugPanel implements SlotReceiver {
    /**
     *
     */
    private final List<PresentationModel<?>>               presenters          = new CopyOnWriteArrayList<PresentationModel<?>>();
    /**
     *
     */
    private static final long                              serialVersionUID    = -9041291520151245877L;
    /** */
    private Map<String, Signal>                            signals             = new ConcurrentHashMap<String, Signal>();
    /** */
    protected Map<String, SelectionSlot<? extends Object>> slots               = new ConcurrentHashMap<String, SelectionSlot<? extends Object>>();
    /** */
    protected Map<String, ? extends Object>                attributeMap        = new ConcurrentHashMap<String, Object>();
    /** */
    private Map<String, CommonAdapter<?>>                     adapters            = new ConcurrentHashMap<String, CommonAdapter<?>>();
    /** The logger of this class. */
    private static final Log                               LOGGER              = LogFactory.getLog(AbstractCommonPanel.class);
    /** */
    protected static final int                             FIRST_PRESENTER     = 0;
    /** */
    protected static final String                          PREF_GROW           = "pref:grow";

    /** */
    protected static final String                          CENTER_DEFAULT_GROW = "center:default:grow";

    /**
     *
     * @param presentationModels
     *            Presentation Models list for the panel.
     */
    protected AbstractCommonPanel(final PresentationModel<?>... presentationModels) {
        super();
        if (null != presentationModels) {
            for (final PresentationModel<?> presentationModel : presentationModels) {
                presenters.add(presentationModel);
            }
        }

    }

    /**
     *
     */
    public void build() {
        attachSignal();
        attachSlotAction();
    }

    /**
     * Method to override.
     *
     * @param adapter
     */
    public void addAdapter(final CommonAdapter<?> adapter) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUG : set Adapter :" + adapter.getAdapterName());
        }
        adapters.put(adapter.getAdapterName(), adapter);
    }

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

    /**
     * @param flInfosPresenterIndex
     *            Index of the presenter to return.
     * @return The presenter.
     */
    protected PresentationModel<?> getPresenter(final int flInfosPresenterIndex) {
        return presenters.get(flInfosPresenterIndex);
    }

    /**
     * @return the adapter
     */
    protected Map<String, CommonAdapter<?>> getAdapters() {
        return Collections.unmodifiableMap(adapters);
    }

    /**
     * @param adapters
     *            the adapters to set
     */
    public void setAdapters(final Map<String, CommonAdapter<?>> adapters) {
        this.adapters = adapters;
    }

    /**
     * @param key
     * @return
     */
    public CommonAdapter<?> getAdapterByName(final String key) {
        return getAdapters().get(key);
    }

    /**
     * @return the presenters
     */
    public List<PresentationModel<?>> getPresenters() {
        return Collections.unmodifiableList(presenters);
    }

    /**
     * @return the signals
     */
    public Map<String, Signal> getSignals() {
        return Collections.unmodifiableMap(signals);
    }

    /**
     * Used for Injection only.
     *
     * @param signals
     *            the signals to set
     */
    public void setSignals(final Map<String, Signal> signals) {
        this.signals = signals;
    }

}
