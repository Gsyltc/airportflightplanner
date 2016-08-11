/*
 * @(#)FlightResumePanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 8 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.waypointmodifications.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionReader;
import com.airportflightplanner.waypointmodifications.messages.WaypointModificationMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.visualelements.AbstractCommonPanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */

public class FlightResumePanel extends AbstractCommonPanel {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(FlightResumePanel.class);

    /** the flight plan presenter. */
    private static final int FP_PRESENTER = 0;
    /** the steer points presenter index. */
    private static final int STEERPOINTS_PRESENTER = 1;

    /**
     * Create the panel.
     *
     * @param currentFpBean
     * @param stpPresenter
     */
    // formatter:off
    public FlightResumePanel(final PresentationModel<FlightPlanReader> currentFpBean,
            final PresentationModel<SteerPointsCollectionReader> stpPresenter) { // NOPMD
        // by
        // sylva
        // on 31/07/16
        // 15:43
        // formatter:on
        super(currentFpBean, stpPresenter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, });

        // formLayout.setColumnGroups(new int[][] { COLUMN_GROUP });
        setLayout(formLayout);

        setBorder(BorderFactory.createTitledBorder(WaypointModificationMessages.FLIGHT_INFO_TITLE));
        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);

        add(new JLabel(WaypointModificationMessages.DISTANCE_LABEL), "2, 2");
        add(createDistanceTextField(presenter), "4, 2");

        add(new JLabel(WaypointModificationMessages.TIME_LABEL), "2, 4");
        add(createTimeTextField(presenter), "4, 4");

        add(createUpdateButton(), "6, 2");

    }

    /**
     * Create the text panel area.
     *
     * @param presenter
     *            the flight plan presenter
     * @return the panel
     */
    private JButton createUpdateButton() {
        final JButton button = new JButton("Refresh");

        return button;
    }

    /**
     * Create the text panel area.
     *
     * @param presenter
     *            the flight plan presenter
     * @return the panel
     */
    private JTextField createTimeTextField(final PresentationModel<FlightPlanReader> presenter) {
        final JTextField component = new JTextField();

        return component;
    }

    /**
     * Create the text panel area.
     *
     * @param presenter
     *            the flight plan presenter
     * @return the panel
     */
    private JTextField createDistanceTextField(final PresentationModel<FlightPlanReader> presenter) {
        final JTextField component = new JTextField();

        return component;
    }

}
