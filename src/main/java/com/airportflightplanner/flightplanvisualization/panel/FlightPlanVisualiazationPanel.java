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

import org.springframework.beans.factory.annotation.Autowired;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualiazationPanel extends CommonPanel {
    /**
     *
     */
    @Autowired(required = true)
    protected final FlighPlanCollectionModel       flightPlansCollection;
    /**
     *
     */
    private final FlightPlanVisualizationPresenter presenter;
    /** */
    private JTable                                 table;
    /** */
    protected Signal                               signal;

    /**
     *
     */
    private static final long                      serialVersionUID = -6354635338489926005L;

    /**
     *
     */

    /**
     * @param newFlightPlanColltionModel
     *            the Flightplan collection model.
     *
     */
    public FlightPlanVisualiazationPanel(final FlighPlanCollectionModel newFlightPlanColltionModel) {
        this.flightPlansCollection = newFlightPlanColltionModel;
        presenter = new FlightPlanVisualizationPresenter(newFlightPlanColltionModel);
        newFlightPlanColltionModel.addFligfhtPlanModelListener(presenter.getListModel());
        build();
    }

    /**
     *
     */
    @Override
    protected final void build() {
        super.build();
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

        add(new CurrentAirportPanel(flightPlansCollection), "2, 2, 3, 1, fill, fill");
        add(new DaysSelectionPanel(), "2, 4, 3, 1, fill, fill");
        add(createFlightVisualizationPanel(), "2, 6, 3, 1");
        add(new SteerPointdPanel(), "2, 8, 3, 1");

    }

    /**
     * @return the panel.
     *
     */
    private JScrollPane createFlightVisualizationPanel() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table = new JTable(presenter.getTableAdapter());
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
                    ListSelectionModel lsm = (ListSelectionModel) e.getSource();

                    if (!lsm.isSelectionEmpty()) {
                        int minIndex = lsm.getMinSelectionIndex();
                        int maxIndex = lsm.getMaxSelectionIndex();
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

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        return scrollPane;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {
        Slot<String> slot = new Slot<String>(TopicName.UPDATE_AIRPORT_TOPIC, this);
        slot.setSlotAction(new SlotAction<String>() {

            @Override
            public void doAction(final String object) {

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
