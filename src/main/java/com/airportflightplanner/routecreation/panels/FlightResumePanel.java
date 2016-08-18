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

package com.airportflightplanner.routecreation.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.models.routes.api.bean.FlightRouteReader;
import com.airportflightplanner.routecreation.messages.RouteEditorMessages;
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
    
    /** the route presenter. */
    private static final int ROUTE_PRESENTER = 0;
    
    /**
     * Create the panel.
     *
     * @param currentFlightRoute
     */
    // formatter:off
    public FlightResumePanel() { // NOPMD
        // by
        // sylva
        // on 31/07/16
        // 15:43
        // formatter:on
        super();
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
        
        setBorder(BorderFactory.createTitledBorder(RouteEditorMessages.FLIGHT_INFO_TITLE));
        // final PresentationModel<FlightRouteReader> presenter =
        // (PresentationModel<FlightRouteReader>) getPresenter(ROUTE_PRESENTER);
        add(new JLabel(RouteEditorMessages.DISTANCE_LABEL), "2, 2");
        add(createDistanceTextField(), "4, 2");
        
        add(new JLabel(RouteEditorMessages.TIME_LABEL), "2, 4");
        add(createDistanceTextField(), "4, 4");
        
        add(createUpdateButton(), "6, 2");
        
    }
    
    /**
     * Create the text panel area.
     *
     * @return the panel
     */
    private JButton createUpdateButton() {
        final JButton button = new JButton("Refresh");
        // button.addActionListener(new ActionListener() {
        //
        //
        // @Override
        // public void actionPerformed(final ActionEvent e) {
        // routePresenter.triggerCommit();
        //
        // }
        // });
        return button;
    }
    
    /**
     * Create the text panel area.
     *
     * @param routePresenter
     *            the route presenter
     * @return the panel
     */
    private JTextField createTimeTextField(final PresentationModel<FlightRouteReader> routePresenter) {
        //// final BufferedValueModel bufferedValue =
        //// routePresenter.getBufferedModel(FlightRouteProperties.STEERPOINTS_LIST);
        // final ValueModel value =
        //// ConverterFactory.createStringConverter(bufferedValue, new Format()
        //// {
        //
        //
        // /**
        // *
        // *
        // */
        // private static final long serialVersionUID = 5858092520252522599L;
        //
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public StringBuffer format(final Object obj, final StringBuffer
        //// toAppendTo, final FieldPosition pos) {
        // final StringBuffer buff = new StringBuffer();
        // final long time =
        //// GeographicProcessor.getFlightTime((List<SteerPointReader>) obj);
        //
        // final Period period = new Period(time);
        // buff.append(period.toString(TimeUtils.PERIOD_DISPLAYER));
        // return buff;
        // }
        //
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public Object parseObject(final String component, final ParsePosition
        //// pos) {
        // return null;
        // }
        // });
        // final JTextField component =
        //// BasicComponentFactory.createTextField(value);
        // component.setEditable(false);
        // return component;
        return new JTextField();
    }
    
    /**
     * Create the text panel area.
     *
     * @return the panel
     */
    private JTextField createDistanceTextField() {
        // final JTextField component =
        // BasicComponentFactory.createTextField(stpPresenter.getBufferedModel(
        // SteerPointsCollectionProperties.CURRENT_FP));
        
        return new JTextField();
    }
    
}
