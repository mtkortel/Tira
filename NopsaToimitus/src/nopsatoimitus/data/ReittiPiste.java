/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Reittipiste
 * Luokka joka sisältää yhden reitin varrella olevan pisteen
 *
 * @author mkortelainen
 */
public class ReittiPiste {
    private double lon;
    private double lat;
    private boolean visit;
    private double weight;
    private String nimi;
    
    public ReittiPiste(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.visit = false;
        this.weight = 0;
        this.nimi = "";
    }

    public ReittiPiste(double lat, double lon, String nimi) {
        this.lat = lat;
        this.lon = lon;
        this.visit = false;
        this.weight = 0;
        this.nimi = nimi;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the visit
     */
    public boolean isVisit() {
        return visit;
    }

    /**
     * @param visit the visit to set
     */
    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * @param nimi the nimi to set
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
