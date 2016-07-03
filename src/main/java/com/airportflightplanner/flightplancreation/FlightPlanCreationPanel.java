/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.panels.CreationRoutePanel;
import com.airportflightplanner.flightplancreation.panels.CreationTimePanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanCreationPanel extends CommonPanel {

    /**
     *
     */
    private static final long                       serialVersionUID  = 4047549681152943474L;
    /**
     *
     */
    private final PresentationModel<FlighPlanModel> currentFlightPlan = new PresentationModel<FlighPlanModel>();


    /**
    *
    */

    /**
     */
    public FlightPlanCreationPanel() {
        build();
    }

    /**
    *
    */
    @Override
    protected void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        CreationTimePanel timePanel = new CreationTimePanel(currentFlightPlan);
        add(timePanel, "2,2,11,1");

        CreationRoutePanel routePanel = new CreationRoutePanel(currentFlightPlan);
        add(routePanel, "2,6,11,1");
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        Slot airportSlot = new Slot(TopicName.UPDATE_AIRPORT_TOPIC, this);
        airportSlot.setSlotAction(new SlotAction<String>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final String object) {
                System.out.println("FP Creation panel : " + object);

            }
        });
    }
}
