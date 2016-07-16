/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.JPanel;

import com.airportflightplanner.common.api.adapter.CommonAdapter;
import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapWriter;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.panels.CreationFlightInfosPanel;
import com.airportflightplanner.flightplancreation.panels.CreationOptionsPanel;
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
public class FlightPlanCreationPanel extends AbstractCommonPanel {

    /** */
    protected static final int         DEPARTURE_POINT        = 0;
    /**
     *
     */
    private static final long          serialVersionUID       = 4047549681152943474L;
    /** */
    private static final int           DEFAULT_HEIGHT         = 400;
    /** */
    private static final int           DEFAULT_WIDTH          = 400;
    /** */
    protected transient GoogleMapPane  googleMap;
    /** */
    private Map<String, CommonAdapter> adapters;
    /** */
    private static final int           GOOGLE_PRESENTER_INDEX = 1;

    /**
     * @param newCurrentFlightPlan
     */
    public FlightPlanCreationPanel(final PresentationModel<FligthPlanReader> newCurrentFlightPlan) {
        super(newCurrentFlightPlan, new PresentationModel<GoogleMapModel>());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {
        final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) getPresenter(GOOGLE_PRESENTER_INDEX);
        final PresentationModel<FligthPlanReader> fpPresetner = (PresentationModel<FligthPlanReader>) getPresenter(FIRST_PRESENTER);

        final SelectionSlot<FligthPlanReader> slot = new SelectionSlot<FligthPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FligthPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FligthPlanReader flightPlanReader) {
                if (null != flightPlanReader) {

                    fpPresetner.triggerFlush();
                    fpPresetner.setBean(flightPlanReader);

                    final GoogleMapWriter googleMapWriter = new GoogleMapModel();
                    googlePresenter.setBean((GoogleMapModel) googleMapWriter);
                    googleMapWriter.setMarkers(GeographicUtils.getSteerPoints(flightPlanReader.getSteerPoints()));
                    final EncodedPolyline polyline = GeographicUtils.getEncodePolyline(flightPlanReader.getSteerPoints());
                    googleMapWriter.setEncodedPolyline(polyline);
                }
            }
        });
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void build() {
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(COLLUMNSPEC_PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));

        final PresentationModel<FligthPlanReader> fpPresenter = (PresentationModel<FligthPlanReader>) getPresenter(FIRST_PRESENTER);

        add(createCreationTimePanel(fpPresenter), "2,2,11,1");
        add(createCreationRoutePanel(fpPresenter), "2,4,11,1");
        add(createCreationFlightInfosPanel(fpPresenter), "2,6,11,1");
        add(createCreationOptionsPanel(fpPresenter), "2,8,11,1");
        add(createMap(), "2, 14, 11, 1, center, center");
    }

    /**
     *
     * @return the Maps.
     */
    private JPanel createMap() {
        final JPanel panel = new JPanel();
        panel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        panel.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) getPresenter(GOOGLE_PRESENTER_INDEX);
        googleMap = new GoogleMapPane(googlePresenter);
        googleMap.setDimension(new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
        googleMap.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        panel.add(googleMap);

        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationOptionsPanel createCreationOptionsPanel(final PresentationModel<FligthPlanReader> fpPresenter) {
        final CreationOptionsPanel panel = new CreationOptionsPanel(fpPresenter);
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationFlightInfosPanel createCreationFlightInfosPanel(final PresentationModel<FligthPlanReader> fpPresenter) {
        final CreationFlightInfosPanel panel = new CreationFlightInfosPanel(fpPresenter);
        panel.setAdapter(adapters.get("AircraftTypeAdapter"));
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationTimePanel createCreationTimePanel(final PresentationModel<FligthPlanReader> fpPresenter) {
        final CreationTimePanel panel = new CreationTimePanel(fpPresenter);
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationRoutePanel createCreationRoutePanel(final PresentationModel<FligthPlanReader> fpPresenter) {
        final CreationRoutePanel panel = new CreationRoutePanel(fpPresenter);
        panel.build();
        return panel;
    }

    /**
     * @param adapters
     */
    public void setAdapters(final Map<String, CommonAdapter> adapters) {
        this.adapters = adapters;
    }
}
