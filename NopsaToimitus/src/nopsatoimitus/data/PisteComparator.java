/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.Comparator;

/**
 * Piste-luokan vertailuun käänteinen vertailu
 * maksimi prioriteettijonoa varten
 * 
 * @author Marko Kortelainen
 * @see nopsatoimitus.data.Piste
 */
public class PisteComparator implements Comparator<Piste>{

    /**
     * Mahdollistettu käänteinen vertailu (max)
     * 
     * @param o1    ensimmäinen vertailtava kohde
     * @param o2    toinen vertailtava kohde
     * @return      -1 jos ensimmäinen on isompi, 0 jos yhtäisot muuten 1
     */
    @Override
    public int compare(Piste o1, Piste o2) {
        if (o1.getSaasto() <  o2.getSaasto()){
            return 1;
        }
        if (o1.getSaasto() >  o2.getSaasto()){
            return -1;
        }
        return 0;
    }
    
}
