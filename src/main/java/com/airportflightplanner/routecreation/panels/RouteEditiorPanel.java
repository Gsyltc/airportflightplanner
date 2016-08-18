/*
 * @(#)WaypointEditionPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.routecreation.panels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class RouteEditiorPanel extends AbstractCommandablePanel {
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(RouteEditiorPanel.class);

    /**
     *
     */
    private static final long serialVersionUID = 6378943645259250488L;
    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int[] COLUMN_GROUP = new int[] { 2, 4 };
    /** the steer points presenter index. */
    private static final int STEERPOINTS_PRESENTER = 1;

    /**
     * Create the panel.
     *
     */
    // formatter:off
    public RouteEditiorPanel() { // NOPMD
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

        // final PresentationModel<FlightPlanReader> presenter =
        // (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        // final PresentationModel<SteerPointsCollectionReader> stpPresenter =
        // (PresentationModel<SteerPointsCollectionReader>) getPresenter(
        // STEERPOINTS_PRESENTER);
        add(createWaypointTextPanel(), "2, 2,3,1,fill,fill");
        add(createResumePanel(), "2, 4,3,1,fill,fill");
    }

    /**
     * Create the text panel area.
     *
     * @return the panel
     */
    private RouteTextPanel createWaypointTextPanel() {
        final RouteTextPanel panel = new RouteTextPanel();
        panel.build();

        return panel;
    }

    /**
     * Create the text panel area.
     *
     * @return the panel
     */
    private FlightResumePanel createResumePanel() {
        final FlightResumePanel panel = new FlightResumePanel();
        panel.build();

        return panel;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSlots() {
        // final PresentationModel<SteerPointsCollectionReader> stpPresenter =
        // (PresentationModel<SteerPointsCollectionReader>) getPresenter(
        // STEERPOINTS_PRESENTER);
        // final PresentationModel<FlightPlanReader> fpPresenter =
        // (PresentationModel<FlightPlanReader>) //
        // getPresenter(FP_PRESENTER);
        // super.createSlots();
        //
        // final Slot fpSlot = new Slot(TopicName.FP_TABLE_SELECTED_TOPIC,
        // getClass().getSimpleName());
        // fpSlot.setSlotAction(new SlotAction<FlightPlanReader>() {
        //
        //
        // /**
        // *
        // */
        // private static final long serialVersionUID = -5959157823719266328L;
        //
        // /**
        // *
        // *
        // * {@inheritDoc}.
        // */
        // @Override
        // public void doAction(final FlightPlanReader bean) {
        // if (!fpPresenter.getBean().equals(bean)) {
        // fpPresenter.setBean(bean);
        // fpPresenter.triggerFlush();
        // }
        // }
        //
        // });
        //
        // final Slot validationSlot = new Slot(TopicName.VALIDATION_TOPIC,
        // getClass().getSimpleName());
        // validationSlot.registerSlot();
        // validationSlot.setSlotAction(new SlotAction<ActionTypes>() {
        //
        //
        // /**
        // *
        // */
        // private static final long serialVersionUID = 1289014075739897031L;
        //
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public void doAction(final ActionTypes action) {
        // switch (action) {
        // case VALIDATE:
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug(this.getClass().getSimpleName() + " : " + "Validate");
        // }
        //
        // final BufferedValueModel steerpoints = stpPresenter.getBufferedModel(
        // SteerPointsCollectionProperties.STEERPOINTS_MAP);
        // final List<SteerPointReader> list = (List<SteerPointReader>)
        // steerpoints.getValue();
        // fpPresenter.setBufferedValue(FlightPlanProperties.DURATION, new
        // Period(GeographicProcessor.getFlightTime(
        // (List<SteerPointReader>) steerpoints.getValue())));
        // fpPresenter.setBufferedValue(FlightPlanProperties.STEERPOINTS_LIST,
        // steerpoints.getValue());
        // fpPresenter.triggerCommit();
        //
        // final SteerPointModelAdapter adapter = (SteerPointModelAdapter)
        // findAdapter(AdapterNames.STEERP_ADAPTER_NAME);
        // adapter.addSteerPoints(list);
        //
        // break;
        //
        // case CANCEL:
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug(this.getClass().getSimpleName() + " : " + "CANCEL");
        // }
        // stpPresenter.triggerFlush();
        // break;
        //
        // case REFRESH:
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug(this.getClass().getSimpleName() + " : " + "REFRESH");
        // }
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug("");
        // }
        // break;
        // default:
        // break;
        // }
        // }
        // });
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        // attachAdapter(AdapterNames.STEERP_ADAPTER_NAME);
    }
}
