/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.selection.Slot;
import com.airportflightplanner.common.selection.TopicName;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.panels.CreationRoutePanel;
import com.airportflightplanner.flightplancreation.panels.CreationTimePanel;
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
    private static final long              serialVersionUID = 4047549681152943474L;
    /**
     *
     */
    private final FlighPlanCollectionModel flightPlansCollection;

    /**
    *
    */

    /**
     * @param flightPlansCollection
     *
     */
    public FlightPlanCreationPanel(final FlighPlanCollectionModel flightPlansCollection) {
        this.flightPlansCollection = flightPlansCollection;
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

        CreationTimePanel timePanel = new CreationTimePanel();
        add(timePanel, "2,2,11,1");

        CreationRoutePanel routePanel = new CreationRoutePanel();
        add(routePanel, "2,6,11,1");

        Slot slot = new Slot(TopicName.UPDATE_AIRPORT_TOPIC, this);
        slot.attachSignal();

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void slotAction(final Object object) {
        System.out.println("Test 2 : " + object);
    }

}
