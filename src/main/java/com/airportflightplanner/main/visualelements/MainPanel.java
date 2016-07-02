/* @(#)MainPanel.java
 *
 * Copyright (c) 2016 Sylvain Goubaud. All rights reserved.
 */
package com.airportflightplanner.main.visualelements;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplancreation.FlightPlanCreationPanel;
import com.airportflightplanner.flightplanvisualization.panel.FlightPlanVisualiazationPanel;
import com.airportflightplanner.main.visualelements.messages.MainPanelMessages;
import com.airportflightplanner.main.visualelements.panels.WaypointEditionPanel;
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
    private static final long              serialVersionUID = -1014619836487219532L;
    /**
     *
     */
    private static final int               FIRST_TAB        = 0;
    /** */
    private final FlighPlanCollectionModel flighPlanCollectionModel;

    /**
     * 3
     *
     * @param flighPlanCollectionModel
     *
     */
    public MainPanel(final FlighPlanCollectionModel flighPlanCollectionModel) {
        this.flighPlanCollectionModel = flighPlanCollectionModel;
        buildPanel();
    }

    /**
     *
     */
    private void buildPanel() {
        // Set Layout
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("pref:grow"), //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        // Create Panel
        FlightPlanCreationPanel createPanel = createFlightPlanCreationPanel();
        FlightPlanVisualiazationPanel fpVisuPanel = createFlightPlanVisualiazationPanel();
        WaypointEditionPanel wpEditionPanel = createWaypointEditionPanel();

        // Create TabbedPanel
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        tabbedPane.add(MainPanelMessages.CREATE, createPanel);
        tabbedPane.add(MainPanelMessages.MODIFY, wpEditionPanel);
        tabbedPane.setSelectedIndex(FIRST_TAB);

        // Add component to main panel
        add(fpVisuPanel, "2, 2, fill, fill");
        add(tabbedPane, "4, 2, fill, fill");

    }

    /**
     *
     * @return
     */
    private WaypointEditionPanel createWaypointEditionPanel() {
        WaypointEditionPanel panel = new WaypointEditionPanel();
        return panel;
    }

    /**
     *
     * @return
     */
    private FlightPlanVisualiazationPanel createFlightPlanVisualiazationPanel() {
        FlightPlanVisualiazationPanel panel = new FlightPlanVisualiazationPanel(flighPlanCollectionModel);
        return panel;
    }

    /**
     *
     * @return
     */
    private FlightPlanCreationPanel createFlightPlanCreationPanel() {
        FlightPlanCreationPanel panel = new FlightPlanCreationPanel();
        return panel;
    }
}
