/*
 * @(#)WaypointTextPanel.java
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

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Period;

import com.airportflightplanner.common.processors.GeographicProcessor;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsListModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionProperties;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionReader;
import com.airportflightplanner.waypointmodifications.messages.WaypointModificationMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ValueModel;
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

public class WaypointTextPanel extends AbstractCommonPanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 5863810864164206794L;
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(WaypointTextPanel.class);
    /** Index of the presenter. */
    private static final int FP_PRESENTER = 0;
    /** Row number of the area. */
    private static final int ROW_COUNTS = 20;
    /** the steer points presenter index. */
    private static final int STEERPOINTS_PRESENTER = 1;
    
    /**
     * Create the panel.
     *
     * @param currentFpBean
     * @param stpPresenter
     */
    public WaypointTextPanel(final PresentationModel<FlightPlanReader> currentFpBean,
            final PresentationModel<SteerPointsCollectionReader> stpPresenter) {
        super(currentFpBean, stpPresenter);
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                LayoutSpecs.PREF_GROW), FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, });
        
        setLayout(formLayout);
        setBorder(BorderFactory.createTitledBorder(WaypointModificationMessages.WAYPOINT_LIST_TITLE));
        
        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        final PresentationModel<SteerPointsCollectionReader> stpPresenter = (PresentationModel<SteerPointsCollectionReader>) getPresenter(
                STEERPOINTS_PRESENTER);
        add(createTextArea(stpPresenter), "2, 2");
        
    }
    
    /**
     * Create the text area.
     *
     * @param stpPresenter
     *
     * @return the component
     */
    private JScrollPane createTextArea(final PresentationModel<SteerPointsCollectionReader> stpPresenter) {
        final JScrollPane pane = new JScrollPane();
        final BufferedValueModel bufModel = stpPresenter.getBufferedModel(SteerPointsCollectionProperties.STEERPOINTS_MAP);
        final ValueModel value = ConverterFactory.createStringConverter(bufModel, new Format() {
            
            
            /**
             *
             *
             */
            private static final long serialVersionUID = 5858092520252522599L;
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
                final StringBuffer buff = new StringBuffer();
                if (obj instanceof SteerPointsListModel) {
                    final SteerPointsListModel steerPoints = (SteerPointsListModel) obj;
                    
                    for (final SteerPointReader steerPoint : steerPoints.getList()) {
                        buff.append(steerPoint).append(NEW_LINE);
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Formating steerpoints : \n" + buff.toString());
                    }
                }
                return buff;
            }
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                
                final List<String> steerpoints = new ArrayList<String>();
                // Check if text is accepted
                final String[] lines = source.split(NEW_LINE);
                for (final String line : lines) {
                    
                    if (GeographicProcessor.validateSteerpoints(line)) {
                        steerpoints.add(line);
                    }
                }
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Flight time : " + new Period(GeographicProcessor.getFlightTime(steerpoints)));
                }
                GeographicProcessor.getFlightTime(steerpoints);
                pos.setIndex(source.length() - 1);
                return steerpoints;
            }
        });
        
        final JTextArea area = BasicComponentFactory.createTextArea(value);
        area.setRows(ROW_COUNTS);
        area.setColumns(1);
        
        pane.add(area);
        pane.setViewportView(area);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return pane;
    }
}
