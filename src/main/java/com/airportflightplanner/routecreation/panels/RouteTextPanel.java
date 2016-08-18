/*
 * @(#)WaypointTextPanel.java
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

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.AdapterNames;
import com.airportflightplanner.routecreation.messages.RouteEditorMessages;
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

public class RouteTextPanel extends AbstractCommonPanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 5863810864164206794L;
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(RouteTextPanel.class);
    /** Row number of the area. */
    private static final int ROW_COUNTS = 15;
    /** the steer points presenter index. */
    private static final int STEERPOINTS_PRESENTER = 0;

    /**
     * Create the panel.
     *
     */
    public RouteTextPanel() {
        super();
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
        setBorder(BorderFactory.createTitledBorder(RouteEditorMessages.WAYPOINT_LIST_TITLE));
        
        // final PresentationModel<SteerPointsCollectionReader> stpPresenter =
        // (PresentationModel<SteerPointsCollectionReader>) getPresenter(
        // STEERPOINTS_PRESENTER);
        add(createTextArea(), "2, 2");

    }

    /**
     * Create the text area.
     *
     * @return the component
     */
    private JScrollPane createTextArea() {
        final JScrollPane pane = new JScrollPane();
        // final SteerPointsConvertAdapter converterAdapter =
        // (SteerPointsConvertAdapter) findAdapter(
        // AdapterNames.SP_CONVERT_ADAPTER_NAME);
        // final BufferedValueModel bufModel =
        // stpPresenter.getBufferedModel(SteerPointsCollectionProperties.STEERPOINTS_MAP);
        // final ValueModel value =
        // ConverterFactory.createStringConverter(bufModel, new Format() {
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
        // toAppendTo, final FieldPosition pos) {
        // final StringBuffer buff = new StringBuffer();
        // if (obj instanceof LinkedListModel && !((LinkedListModel<?>)
        // obj).isEmpty()) {
        // final LinkedListModel<SteerPointReader> steerPoints =
        // (LinkedListModel<SteerPointReader>) obj;
        //
        // for (final SteerPointReader steerPoint : steerPoints) {
        // buff.append(converterAdapter.convertSteerPointToString(steerPoint)).append(NEW_LINE);
        // }
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug("Formating steerpoints : \n" + buff.toString());
        // }
        // }
        // return buff;
        // }
        //
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public Object parseObject(final String source, final ParsePosition
        // pos) {
        //
        // final LinkedListModel<SteerPointReader> steerpoints = new
        // LinkedListModel<SteerPointReader>();
        //
        // // Check if text is accepted
        // final String[] lines = source.split(NEW_LINE);
        // for (final String line : lines) {
        // final SteerPointModel steerPoint =
        // converterAdapter.convertSteerPoint(line);
        // if (null != steerPoint) {
        // steerpoints.add(steerPoint);
        // }
        // }
        // pos.setIndex(source.length() - 1);
        // return steerpoints;
        // }
        // });
        //
        // final JTextArea area = BasicComponentFactory.createTextArea(value);
        // area.setRows(ROW_COUNTS);
        // area.setColumns(1);

        // pane.add(area);
        // pane.setViewportView(area);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return pane;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(AdapterNames.SP_CONVERT_ADAPTER_NAME);
    }
}
