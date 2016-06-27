/* @(#)FlightPlanVisualiazation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplanvisualization.presenter.FlightPlanVisualizationPresenter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualiazationPanel extends JPanel {
    /**
     *
     */
    private final FlighPlanCollectionModel         fpcm             = new FlighPlanCollectionModel();
    /**
     * <
     */
    private final FlightPlanVisualizationPresenter presenter        = new FlightPlanVisualizationPresenter(fpcm);
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
     *
     */
    public FlightPlanVisualiazationPanel() {
        buildPanel();
        fpcm.addFligfhtPlanModelListener(presenter.getListModel());
    }

    /**
     *
     */
    private void buildPanel() {
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

        add(new CurrentAirportPanel(fpcm), "2, 2, 3, 1, fill, fill");
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


         TableRowSorter<TableModel> sorter = new         TableRowSorter<>(table.getModel());
         table.setRowSorter(sorter);
         List<RowSorter.SortKey> sortKeys = new ArrayList<>();

         sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

         sorter.setSortKeys(sortKeys);
         sorter.sort();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        return scrollPane;
    }
}
