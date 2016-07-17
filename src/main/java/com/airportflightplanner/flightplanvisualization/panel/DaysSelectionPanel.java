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

        add(createMondayCb(), "1, 1");
        add(createTuesdayCb(), "3, 1");
        add(createWednesdayCb(), "5, 1");
        add(createThrusdayCb(), "7, 1");
        add(createFridayCb(), "9, 1");
        add(createSaturdayCb(), "11, 1");
        add(createSundayCb(), "13, 1");
    }


    /**
     *
     * @return
     */
    private JCheckBox createMondayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.MONDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @return
     */
    private JCheckBox createTuesdayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.TUESDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @return
     */
    private JCheckBox createWednesdayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.WEDNESDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @return
     */
    private JCheckBox createThrusdayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.THRUSDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @return
     */
    private JCheckBox createFridayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.FRIDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @return
     */
    private JCheckBox createSaturdayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.SATURDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }
    /**
     *
     * @return
     */
    private JCheckBox createSundayCb(){
        final JCheckBox checkBox = new JCheckBox(StartDays.SUNDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }


}
