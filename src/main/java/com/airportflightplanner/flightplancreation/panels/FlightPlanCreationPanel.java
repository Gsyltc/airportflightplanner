/*
 * @(#)FlightPlanCreationPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.AdapterNames;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.common.processors.GoogleMapProcessor;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapWriter;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.models.daysselection.api.bean.DaySelectionReader;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
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
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(FlightPlanCreationPanel.class);
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
    public FlightPlanCreationPanel(final Model currentFpBean, final Model daySelection) { // NOPMD
                                                                                          // by
                                                                                          // sylva
                                                                                          // on
                                                                                          // 31/07/16
                                                                                          // 15:43
        super(new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFpBean), //
                new PresentationModel<GoogleMapModel>(new GoogleMapModel()), //
                new PresentationModel<DaySelectionReader>((DaySelectionReader) daySelection));
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
        fpPresenter.addPropertyChangeListener(PresentationModel.PROPERTY_BUFFERING, new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                final FlightPlanModelAdapter adapter = (FlightPlanModelAdapter) findAdapter(AdapterNames.FP_ADAPTER_NAME);
                adapter.setModificationtoCommit(fpPresenter.isBuffering());
            }
        });
        add(createCreationTimePanel(fpPresenter), "2,2,11,1");
        add(createCreationRoutePanel(fpPresenter), "2,4,11,1");
        add(createCreationFlightInfosPanel(fpPresenter), "2,6,11,1");
        add(createCreationOptionsPanel(fpPresenter), "2,8,11,1");
        add(createMap(), "2, 10, 7, 1, fill,fill");
        add(createCommandPanel(fpPresenter), "10, 10, 3, 1, fill,fill");
    }

    /**
     * @param fpPresenter
     * @return
     *
     */
    public CommandPanel createCommandPanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        final CommandPanel panel = new CommandPanel(fpPresenter);
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
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(AdapterNames.FP_ADAPTER_NAME);
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
                final PresentationModel<GoogleMapModel> googlePresenter = (PresentationModel<GoogleMapModel>) //
                getPresenter(GOOGLE_PRESENTER_INDEX);

                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) //
                getPresenter(FP_PRESENTER);
                if (null != flightPlanReader) {
                    
                    fpPresenter.triggerFlush();
                    fpPresenter.setBean(flightPlanReader);

                    final GoogleMapWriter googleMapWriter = new GoogleMapModel();
                    googlePresenter.setBean((GoogleMapModel) googleMapWriter);
                    googleMapWriter.setMarkers(flightPlanReader.getSteerPoints());
                    final EncodedPolyline polyline = GoogleMapProcessor.getEncodePolyline(flightPlanReader.getSteerPoints());
                    googleMapWriter.setEncodedPolyline(polyline);
                }
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
                    presenter.triggerCommit();
                    presenter.triggerFlush();
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

    /**
     * Create the google map panel.
     *
     * @return the map panel.
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
