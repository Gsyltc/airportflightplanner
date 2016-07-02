/* @(#)FlightPlanVisualiazation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.selection.Slot;
import com.airportflightplanner.common.selection.TopicName;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplanvisualization.presenter.FlightPlanVisualizationPresenter;
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
    private final FlighPlanCollectionModel         flightPlansCollection;
    /**
     * <
     */
    private final FlightPlanVisualizationPresenter presenter;
    /** */
    private JTable                                 table;

    /**
     *
     */
    private static final long                      serialVersionUID = -6354635338489926005L;

    /**
     *
     */

    /**
     * @param flightPlansCollection
     *
     */
    public FlightPlanVisualiazationPanel(final FlighPlanCollectionModel flightPlansCollection) {
        this.flightPlansCollection = flightPlansCollection;
        presenter = new FlightPlanVisualizationPresenter(flightPlansCollection);
        flightPlansCollection.addFligfhtPlanModelListener(presenter.getListModel());
        build();
    }

    /**
     *
     */
    @Override
    protected void build() {
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
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        add(new CurrentAirportPanel(flightPlansCollection), "2, 2, 3, 1, fill, fill");
        add(new DaysSelectionPanel(), "2, 4, 3, 1, fill, fill");
        add(createFlightVisualizationPanel(), "2, 6, 3, 1, fill, top");

    }

    /**
     * @return
     *
     */
    private JScrollPane createFlightVisualizationPanel() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table = new JTable(presenter.getTableAdapter());
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setFillsViewportHeight(true);

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
    public void slotAction(final Object object) {
        System.out.println(object.toString());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        Slot slot = new Slot(TopicName.UPDATE_AIRPORT_TOPIC, this);
        slot.attachSignal();

    }

}
