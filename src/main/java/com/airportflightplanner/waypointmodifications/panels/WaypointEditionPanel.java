/*
 * @(#)WaypointEditionPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 14 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.waypointmodifications.panels;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Period;

import com.airportflightplanner.common.processors.GeographicProcessor;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanProperties;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class WaypointEditionPanel extends AbstractCommandablePanel {
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(WaypointEditionPanel.class);

    /**
     *
     */
    private static final long serialVersionUID = 6378943645259250488L;
    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int[] COLUMN_GROUP = new int[] { 2, 4 };
    /** the steer points presenter index. */
    private static final int STEERPOINTS_PRESENTER = 1;

    /**
     * Create the panel.
     *
     * @param currentFpBean
     * @param steerpointModel
     */
    // formatter:off
    public WaypointEditionPanel(final Model currentFpBean, final Model steerpointModel) { // NOPMD
        // by
        // sylva
        // on 31/07/16
        // 15:43
        // formatter:on
        super(new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFpBean), //
                new PresentationModel<>((SteerPointsCollectionReader) steerpointModel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, });

        formLayout.setColumnGroups(new int[][] { COLUMN_GROUP });
        setLayout(formLayout);

        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        final PresentationModel<SteerPointsCollectionReader> stpPresenter = (PresentationModel<SteerPointsCollectionReader>) getPresenter(
                STEERPOINTS_PRESENTER);
        add(createWaypointTextPanel(presenter, stpPresenter), "2, 2,3,1,fill,fill");
        add(createResumePanel(presenter, stpPresenter), "2, 4,3,1,fill,fill");
        // add(createDaysSelectionPanel(), "2, 4, 3, 1, fill, fill");

    }

    /**
     * Create the text panel area.
     *
     * @param presenter
     *            the flight plan presenter
     * @param stpPresenter
     * @return the panel
     */
    private WaypointTextPanel createWaypointTextPanel(final PresentationModel<FlightPlanReader> presenter,
            final PresentationModel<SteerPointsCollectionReader> stpPresenter) {
        final WaypointTextPanel panel = new WaypointTextPanel(presenter, stpPresenter);
        panel.build();

        return panel;
    }

    /**
     * Create the text panel area.
     *
     * @param presenter
     *            the flight plan presenter
     * @param stpPresenter
     * @return the panel
     */
    private FlightResumePanel createResumePanel(final PresentationModel<FlightPlanReader> presenter,
            final PresentationModel<SteerPointsCollectionReader> stpPresenter) {
        final FlightResumePanel panel = new FlightResumePanel(presenter, stpPresenter);
        panel.build();

        return panel;
    }

    /**
     *
     * {@inheritDoc}.
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
            private static final long serialVersionUID = 1240749169986714101L;

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) //
                getPresenter(FP_PRESENTER);

                final SteerPointsCollectionModel bean = new SteerPointsCollectionModel();
                bean.setCurrentFlightPlan(flightPlanReader);
                for (final SteerPointReader steerPoint : flightPlanReader.getSteerPoints()) {
                    bean.addSteerPoint(steerPoint);
                }
                final PresentationModel<SteerPointsCollectionReader> stpPresenter = (PresentationModel<SteerPointsCollectionReader>) getPresenter(
                        STEERPOINTS_PRESENTER);
                stpPresenter.setBean(bean);
            }
        });

        final Slot validationSlot = new Slot(TopicName.VALIDATION_TOPIC, getClass().getSimpleName());
        validationSlot.registerSlot();

        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        validationSlot.setSlotAction(new SlotAction<ActionTypes>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 1289014075739897031L;

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final ActionTypes action) {
                switch (action) {
                case VALIDATE:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "Validate");
                    }
                    final BufferedValueModel steerpoints = presenter.getBufferedModel(FlightPlanProperties.STEERPOINTS_LIST);
                    presenter.setValue(FlightPlanProperties.DURATION, new Period(GeographicProcessor.getFlightTime(
                            (List<SteerPointReader>) steerpoints.getValue())));
                    presenter.triggerCommit();
                    break;
                
                case CANCEL:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "CANCEL");
                    }
                    presenter.triggerFlush();
                    break;
                
                case REFRESH:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "REFREASH");
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("");
                    }
                    break;
                default:
                    break;
                }
            }
        });
    }
}
