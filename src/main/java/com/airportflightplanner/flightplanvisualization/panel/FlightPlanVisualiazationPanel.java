/* @(#)FlightPlanVisualiazation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.util.ArrayList;
import java.util.List;

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

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualiazationPanel extends AbstractCommonPanel {
    /**
     *
     */
    protected transient final FlighPlanCollectionModel flightPlansCollection;
    /** */
    protected transient Signal                         signal;

    /**
     *
     */
    private static final long                          serialVersionUID = -6354635338489926005L;

    /**
     *
     */

    /**
     * @param newFPCollectionModel
     *            the Flightplan collection model.
     *
     */
    public FlightPlanVisualiazationPanel(final FlighPlanCollectionModel newFPCollectionModel) {
        super(new FlightPlanVisualizationPresenter(newFPCollectionModel));
        flightPlansCollection = newFPCollectionModel;
        //        final FlightPlanVisualizationPresenter presenter = new FlightPlanVisualizationPresenter(newFPCollectionModel);
        //        newFlightPlanColltionModel.addFligfhtPlanModelListener(//
        //                ((FlightPlanCollectionReader) presenters.get(FlightPlanVisualizationPresenter.class.getSimpleName())).getListModel());
        // constructPanel();
    }

    /**
     *
     */
    @Override
    public final void build() {
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
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

        add(createCurrentAirportPanel(), "2, 2, 3, 1, fill, fill");
        add(createDaysSelectionPanel(), "2, 4, 3, 1, fill, fill");
        add(createFlightScrollPane(), "2, 6, 3, 1");
        add(createSteerPointPanel(), "2, 8, 3, 1");

        final FlightPlanVisualizationPresenter presenter = (FlightPlanVisualizationPresenter) getPresenter(FIRST_PRESENTER);
        flightPlansCollection.addFligfhtPlanModelListener(presenter.getListModel());

    }

    /**
     *
     * @return
     */
    private CurrentAirportPanel createCurrentAirportPanel() {
        final CurrentAirportPanel panel = new CurrentAirportPanel(flightPlansCollection);
        panel.build();
        return panel;
    }

    /**
     *
     * @return
     */
    private DaysSelectionPanel createDaysSelectionPanel() {
        final DaysSelectionPanel panel = new DaysSelectionPanel();
        panel.build();
        return panel;
    }

    /**
     *
     * @return
     */
    private SteerPointPanel createSteerPointPanel() {
        final SteerPointPanel panel = new SteerPointPanel();
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
        final PresentationModel<?> presenter = getPresenter(FIRST_PRESENTER);
        final JTable table = new JTable(((FlightPlanVisualizationPresenter) presenter).getTableAdapter());
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void valueChanged(final ListSelectionEvent e) {
                FligthPlanReader flightPlan = null;
                if (e.getValueIsAdjusting()) {
                    final ListSelectionModel lsm = (ListSelectionModel) e.getSource();

                    if (!lsm.isSelectionEmpty()) {
                        final int minIndex = lsm.getMinSelectionIndex();
                        final int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) {
                                flightPlan = flightPlansCollection.getFlightPlanByIndex(i);
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
        final SelectionSlot<String> slot = new SelectionSlot<String>(TopicName.UPDATE_AIRPORT_TOPIC, this);
        slot.setSlotAction(new SlotAction<String>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final String object) {
                // TODO
            }
        });

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

}
