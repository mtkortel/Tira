/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 *
 * @author mkortelainen
 */
public class Reitti {
    ReittiPiste[] pisteet;
    private double pituus;
    int seuraava;
    boolean tilaa;
    
    public Reitti(int koko){
        if (koko > 0){
            pisteet = new ReittiPiste[koko];
            pituus = -1;
            seuraava=0;
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
    public boolean setSeuraavaPiste(ReittiPiste piste){
        if (tilaa){
            pisteet[seuraava] = piste;
            seuraava++;
            if ( seuraava>1 ){
                pituus += GeoCode.distance(pisteet[seuraava-2], pisteet[seuraava-1]);
            }
            if (seuraava==pisteet.length){
                tilaa=false;
            }
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
