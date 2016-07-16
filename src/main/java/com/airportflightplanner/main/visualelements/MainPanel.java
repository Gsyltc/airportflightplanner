/*
 * @(#)MainPanel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.main.visualelements;

import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.airportflightplanner.common.api.adapter.CommonAdapter;
import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.models.FlighPlanCollectionModel;
import com.airportflightplanner.flightplancreation.FlightPlanCreationPanel;
import com.airportflightplanner.flightplanvisualization.panel.FlightPlanVisualiazationPanel;
import com.airportflightplanner.main.visualelements.messages.MainPanelMessages;
import com.airportflightplanner.main.visualelements.panels.WaypointEditionPanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class MainPanel extends FormDebugPanel {

    /**
     *
     */
    private static final long                          serialVersionUID = -1014619836487219532L;
    /**
     *
     */
    private static final int                           FIRST_TAB        = 0;
    /** */
    private transient final FlighPlanCollectionModel   flighPlanCollectionModel;
    /** */
    private transient final Map<String, CommonAdapter> adapters;

    /**
     * Main Panel.
     *
     * @param fpCollectionModel
     *            Collection Model.
     * @param adapters
     */
    public MainPanel(final FlighPlanCollectionModel fpCollectionModel, final Map<String, CommonAdapter> adapters) {
        setPaintRows(false);
        setPaintInBackground(false);
        flighPlanCollectionModel = fpCollectionModel;
        this.adapters = adapters;
        buildPanel();
    }

    /**
     *
     */
    private void buildPanel() {
        // Set Layout
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("right:pref"), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.DEFAULT_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        // Create Panel
        final FlightPlanCreationPanel createPanel = createFlightPlanCreationPanel();
        //
        final FlightPlanVisualiazationPanel fpVisuPanel = createFlightPlanVisualiazationPanel();
        //
        final WaypointEditionPanel wpEditionPanel = createWaypointEditionPanel();

        // Create TabbedPanel
        final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        tabbedPane.add(MainPanelMessages.CREATE, createPanel);
        tabbedPane.add(MainPanelMessages.MODIFY, wpEditionPanel);
        tabbedPane.setSelectedIndex(FIRST_TAB);
        //
        // Add component to main panel
        add(fpVisuPanel, "2, 2, fill, fill");
        add(tabbedPane, "4, 2, center, fill");

    }

    /**
     *
     * @return the panel.
     */
    private WaypointEditionPanel createWaypointEditionPanel() {
        final WaypointEditionPanel panel = new WaypointEditionPanel();
        panel.build();
        return panel;
    }

    /**
     *
     * @return the panel.
     */
    private FlightPlanVisualiazationPanel createFlightPlanVisualiazationPanel() {
        final FlightPlanVisualiazationPanel panel = new FlightPlanVisualiazationPanel(flighPlanCollectionModel);
        panel.build();
        return panel;
    }

    /**
     *
     * @return the panel.
     */
    private FlightPlanCreationPanel createFlightPlanCreationPanel() {
        final FlightPlanCreationPanel panel = new FlightPlanCreationPanel(new PresentationModel<FligthPlanReader>((FligthPlanReader) null));
        panel.setAdapters(adapters);
        panel.build();
        return panel;
    }
}
