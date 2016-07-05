/* @(#)GoogleEditorPane.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * @author DCNS
 *
 */
public class JGoogleMapEditorPan extends JEditorPane {
    /**
     *
     */
    private static final long serialVersionUID = -3128841669458527858L;
    private int               zoomFactor       = 7;
    private String            ApiKey           = "AIzaSyCZH1L9HGjHheMV7AdJnRRAy_0n75nXiD0";
    private String            roadmap          = "roadmap";
    public final String       viewTerrain      = "terrain";
    public final String       viewSatellite    = "satellite";
    public final String       viewHybrid       = "hybrid";
    public final String       viewRoadmap      = "roadmap";

    /**
     * Constructeur: initialisation du EditorKit
     */
    public JGoogleMapEditorPan() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
    }

    /**
     * Fixer le zoom
     *
     * @param zoom
     *            valeur de 0 à 21
     */
    public void setZoom(final int zoom) {
        this.zoomFactor = zoom;
    }

    /**
     * Fixer la clé de developpeur
     *
     * @param key
     *            APIKey fourni par Google
     */
    public void setApiKey(final String key) {
        this.ApiKey = key;
    }

    /**
     * Fixer le type de vue
     *
     * @param roadMap
     */
    public void setRoadmap(final String roadMap) {
        this.roadmap = roadMap;
    }

    /**
     * Afficher la carte d'après des coordonnées GPS
     *
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @throws Exception
     *             erreur si la APIKey est non renseignée
     */
    public void showCoordinate(final String latitude, final String longitude, final int width, final int height) throws Exception {
        this.setMap(latitude, longitude, width, height);
    }

    /**
     * Afficher la carte d'après une ville et son pays
     *
     * @param city
     * @param country
     * @param width
     * @param height
     * @throws Exception
     *             erreur si la APIKey est non renseignée
     */
    public void showLocation(final String city, final String country, final int width, final int height) throws Exception {
        this.setMap(city, country, width, height);
    }

    /**
     * Assembler l'url et Générer le code HTML
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws Exception
     */
    private void setMap(final String x, final String y, final Integer width, final Integer height) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key not set !!!!");
        }

        String url = "http://maps.googleapis.com/maps/api/staticmap?";
        url += "center=" + x + "," + y;
        url += "&zoom=" + this.zoomFactor;
        url += "&size=" + width.toString() + "x" + height.toString();
        url += "&maptype=" + this.roadmap;
        url += "&markers=color:blue" + x + "," + y;
        url += "&sensor=false";
        url += "&key=" + this.ApiKey;

        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    }
}
