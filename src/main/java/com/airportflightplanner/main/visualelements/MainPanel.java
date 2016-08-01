/*
 * @(#)MainPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 31 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.main.visualelements;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.airportflightplanner.common.types.BeanNames;
import com.airportflightplanner.flightplancreation.FlightPlanCreationPanel;
import com.airportflightplanner.flightplanvisualization.panel.FlightPlanVisualiazationPanel;
import com.airportflightplanner.main.visualelements.messages.MainPanelMessages;
import com.airportflightplanner.main.visualelements.panels.WaypointEditionPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.models.ModelsProvider;

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
    
    /**
     * Main Panel.
     */
    public MainPanel() {
        super();
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
    private FlightPlanCreationPanel createFlightPlanCreationPanel() {
        final FlightPlanCreationPanel panel = new FlightPlanCreationPanel(//
                ModelsProvider.INSTANCE.findModelByName(BeanNames.CURRENT_FP_MODEL), //
                ModelsProvider.INSTANCE.findModelByName(BeanNames.DAYS_MODEL));
        panel.build();
        return panel;
    }
    
    /**
     *
     * @return the panel.
     */
    private FlightPlanVisualiazationPanel createFlightPlanVisualiazationPanel() {
        final FlightPlanVisualiazationPanel panel = new FlightPlanVisualiazationPanel(//
                ModelsProvider.INSTANCE.findModelByName(BeanNames.FP_COLLECTION_MODEL), //
                ModelsProvider.INSTANCE.findModelByName(BeanNames.STEERPOINT_MODEL), //
                ModelsProvider.INSTANCE.findModelByName(BeanNames.CURRENT_FP_MODEL), //
                ModelsProvider.INSTANCE.findModelByName(BeanNames.DAYS_MODEL));
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
