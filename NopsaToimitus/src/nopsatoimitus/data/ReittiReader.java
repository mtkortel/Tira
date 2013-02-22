/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nopsatoimitus.Launcher;

/**
 * Lukee reitin tiedostosta
 * @author Marko Kortelainen
 * @see nopsatoimitus.data.ReittiPiste
 */
public class ReittiReader {
    private final String tiedosto;
    
    /**
     * Konstruktori
     * @param tiedosto Tiedoston polku ja nimi, jossa reitti sijaitsee
     */
    public ReittiReader(String tiedosto){
        this.tiedosto = tiedosto;
    }
    
    /**
     * Metodi palauttaa reitin taulukkomuodossa
     * @return Taulukko reittipisteistä
     */
    public TaulukkoLista<ReittiPiste> lue(){
        TaulukkoLista<ReittiPiste> pisteet = new TaulukkoLista<ReittiPiste>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
            String rivi;
            int row = 0;
            while ((rivi = reader.readLine()) != null){
                ReittiPiste piste;
                if (row==0){
                    hasProxy(rivi);
                    row++;
                } else { // GPS pisteet
                    String[] jako = rivi.split(",");
                    piste = setPisteTiedot(jako);
                    pisteet.add(piste);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ReittiReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return pisteet;
    }

   

    /**
     * Lukee tiedostosta proxyasetukset
     * @param rivi Proxyasetukset
     */
    private void hasProxy(String rivi) {
        String[] prox = rivi.split(":");
        if (prox.length==2){
            Launcher.proxy_host = prox[0];
            Launcher.proxy_port = Integer.parseInt(prox[1]);
        }
    }
    /**
     * Lukee riviltä pistetiedot
     * @param jako  rivi tiedostonsta taulukoksi muutettuna
     * @return  Reittipiste-tiedot
     * @throws NumberFormatException Virheellinen numeerinen tieto
     */
    private ReittiPiste setPisteTiedot(String[] jako) throws NumberFormatException {
        ReittiPiste piste;
        if (jako.length > 2){
            piste = new ReittiPiste(Double.parseDouble(jako[0].trim()), Double.parseDouble(jako[1].trim()), jako[2].trim());
        } else {
            piste = new ReittiPiste(Double.parseDouble(jako[0].trim()), Double.parseDouble(jako[1].trim()), jako[0] + "-" + jako[1]);
        }
        return piste;
    }
}
