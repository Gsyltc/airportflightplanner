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

import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.SignalModels;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class CommonPanel extends JPanel implements SlotReceiver {
  /**
   *
   */
  private static final long                  serialVersionUID = -9041291520151245877L;
  /** */
  public Map<String, Signal>                 signals          = new ConcurrentHashMap<String, Signal>();
  /** */
  public Map<String, Slot<? extends Object>> slots            = new ConcurrentHashMap<String, Slot<? extends Object>>();

  public Map<String, ? extends Object>       attributeMap     = new ConcurrentHashMap<String, Object>();

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
  public void attachSignal() {
    // To override if needed
  }

  /**
   * For Spring Injection ONLY - Need to be Oeverrided at demand
   *
   * @param slots
   *          List of Slots to attach
   */
  public void setSlots(final List<? extends Object> slots) {
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
  public final Signal findSignal(final String topicName) {
    return signals.get(topicName);
  }
}
