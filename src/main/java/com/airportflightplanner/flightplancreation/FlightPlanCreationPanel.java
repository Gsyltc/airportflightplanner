/*
 * @(#)FlightPlanCreationPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 27 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapWriter;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.panels.CommandPanel;
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

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanCreationPanel extends AbstractCommandablePanel {
    
    
    /** */
    protected static final int DEPARTURE_POINT = 0;
    /**
     *
     */
    private static final long serialVersionUID = 4047549681152943474L;
    /** */
    private static final int DEFAULT_HEIGHT = 400;
    /** */
    private static final int DEFAULT_WIDTH = 400;
    
    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int GOOGLE_PRESENTER_INDEX = 1;
    
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
    public final void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
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
        add(createMap(), "2, 10, 7, 1, fill,fill");
        add(createCommandPanel(), "10, 10, 3, 1, fill,fill");
    }
    
    /**
     * @return
     *
     */
    public CommandPanel createCommandPanel() {
        final CommandPanel panel = new CommandPanel();
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
        // panel.addAdapter((DomainModelAdapter<?>)
        // AdaptersProvider.findAdapterByName(//
        // FlightPlanModelAdapter.class.getSimpleName()));
        // panel.addAdapter((DomainModelAdapter<?>)
        // AdaptersProvider.findAdapterByName(//
        // AircraftTypeAdapter.class.getSimpleName()));
        // panel.addAdapter((DomainModelAdapter<?>)
        // AdaptersProvider.findAdapterByName(//
        // StartDaysAdapter.class.getSimpleName()));
        panel.build();
        return panel;
    }
    
    /**
     * @param fpPresenter
     * @return
     *
     */
    public CreationOptionsPanel createCreationOptionsPanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CreationOptionsPanel panel = new CreationOptionsPanel(fpPresenter);
        // panel.addAdapter((DomainModelAdapter<?>)
        // AdaptersProvider.findAdapterByName(//
        // FlightPlanModelAdapter.class.getSimpleName()));
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
     * {@inheritDoc}
     */
    @Override
    public void createSlots() {
        super.createSlots();
        
        final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) //
        getPresenter(GOOGLE_PRESENTER_INDEX);
        
        final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) //
        getPresenter(FP_PRESENTER);
        
        final Slot slot = new Slot(TopicName.FLIGHTPLAN_TABLE_SELECTED_TOPIC, getClass().getSimpleName());
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
                if (null != flightPlanReader) {
                    
                    fpPresenter.triggerFlush();
                    fpPresenter.setBean(flightPlanReader);
                    
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
        
        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.MAP_TITLE);
        panel.setBorder(panelBorder);
        return panel;
    }
    
}
