/* @(#)SteepointdPanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
import com.airportflightplanner.common.api.steerpoints.collection.SteerPointsCollectionReader;
import com.airportflightplanner.common.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsPresenter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointPanel extends AbstractCommonPanel {
    /**
     *
     */
    protected Signal          signal;

    /**
     *
     */
    private static final long serialVersionUID     = -6354635338489926005L;
    /** */
    private static final int  STEERPOINT_PRESENTER = AbstractCommonPanel.FIRST_PRESENTER;

    /**
     *
     */

    /**
     * @param presenter
     *
     */
    public SteerPointPanel(final SteerPointsPresenter presenter) {
        super(presenter);
    }

    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.STEERPOINTS_TITLE);
        setBorder(panelBorder);

        add(createSteerPointsPanel(), "2, 2, 3, 1");

        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(STEERPOINT_PRESENTER);
        final SteerPointsCollectionReader reader = presenter.getBean();
        reader.addSteerPointsListModelListener(presenter.getListModel());

    }

    /**
     * @return SteerPoint Panel.
     *
     */
    private JScrollPane createSteerPointsPanel() {
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(STEERPOINT_PRESENTER);
        final JTable table = new JTable(presenter.getTableAdapter());
        table.setColumnSelectionAllowed(true);
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setFillsViewportHeight(true);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setViewportView(table);
        return scrollPane;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {
        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED_TOPIC, this);
        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(STEERPOINT_PRESENTER);
        final SteerPointsCollectionModel steerPointsModel = presenter.getBean();
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
                steerPointsModel.getSteerPointsListModel().clear();
                if (null != flightPlanReader) {
                    final List<SteerPointReader> steerPoints = GeographicUtils.getSteerPoints(flightPlanReader.getSteerPoints());
                    steerPointsModel.addSteerPoints(steerPoints);
                }
            }
        });
    }
}
