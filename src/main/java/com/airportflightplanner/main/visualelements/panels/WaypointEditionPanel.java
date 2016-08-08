/*
 * @(#)WaypointEditionPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 24 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.main.visualelements.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Period;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.processors.GeographicProcessor;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class WaypointEditionPanel extends AbstractCommandablePanel {
    
    
    public WaypointEditionPanel(final Model currentFpBean) { // NOPMD
        // by
        // sylva
        // on
        // 31/07/16
        // 15:43
        super(new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFpBean));
    }

    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(WaypointEditionPanel.class);

    /**
     *
     */
    private static final long serialVersionUID = 6378943645259250488L;
    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int[] COLUMN_GROUP = new int[] { 2, 4 };
    /** */
    protected static final String NEW_LINE = "\n";

    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                "3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, });

        formLayout.setColumnGroups(new int[][] { COLUMN_GROUP });
        setLayout(formLayout);

        add(createTextPanel(), "2, 2, 3, 1, fill, fill");
        // add(createDaysSelectionPanel(), "2, 4, 3, 1, fill, fill");

    }

    /**
     * @return
     */
    private JTextArea createTextPanel() {
        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        presenter.addBeanPropertyChangeListener(FlightPlanProperties.AIRCRAFT_CLASS, new PropertyChangeListener() {
            
            
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                System.out.println("CHANGED");
            }
        });
        final BufferedValueModel bufModel = presenter.getBufferedModel(FlightPlanProperties.STEERPOINTS_LIST);

        final ValueModel value = ConverterFactory.createStringConverter(bufModel, new Format() {
            
            
            /**
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
                if (obj instanceof List) {
                    final List<String> steerPoints = (List<String>) obj;

                    for (final String steerPoint : steerPoints) {
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
                    boolean isValid = true;
                    final String[] datas = line.split(" +");
                    // Check number of param
                    if (datas.length >= 6) {
                        isValid &= isPositionValid(datas[0]);
                        isValid &= isPositionValid(datas[1]);
                        isValid &= isAltitudeValid(datas[2]);
                        isValid &= isSpeedValid(datas[4]);
                        isValid &= isMaxBankAngleValid(datas[5]);
                        isValid &= !(null == datas[9]);
                    }
                    if (isValid) {
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
        area.setRows(20);
        area.setColumns(1);
        return area;
    }

    /**
     *
     * @param value
     */
    private boolean isPositionValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,3}+\\.?\\d{0,10}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     */
    private boolean isAltitudeValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,5}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     */
    private boolean isSpeedValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,3}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     */
    private boolean isMaxBankAngleValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,2}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSlots() {
        super.createSlots();
        final Slot slot = new Slot(TopicName.FP_TABLE_SELECTED_TOPIC, getClass().getSimpleName());
        slot.registerSlot();
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            
            
            /**
            *
            */
            private static final long serialVersionUID = 1240749169986714101L;

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) //
                getPresenter(FP_PRESENTER);
                if (null != flightPlanReader) {
                    fpPresenter.triggerFlush();
                    fpPresenter.setBean(flightPlanReader);
                }
            }
        });

        final Slot validationSlot = new Slot(TopicName.VALIDATION_TOPIC, getClass().getSimpleName());
        validationSlot.registerSlot();

        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        validationSlot.setSlotAction(new SlotAction<ActionTypes>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 1289014075739897031L;

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final ActionTypes action) {
                switch (action) {
                case VALIDATE:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "Validate");
                    }
                    final BufferedValueModel steerpoints = presenter.getBufferedModel(FlightPlanProperties.STEERPOINTS_LIST);
                    presenter.setValue(FlightPlanProperties.DURATION, new Period(GeographicProcessor.getFlightTime(
                            (List<String>) steerpoints.getValue())));
                    presenter.triggerCommit();
                    break;
                
                case CANCEL:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "CANCEL");
                    }
                    presenter.triggerFlush();
                    break;
                
                case REFRESH:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(this.getClass().getSimpleName() + " : " + "REFREASH");
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("");
                    }
                    break;
                default:
                    break;
                }
            }
        });
    }
}
