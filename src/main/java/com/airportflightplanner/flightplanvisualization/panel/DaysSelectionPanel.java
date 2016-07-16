/* @(#)DaysSlectionPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import javax.swing.JCheckBox;

import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class DaysSelectionPanel extends AbstractCommonPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3382772953500242522L;


    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, }, //
                new RowSpec[] { //
                        FormSpecs.PREF_ROWSPEC, })); //

        final JCheckBox monday = new JCheckBox(StartDays.MONDAY.toString());
        add(monday, "1, 1");

        final JCheckBox tuesday = new JCheckBox(StartDays.TUESDAY.toString());
        add(tuesday, "3, 1");

        final JCheckBox wednesday = new JCheckBox(StartDays.WEDNESDAY.toString());
        add(wednesday, "5, 1");

        final JCheckBox thrusday = new JCheckBox(StartDays.THRUSDAY.toString());
        add(thrusday, "7, 1");

        final JCheckBox fiday = new JCheckBox(StartDays.FRIDAY.toString());
        add(fiday, "9, 1");

        final JCheckBox saturday = new JCheckBox(StartDays.SATURDAY.toString());
        add(saturday, "11, 1");

        final JCheckBox sunday = new JCheckBox(StartDays.SUNDAY.toString());
        add(sunday, "13, 1");
    }

}
