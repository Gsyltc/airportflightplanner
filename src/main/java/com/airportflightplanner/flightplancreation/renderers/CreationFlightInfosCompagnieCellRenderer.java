/* @(#)CreationFlightInfosCompagnieCellRender.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author DCNS
 *
 */
public class CreationFlightInfosCompagnieCellRenderer implements ListCellRenderer<String> {
    /** */
    private  transient final  DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Component getListCellRendererComponent(final JList list, final String value, //
            final int index, final boolean isSelected, final boolean cellHasFocus) {
        final JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(//
                list, value, index, isSelected, cellHasFocus);
        if (null != value && !value.equals("XXXXXXXXXXXXXXXXXXXX")) {
            renderer.setText(Internationalizer.getI18String(value));
        }

        return renderer;
    }

}
