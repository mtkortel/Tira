/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 *
 * @author Marko Kortelainen
 */
public class Piste {
    private int alku;
    private int loppu;
    private double saasto;

    /**
     * Konstruktori oikoreittiä varten
     * @see nopsatoimitus.data.CW
     * 
     * @param alku      oikoreitin alkupisteen indeksi
     * @param loppu     oikoreitin loppupisteen indeksi
     * @param saasto    saavutettu säästö
     */
    Piste(int alku, int loppu, double saasto) {
        this.alku = alku;
        this.loppu = loppu;
        this.saasto = saasto;
    }

    /**
     * @return the alku
     */
    public int getAlku() {
        return alku;
    }

    /**
     * @param alku the alku to set
     */
    public void setAlku(int alku) {
        this.alku = alku;
    }

    /**
     * @return the loppu
     */
    public int getLoppu() {
        return loppu;
    }

    /**
     * @param loppu the loppu to set
     */
    public void setLoppu(int loppu) {
        this.loppu = loppu;
    }

    /**
     * @return the saasto
     */
    public double getSaasto() {
        return saasto;
    }

    /**
     * @param saasto the saasto to set
     */
    public void setSaasto(double saasto) {
        this.saasto = saasto;
    }
    
    /**
     * Yliajettu metori tulostusta varten
     * @return 
     */
    @Override
    public String toString(){
        return getAlku() + " -> " + getLoppu() + " = " + getSaasto();
    }
}
