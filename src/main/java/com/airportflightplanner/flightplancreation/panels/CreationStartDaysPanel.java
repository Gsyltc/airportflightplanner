/*
 * @(#)CreationStartDaysPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.JCheckBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.common.types.StartDays;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.signals.Signal;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationStartDaysPanel extends AbstractCommandablePanel {
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(CreationStartDaysPanel.class);
    /**
     *
     */
    private static final long serialVersionUID = 3382772953500242522L;
    /** */
    private static final int DAYS_PRESENTER = 0;
    /** */
    private static final int FP_PRESENTER = 1;
    /** Set of start days. */
    protected final Set<StartDays> startDays = new CopyOnWriteArraySet<StartDays>();
    
    /**
     * Create the panel.
     *
     * @param presenter
     *            Day selection presenter.
     * @param fpPresenter
     *            flight plan presenter.
     *
     */
    public CreationStartDaysPanel(final PresentationModel<DaySelectionReader> presenter, final PresentationModel<?> fpPresenter) {
        super(presenter, fpPresenter);
    }
    
    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, }, //
                new RowSpec[] { //
                        FormSpecs.PREF_ROWSPEC, })); //
        
        final PresentationModel<DaySelectionReader> dayPresenter = //
                (PresentationModel<DaySelectionReader>) getPresenter(DAYS_PRESENTER);
        
        dayPresenter.addPropertyChangeListener(PresentationModel.PROPERTY_BUFFERING, new PropertyChangeListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(evt.getPropertyName() + "=" + evt.getNewValue());
                }
                final Signal signal = findSignal(TopicName.FP_MODIFIED_TOPIC);
                signal.fireSignal(evt.getNewValue());
            }
        });
        
        add(createMondayCb(dayPresenter), "1, 1");
        add(createTuesdayCb(dayPresenter), "3, 1");
        add(createWednesdayCb(dayPresenter), "5, 1");
        add(createThrusdayCb(dayPresenter), "7, 1");
        add(createFridayCb(dayPresenter), "9, 1");
        add(createSaturdayCb(dayPresenter), "11, 1");
        add(createSundayCb(dayPresenter), "13, 1");
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSlots() {
        final PresentationModel<DaySelectionReader> dayPresenter = //
                (PresentationModel<DaySelectionReader>) getPresenter(DAYS_PRESENTER);
        
        super.createSlots();
        final Slot slot = new Slot(TopicName.FP_TABLE_SELECTED_TOPIC, getClass().getSimpleName());
        slot.registerSlot();
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 842551482406987966L;
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(TopicName.FP_TABLE_SELECTED_TOPIC + " received");
                }
                dayPresenter.triggerFlush();
            }
        });
        
        final Slot validateSlot = new Slot(TopicName.VALIDATION_TOPIC, getClass().getSimpleName());
        validateSlot.registerSlot();
        validateSlot.setSlotAction(new SlotAction<ActionTypes>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = -7327592856033503598L;
            
            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void doAction(final ActionTypes arg) {
                final PresentationModel<DaySelectionReader> fpPresenter = //
                        (PresentationModel<DaySelectionReader>) getPresenter(FP_PRESENTER);
                switch (arg) {
                case VALIDATE:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Validate Days : " + startDays);
                    }
                    dayPresenter.triggerCommit();
                    final Set<StartDays> fpStartDays = (Set<StartDays>) fpPresenter.getBufferedValue(
                            FlightPlanProperties.START_DAYS);
                    fpStartDays.clear();
                    fpStartDays.addAll(startDays);
                    fpPresenter.triggerCommit();
                    
                    break;
                
                case CANCEL:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Cancel Days");
                    }
                    dayPresenter.triggerFlush();
                    break;
                
                case REFRESH:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Refresh Days");
                    }
                    dayPresenter.triggerFlush();
                    break;
                default:
                    break;
                }
                
            }
            
        });
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createFridayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.FRIDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.FRIDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.FRIDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createMondayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.MONDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.MONDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.MONDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSaturdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SATURDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.SATURDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.SATURDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSundayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SUNDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.SUNDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createThrusdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.THRUSDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.THRUSDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.THRUSDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createTuesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.TUESDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.TUESDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.TUESDAY.toString());
    }
    
    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createWednesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final BufferedValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.WEDNESDAY);
        value.addValueChangeListener(new StartDaysPropertyChangeListener(StartDays.WEDNESDAY));
        return BasicComponentFactory.createCheckBox(value, StartDays.WEDNESDAY.toString());
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSignals() {
        super.createSignals();
        attachSignal(TopicName.FP_MODIFIED_TOPIC);
    }
    
    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected final class StartDaysPropertyChangeListener implements PropertyChangeListener {
        
        
        /** The start day. */
        private final StartDays startday;
        
        /**
         * Constructor.
         *
         * @param newStartDay
         *            the start day.
         */
        StartDaysPropertyChangeListener(final StartDays newStartDay) {
            startday = newStartDay;
        }
        
        /**
         *
         * {@inheritDoc}.
         */
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            final boolean isSelected = (boolean) evt.getNewValue();
            if (getStartDays().contains(getStartday()) && !isSelected) {
                startDays.remove(getStartday());
            } else {
                if (isSelected) {
                    startDays.add(getStartday());
                }
            }
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Update starts days : " + startDays);
            }
        }
        
        /**
         * @return the startday
         */
        private StartDays getStartday() {
            return startday;
        }
        
    }

    /**
     * @return the startDays
     */
    protected Set<StartDays> getStartDays() {
        return startDays;
    }
}
