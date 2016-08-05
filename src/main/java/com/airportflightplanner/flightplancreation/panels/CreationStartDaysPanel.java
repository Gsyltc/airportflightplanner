/*
 * @(#)CreationStartDaysPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 4 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.common.types.StartDays;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
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

    /**
     * @param presenter
     *
     */
    public CreationStartDaysPanel(final PresentationModel<DaySelectionReader> presenter) {
        super(presenter);
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
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Stardays presenter " + evt.getPropertyName() + "=" + evt.getNewValue());
                }
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
     * {@inheritDoc}
     */
    @Override
    public void createSlots() {
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
                final PresentationModel<DaySelectionReader> dayPresenter = //
                        (PresentationModel<DaySelectionReader>) getPresenter(DAYS_PRESENTER);
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
                switch (arg) {
                case VALIDATE:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Validate Days");
                    }
                    break;
                
                case CANCEL:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Cancel Days");
                    }
                    break;
                
                case REFRESH:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Refresh Days");
                    }
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
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.FRIDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.FRIDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createMondayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.MONDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.MONDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSaturdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SATURDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.SATURDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSundayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SUNDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createThrusdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.THRUSDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.THRUSDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createTuesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.TUESDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.TUESDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createWednesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.WEDNESDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.WEDNESDAY.toString());
    }

}
