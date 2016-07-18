/*
 * @(#)FlightPlanVisualiazationPanel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.airportflightplanner.common.api.adapter.CommonAdapter;
import com.airportflightplanner.common.api.adapter.StartDaysAdapter;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsPresenter;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualiazationPanel extends AbstractCommonPanel {
    /** */
    protected transient Signal         signal;
    /** */
    private FlighPlanCollectionModel   fpCollection;
    /** */
    private Map<String, CommonAdapter> adapters;
    /** */
    private static final int           FP_PRESENTER          = AbstractCommonPanel.FIRST_PRESENTER;
    /** */
    private static final int           SP_PRESENTER_INDEX    = 1;
    /** */
    private static final int           CURRENT_FP_PRESENTER  = 2;
    /** */
    private static final int           DAYS_SELECT_PRESENTER = 3;

    /**
     *
     */
    private static final long          serialVersionUID      = -6354635338489926005L;

    /**
     *
     */

    /**
     * @param newFPCollectionModel
     *            the Flightplan collection model.
     * @param steerpointsModel
     * @param currentFp
     * @param daysSelectionModel
     *
     */
    public FlightPlanVisualiazationPanel(final Model newFPCollectionModel, //
            final Model steerpointsModel, final Model currentFp, final Model daysSelectionModel) {
        super(new FlightPlanVisualizationPresenter(newFPCollectionModel), //
                new SteerPointsPresenter(steerpointsModel), //
                new PresentationModel<FlightPlanReader>((FlightPlanReader) currentFp), //
                new PresentationModel<DaySelectionReader>((DaySelectionReader) daysSelectionModel));
    }

    /**
     *
     */
    @Override
    public final void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        final FlightPlanVisualizationPresenter presenter = (FlightPlanVisualizationPresenter) getPresenter(FP_PRESENTER);
        setFpCollection(presenter.getBean());

        add(createCurrentAirportPanel(), "2, 2, 3, 1, fill, fill");
        add(createDaysSelectionPanel(), "2, 4, 3, 1, fill, fill");
        add(createFlightScrollPane(), "2, 6, 3, 1");
        add(createSteerPointPanel(), "2, 8, 3, 1");
    }

    /**
     *
     * @return
     */
    private CurrentAirportPanel createCurrentAirportPanel() {
        final CurrentAirportPanel panel = new CurrentAirportPanel(getFpCollection(), //
                (FlightPlanVisualizationPresenter) getPresenter(FP_PRESENTER));
        panel.build();
        return panel;
    }

    /**
     *
     * @return
     */
    private DaysSelectionPanel createDaysSelectionPanel() {
        final DaysSelectionPanel panel = new DaysSelectionPanel(//
                (PresentationModel<DaySelectionReader>) getPresenter(DAYS_SELECT_PRESENTER), //
        (PresentationModel<FlightPlanReader>) getPresenter(CURRENT_FP_PRESENTER));
        panel.setAdapter(adapters.get(StartDaysAdapter.class.getSimpleName()));
        panel.build();
        return panel;
    }

    /**
     *
     * @return
     */
    private SteerPointPanel createSteerPointPanel() {
        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(SP_PRESENTER_INDEX);
        final SteerPointPanel panel = new SteerPointPanel(presenter);
        panel.build();
        return panel;
    }

    /**
     * @return the panel.
     *
     */
    private JScrollPane createFlightScrollPane() {
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        final PresentationModel<?> presenter = getPresenter(FP_PRESENTER);
        final JTable table = new JTable(((FlightPlanVisualizationPresenter) presenter).getTableAdapter());
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                FlightPlanReader flightPlan = null;
                if (event.getValueIsAdjusting()) {
                    final ListSelectionModel lsm = (ListSelectionModel) event.getSource();

                    if (!lsm.isSelectionEmpty()) {
                        final int minIndex = lsm.getMinSelectionIndex();
                        final int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) {
                                flightPlan = getFpCollection().getFlightPlanByIndex(i);
                            }
                        }
                    }
                    signal.fireSignal(flightPlan);
                }
            }
        });

        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        final List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        return scrollPane;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {

        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) getPresenter(CURRENT_FP_PRESENTER);
                fpPresenter.triggerFlush();
                fpPresenter.setBean(bean);
            }
        });

    }

    public void setAdapters(final Map<String, CommonAdapter> adapters) {
        this.adapters = adapters;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSignal() {
        signal = new Signal(TopicName.FLIGHTPLAN_TABLE_SELECTED);
        createSignal(TopicName.FLIGHTPLAN_TABLE_SELECTED, signal);
    }

    /**
     * @return the fpCollection
     */
    protected FlighPlanCollectionModel getFpCollection() {
        return fpCollection;
    }

    /**
     *
     * @param fpCollection
     */
    private void setFpCollection(final FlighPlanCollectionModel fpCollection) {
        this.fpCollection = fpCollection;
    }
}
