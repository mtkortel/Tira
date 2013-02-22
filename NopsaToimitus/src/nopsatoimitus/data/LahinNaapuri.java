/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.Arrays;

/**
 * Luokka, joka selvittää etäisyyden lähimmän naapurin periaatteen mukaan
 * Nearest-Neighbourgh (NN)
 * @author Marko Kortelainen
 */
public class LahinNaapuri {

    TaulukkoLista<ReittiPiste> lista;
    double[][] matkat;
    boolean[] kayty;
    Reitti reitti;
    boolean isEka=true;
    int paikka=0;
    
    /**
     * Konstruktori, joka alustaa asiat
     * @param lista     Lista reittipisteistä
     * @param matkat    Matriisi reittipisteiden välisistä etäisyyksistä
     */
    public LahinNaapuri(TaulukkoLista<ReittiPiste> lista, double[][] matkat) {
        this.lista = lista;
        this.matkat = matkat;
        this.kayty = new boolean[matkat.length];
        // Listalla on lähtöpiste, muttei maalia
        reitti = new Reitti(lista.size+1);
    }
    
    /**
     * Lyhimmän reitin etsinnän aloitus
     * @return lyhin reitti käyttäen hyväksi lähimmän naapurin algoritmiä
     */
    public Reitti etsiLyhinReitti(){
        reitti.setSeuraavaPiste(lista.get(0), 0);
        kayty[0]=true;
        int viimeinen = etsiLyhinNaapuri(0);
        //reitti.setSeuraavaPiste(lista.get(viimeinen), matkat[viimeinen][0]);
        reitti.setSeuraavaPiste(lista.get(0), matkat[viimeinen][0]);
        return reitti;
    }

    /**
     * Etsii lähimmän naapurin
     * 
     * @param   lähtöpiste etsintään
     * @return  viimeisimmän reittipisteen indeksi ennen maalia
     */
     private int etsiLyhinNaapuri(int rivi){
        double matka=Double.MAX_VALUE;        
        for (int sar=0; sar < kayty.length; sar++){
            if (!kayty[sar]){
                if (matkat[rivi][sar]<matka){
                    matka = matkat[rivi][sar];
                       paikka = sar;
                }
            }
        }
        merkitsePiste(matka, rivi);
        return paikka;
    }

    /**
     * Merkitsee pisteen reitille ja siirtyy seuraavaan
     * @param matka
     * @param rivi 
     */ 
    private void merkitsePiste(double matka, int rivi) {
        reitti.setSeuraavaPiste(lista.get(paikka), matka);
        kayty[paikka]=true;
        
        rivi++;
        //if (rivi < matkat.length){
        if (!allTrue(kayty)){
            paikka=0;
            etsiLyhinNaapuri(rivi);
        }
    }
    
    /**
     * Tarkastaa onko boolean taulukon kaikki alkiot tosia
     * @param taulukko  tarkastettava taulukko
     * @return          onko kaikki arvot tosia
     */
    private boolean allTrue(boolean[] taulukko){
        for(boolean b: taulukko){
            if (!b){
                return false;
            }
        }
        return true;
    }
}
