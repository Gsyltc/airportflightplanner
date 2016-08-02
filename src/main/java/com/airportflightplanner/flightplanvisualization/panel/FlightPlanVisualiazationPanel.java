/*
 * @(#)FlightPlanVisualiazationPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 1 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.flightplan.collection.FlightPlanCollectionReader;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsPresenter;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.signals.Signal;
import fr.gsyltc.framework.visualelements.AbstractCommonPanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualiazationPanel extends AbstractCommonPanel {
    
    
    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int SP_PRESENTER_INDEX = 1;
    /** */
    private static final int CURRENT_FP_PRESENTER = 2;
    /** */
    private static final int DAYS_SELECT_PRESENTER = 3;
    /**
     *
     */
    private static final long serialVersionUID = -6354635338489926005L;
    /** */
    protected transient Signal signal;
    
    /** */
    private FlightPlanCollectionReader fpCollection;
    
    /**
     *
     */
    
    /**
     * @param newFPCollectionModel
     *            the Flightplan collection model.
     * @param steerpointsModel
     * @param currentFp
     * @param daysSelectionModel
     *
     */
    public FlightPlanVisualiazationPanel(final Model newFPCollectionModel, // //
                                                                           // NOPMD
                                                                           // by
                                                                           // sylva
                                                                           // on
                                                                           // 31/07/16
                                                                           // 15:43
            final Model steerpointsModel, final Model currentFp, final Model daysSelectionModel) {
        super(new FlightPlanVisualizationPresenter(newFPCollectionModel), //
                new SteerPointsPresenter(steerpointsModel), //
                new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFp), //
                new PresentationModel<DaySelectionReader>((DaySelectionReader) daysSelectionModel));
    }
    
    /**
     *
     */
    @Override
    public final void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                "3dlu:grow"), //
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
        
        formLayout.setColumnGroups(new int[][] { new int[] { 2, 4 } });
        setLayout(formLayout);
        final FlightPlanVisualizationPresenter presenter = (FlightPlanVisualizationPresenter) getPresenter(FP_PRESENTER);
        setFpCollection(presenter.getBean());
        
        add(createCurrentAirportPanel(), "2, 2, 3, 1, fill, fill");
        add(createDaysSelectionPanel(), "2, 4, 3, 1, fill, fill");
        add(createFlightPlanListPanel(), "2, 6, 3, 1");
        add(createSteerPointPanel(), "2, 8, 3, 1");
    }
    
    /**
     *
     * @return
     */
    private CurrentAirportPanel createCurrentAirportPanel() {
        final CurrentAirportPanel panel = new CurrentAirportPanel(//
                (FlightPlanVisualizationPresenter) getPresenter(FP_PRESENTER));
        panel.build();
        return panel;
    }
    
    /**
     *
     * @return
     */
    private DaysSelectionPanel createDaysSelectionPanel() {
        final DaysSelectionPanel panel = new DaysSelectionPanel(//
                (PresentationModel<DaySelectionReader>) getPresenter(DAYS_SELECT_PRESENTER), //
                (PresentationModel<FlightPlanReader>) getPresenter(CURRENT_FP_PRESENTER));
        panel.build();
        return panel;
    }
    
    /**
     *
     * @return
     */
    private FlightPlanListPanel createFlightPlanListPanel() {
        final FlightPlanVisualizationPresenter presenter = (FlightPlanVisualizationPresenter) getPresenter(FP_PRESENTER);
        final FlightPlanListPanel panel = new FlightPlanListPanel(presenter);
        panel.build();
        return panel;
    }
    
    /**
     *
     * @return
     */
    private SteerPointPanel createSteerPointPanel() {
        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(SP_PRESENTER_INDEX);
        final SteerPointPanel panel = new SteerPointPanel(presenter);
        panel.build();
        return panel;
    }
    
    /**
     *
     * @param newFpCollection
     */
    private void setFpCollection(final FlightPlanCollectionReader newFpCollection) {
        fpCollection = newFpCollection;
    }
    
    /**
     * @return the fpCollection
     */
    protected FlightPlanCollectionReader getFpCollection() {
        return fpCollection;
    }
}
