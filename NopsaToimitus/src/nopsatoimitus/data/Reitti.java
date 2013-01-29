/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 *  Luokka pitää sisällään reitin ja siihen littyvää tietoa.
 * 
 * @author Marko Kortelainen
 */
public class Reitti {
    ReittiPiste[] pisteet;
    private double pituus = 0;
    int seuraava = 0;
    boolean tilaa =true;
    XMLParser googleParser;
    
    /**
     * Konstruktori joka alustaa reitin, pituuden yms.
     * @param koko Reitin koko (pisteiden määrä)
     */
    public Reitti(int koko){
        googleParser = new XMLParser();
        if (koko > 0){
            pisteet = new ReittiPiste[koko];
            tilaa=true;
        } else {
            tilaa=false;
        }
    }
    /**
     * Asettaa ReittiPisteen paikalleen. Laskee samalla reitin pituutta
     * @param piste Reittipiste
     * @return      Onnistuiko lisääminen
     */
    public boolean setSeuraavaPiste(ReittiPiste piste, boolean useGoogle){
        if (seuraava<pisteet.length){
            pisteet[seuraava] = piste;
            seuraava++;
            if ( seuraava>1 ){
                if (useGoogle){
                   pituus += googleParser.getDistance(pisteet[seuraava-2], pisteet[seuraava-1]);
                } else {
                    pituus += GeoCode.distance(pisteet[seuraava-2], pisteet[seuraava-1]);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean setSeuraavaPiste(ReittiPiste piste, double etaisyys){
        if (seuraava<pisteet.length){
            pisteet[seuraava] = piste;
            pituus += etaisyys;
            seuraava++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return palauttaa reitin pituuden
     */
    public double getPituus() {
        return pituus;
    }
    /**
     * Palauttaa reitin
     * @return 
     */
    public ReittiPiste[] getReitti(){
        return pisteet;
    }
}
