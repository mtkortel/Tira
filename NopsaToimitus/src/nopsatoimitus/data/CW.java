/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Clarke-Wright algoritmi
 * @author Marko Kortelainen
 * @see http://www.seas.gwu.edu/~simhaweb/champalg/tsp/tsp.html
 * 
 * Suunnitelma:
 *  1.   Identify a hub vertex h
 *  2.   VH = V - {h}
 *  3.   for each i,j != h
 *  4.      compute savings(i,j)
 *  5.   endfor
 *  6.   sortlist = Sort vertex pairs in decreasing order of savings
 *  7.   while |VH| > 2
 *  8.      try vertex pair (i,j) in sortlist order
 *  9.      if (i,j) shortcut does not create a cycle
 *             and degree(v) ≤ 2 for all v
 *  10.          add (i,j) segment to partial tour
 *  11.          if degree(i) = 2
 *  12.             VH =  VH - {i}
 *  13.          endif
 *  14.          if degree(j) = 2
 *  15.             VH =  VH - {j}
 *  16.          endif
 *  17.     endif
 *  18.  endwhile
 *  19.  Stitch together remaining two vertices and hub into final tour   
 * 
 */
public class CW {
    double pituus = Double.MAX_VALUE;
    
    
    public CW(double[][] matkat, TaulukkoLista<ReittiPiste> lista) {
        pituus = laskeAlkuReitti(matkat);
    }

    /**
     * Lasketaan alustava reitti 1-n järjestyksessä
     * 
     * @param matkat matriisi etäisyyksistä
     * @return  alustavan reitin pituus
     */
    private double laskeAlkuReitti(double[][] matkat) {
        double matka = 0;
        for (int i = 0 ; i < matkat.length-1; i++){
            matka += matkat[i][i+1];
        }
        matka = matkat[matkat.length][0];
        return matka;
    }
    
}
