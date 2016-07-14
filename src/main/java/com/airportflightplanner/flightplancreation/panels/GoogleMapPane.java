/* @(#)GoogleEditorPane.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.airportflightplanner.common.types.MapType;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapModelProperties;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.processor.GoogleMapProcessor;
import com.jgoodies.binding.PresentationModel;

/**
 * @author DCNS
 *
 */
public class GoogleMapPane extends JEditorPane {
    /**
     *
     */
    private static final long                       serialVersionUID = -3128841669458527858L;
    /** */
    private String                                  zoomFactor       = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_ZOOM_FACTOR);
    /** */
    private final String                            apiKey           = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_KEY);
    /** */
    private MapType                                 roadmap          =                                                                           //
            MapType.valueOf(CommonProperties.getPropertyValue(CommonProperties.GOOGLE_MAPTYPE));
    /** */
    private final String                            polylineColor    = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_COLOR);
    /** */
    private final String                            polylineWeigth   = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_WIDTH);
    /** */
    private static final int                        DEFAULT_SIZE     = 400;
    /** */
    private int                                     mapWidth         = DEFAULT_SIZE;
    /** */
    private int                                     mapHeight        = DEFAULT_SIZE;
    /** */
    private final PresentationModel<GoogleMapModel> googleMapModel;
    /** */
    HTMLEditorKit                                   kit              = new HTMLEditorKit();
    /** */
    HTMLDocument                                    htmlDoc          = (HTMLDocument) kit.createDefaultDocument();

    /**
     * Constructeur: initialisation du EditorKit
     *
     * @param googleMapModel
     */
    public GoogleMapPane(final PresentationModel<GoogleMapModel> googleMapModel) {
        this.googleMapModel = googleMapModel;
        googleMapModel.addBeanPropertyChangeListener(GoogleMapModelProperties.POLYLINE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                updateMap();
            }
        });

        build();
    }

    /**
     *
     */
    private void build() {
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img width=" + mapWidth + " height=" + mapHeight + " src='file:" + //
                getClass().getClassLoader().getResource("images/default.jpg").getPath() + "'>";
        html += "</body></html>";
        this.setText(html);
    }

    /**
     * Fixer le zoom.
     *
     * @param zoom
     *            valeur de 0 a 21
     */
    public void setZoom(final int zoom) {
        this.zoomFactor = String.valueOf(zoom);
    }

    /**
     * Fixer le type de vue.
     *
     * @param roadMap
     *            the roadMap.
     */
    public void setRoadmap(final MapType roadMap) {
        this.roadmap = roadMap;
    }

    /**
     *
     */
    protected void updateMap() {
        if (this.apiKey.isEmpty()) {
            throw new IllegalArgumentException("Developper API Key not set !!!!");
        }

        String url = "http://maps.googleapis.com/maps/api/staticmap?";
        url += "&path=color:" + polylineColor + "|weight:" + polylineWeigth + "|geodesic:true";
        url += GoogleMapProcessor.getEncodedRoad(googleMapModel.getBean());
        url += "&size=" + mapWidth + "x" + mapHeight;
        url += "&maptype=" + this.roadmap.name().toLowerCase();
        url += "&sensor=false";
        url += "&zoom" + this.zoomFactor;
        url += "&key=" + this.apiKey;

        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img width=" + mapWidth + " height=" + mapHeight + " src='" + url + "'>";
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
