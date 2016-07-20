/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Map;

import javax.swing.JPanel;

import com.airportflightplanner.common.api.adapter.common.CommonAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
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
import com.jgoodies.binding.beans.Model;
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
    protected static final int            DEPARTURE_POINT        = 0;
    /**
     *
     */
    private static final long             serialVersionUID       = 4047549681152943474L;
    /** */
    private static final int              DEFAULT_HEIGHT         = 400;
    /** */
    private static final int              DEFAULT_WIDTH          = 400;
    /** */
    private Map<String, CommonAdapter<?>> adapters;

    /** */
    private static final int              FP_PRESENTER           = AbstractCommonPanel.FIRST_PRESENTER;
    /** */
    private static final int              GOOGLE_PRESENTER_INDEX = 1;

    /**
     * @param currentFpBean
     * @param daySelection
     */
    public FlightPlanCreationPanel(final Model currentFpBean, final Model daySelection) {
        super(new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFpBean), //
                new PresentationModel<GoogleMapModel>(new GoogleMapModel()), //
                new PresentationModel<DaysSelectionModel>((DaysSelectionModel) daySelection));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {
        final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) //
                getPresenter(GOOGLE_PRESENTER_INDEX);

        final PresentationModel<FlightPlanReader> fpPresetner = (PresentationModel<FlightPlanReader>) //
                getPresenter(FP_PRESENTER);

        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>( //
                TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
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
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));

        final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) //
                getPresenter(FP_PRESENTER);
        fpPresenter.getBean();
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

        final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) //
                getPresenter(GOOGLE_PRESENTER_INDEX);
        final GoogleMapPane googleMap = new GoogleMapPane(googlePresenter);
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
    public CreationOptionsPanel createCreationOptionsPanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CreationOptionsPanel panel = new CreationOptionsPanel(fpPresenter);
        panel.addAdapter(getAdapters().get("FlightPlanModelAdapter"));
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationFlightInfosPanel createCreationFlightInfosPanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CreationFlightInfosPanel panel = new CreationFlightInfosPanel(fpPresenter);
        panel.addAdapter(getAdapters().get("FlightPlanModelAdapter"));
        panel.addAdapter(getAdapters().get("AircraftTypeAdapter"));
        panel.addAdapter(getAdapters().get("StartDaysAdapter"));
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationTimePanel createCreationTimePanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CreationTimePanel panel = new CreationTimePanel(fpPresenter);
        panel.build();
        return panel;
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationRoutePanel createCreationRoutePanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CreationRoutePanel panel = new CreationRoutePanel(fpPresenter);
        panel.build();
        return panel;
    }

    /**
     * @param adapters
     */
    @Override
    public void setAdapters(final Map<String, CommonAdapter<?>> adapters) {
        this.adapters = adapters;
    }

    /**
     * @return the adapters
     */
    @Override
    public Map<String, CommonAdapter<?>> getAdapters() {
        return Collections.unmodifiableMap(adapters);
    }
}
