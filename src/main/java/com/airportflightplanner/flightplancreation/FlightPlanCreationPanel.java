/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.api.model.GoogleMapWriter;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.panels.CreationRoutePanel;
import com.airportflightplanner.flightplancreation.panels.CreationTimePanel;
import com.airportflightplanner.flightplancreation.panels.GoogleMapPane;
import com.google.maps.model.EncodedPolyline;
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
    private static final long                         serialVersionUID  = 4047549681152943474L;
    /** */
    protected static final int                        DEPARTURE_POINT   = 0;
    /**
     *
     */
    private final PresentationModel<FlighPlanModel>   currentFlightPlan = new PresentationModel<FlighPlanModel>();
    /**
    *
    */
    protected final PresentationModel<GoogleMapModel> googleMapModel    = new PresentationModel<GoogleMapModel>();

    /** */
    protected GoogleMapPane                           googleMap;

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
     * {@inheritDoc}
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

        add(createMap(), "2,10,11,1");
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        Slot<String> airportSlot = new Slot<String>(TopicName.UPDATE_AIRPORT_TOPIC, this);
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

        Slot<FlightPlanReader> slot = new Slot<>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
                if (null != flightPlanReader) {
                    GoogleMapWriter googleMapWriter = new GoogleMapModel();
                    googleMapModel.setBean((GoogleMapModel) googleMapWriter);
                    googleMapWriter.setMarkers(GeographicUtils.getSteerPoints(flightPlanReader.getSteerPoints()));
                    EncodedPolyline polyline = GeographicUtils.getEncodePolyline(flightPlanReader.getSteerPoints());
                    googleMapWriter.setEncodedPolyline(polyline);
                }
            }
        });
    }

    /**
     *
     * @param googleMapModel
     * @return
     */
    private JPanel createMap() {
        JPanel panel = new JPanel();
        panel.setSize(400, 400);
        panel.setMinimumSize(new Dimension(400, 400));

        googleMap = new GoogleMapPane(googleMapModel);
        googleMap.setDimension(new Rectangle(0, 0, 400, 400));
        googleMap.setSize(400, 400);
        panel.add(googleMap);

        return panel;
    }
}
