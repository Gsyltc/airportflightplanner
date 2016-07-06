/* @(#)GoogleEditorPane.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.airportflightplanner.common.types.MapType;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author DCNS
 *
 */
public class GoogleMapPane extends JEditorPane {
    /**
     *
     */
    private static final long serialVersionUID = -3128841669458527858L;
    /** */
    private String            zoomFactor       = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_ZOOM_FACTOR);
    /** */
    private final String      apiKey           = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_KEY);
    /** */
    private MapType           roadmap          = MapType.valueOf(CommonProperties.getPropertyValue(CommonProperties.GOOGLE_MAPTYPE));
    /** */
    private final String      polylineColor    = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_COLOR);
    /** */
    private final String      polylineWeigth   = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_WIDTH);

    /** */
    private int               mapWidth;
    /** */
    private int               mapHeight;

    /**
     * Constructeur: initialisation du EditorKit
     */
    public GoogleMapPane() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
        setMaximumSize(new Dimension(mapWidth, mapHeight));
        setMinimumSize(new Dimension(mapWidth, mapHeight));
        setSize(mapWidth, mapHeight);

    }

    /**
     * Fixer le zoom
     *
     * @param zoom
     *            valeur de 0 à 21
     */
    public void setZoom(final int zoom) {
        this.zoomFactor = String.valueOf(zoom);
    }

    /**
     * Fixer le type de vue
     *
     * @param roadMap
     */
    public void setRoadmap(final MapType roadMap) {
        this.roadmap = roadMap;
    }

    public void createPolyline(final String string) {
        string.substring(0, string.length() - 2);
        if (this.apiKey.isEmpty()) {
            // throw new Exception("Developper API Key not set !!!!");
        }

        String url = "http://maps.googleapis.com/maps/api/staticmap?";
        url += "&path=color:" + polylineColor + "|weight:" + polylineWeigth + string;
        url += "&size=" + mapWidth + "x" + mapHeight;
        url += "&maptype=" + this.roadmap.name().toLowerCase();
        // url += "&markers=color:blue" + "45.9215,8.1254";
        url += "&sensor=false";
        url += "&zoom" + this.zoomFactor;
        url += "&key=" + this.apiKey;

        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    }

    /**
     *
     * @param bounds
     */
    public void setDimension(final Rectangle bounds) {
        this.mapWidth = (int) bounds.getWidth();
        this.mapHeight = (int) bounds.getHeight();
    }
}
