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

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.airportflightplanner.common.types.BeanNames;
import com.airportflightplanner.flightplancreation.FlightPlanCreationPanel;
import com.airportflightplanner.flightplanvisualization.panel.FlightPlanVisualiazationPanel;
import com.airportflightplanner.main.visualelements.messages.MainPanelMessages;
import com.airportflightplanner.main.visualelements.panels.WaypointEditionPanel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class MainPanel extends JPanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -1014619836487219532L;
    /**
     *
     */
    private static final int FIRST_TAB = 0;
    // /** */
    // private final Map<String, DomainModelAdapter<?>> adapters;

    /** */
    private final Map<BeanNames, Model> beansMap;

    /**
     * Main Panel.
     *
     * @param beans
     *            Map of Models.
     * @param adapters
     */
    public MainPanel(final Map<BeanNames, Model> beans) {
        // setPaintRows(false);
        // setPaintInBackground(false);
        this.beansMap = beans;
        buildPanel();
    }

    /**
     *
     * @return
     */
    public Map<BeanNames, Model> getBeansMap() {
        return this.beansMap;
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
    private FlightPlanCreationPanel createFlightPlanCreationPanel() {
        final FlightPlanCreationPanel panel = new FlightPlanCreationPanel(//
                this.beansMap.get(BeanNames.CURRENT_FP_MODEL), //
                this.beansMap.get(BeanNames.DAYS_MODEL));
        panel.build();
        return panel;
    }

    /**
     *
     * @return the panel.
     */
    private FlightPlanVisualiazationPanel createFlightPlanVisualiazationPanel() {
        final FlightPlanVisualiazationPanel panel = new FlightPlanVisualiazationPanel(//
                this.beansMap.get(BeanNames.FP_COLLECTION_MODEL), //
                this.beansMap.get(BeanNames.STEERPOINT_MODEL), //
                this.beansMap.get(BeanNames.CURRENT_FP_MODEL), //
                this.beansMap.get(BeanNames.DAYS_MODEL));
        panel.build();
        return panel;
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
}
