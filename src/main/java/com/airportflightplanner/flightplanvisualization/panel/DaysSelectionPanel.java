/* @(#)DaysSlectionPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.airportflightplanner.main.visualelements.messages.MainPanelMessages;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class DaysSelectionPanel extends JPanel {
    public DaysSelectionPanel() {
        setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,},
            new RowSpec[] {
                FormSpecs.PREF_ROWSPEC,}));

        JCheckBox chckbxNewCheckBox = new JCheckBox(MainPanelMessages.MONDAY);
        add(chckbxNewCheckBox, "1, 1");

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox(MainPanelMessages.TUESDAY);
        add(chckbxNewCheckBox_1, "3, 1");

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox(MainPanelMessages.WEDNESDAY);
        add(chckbxNewCheckBox_2, "5, 1");

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox(MainPanelMessages.THRUSDAY);
        add(chckbxNewCheckBox_3, "7, 1");

        JCheckBox chckbxNewCheckBox_4 = new JCheckBox(MainPanelMessages.FRIDAY);
        add(chckbxNewCheckBox_4, "9, 1");

        JCheckBox chckbxS = new JCheckBox(MainPanelMessages.SATURDAY);
        add(chckbxS, "11, 1");

        JCheckBox chckbxD = new JCheckBox(MainPanelMessages.SUNDAY);
        add(chckbxD, "13, 1");
    }

}
