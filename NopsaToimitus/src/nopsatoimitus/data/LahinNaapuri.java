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
     * @return 
     */
    public Reitti etsiLyhinReitti(){
        reitti.setSeuraavaPiste(lista.get(0), 0); // Asetetaan lähtöpiste
        kayty[0] = true;
        // Onko vielä käyty kaikkialla
        int viimeisin;
        int viimInd = 0;
        while(Arrays.toString(kayty).contains("f")){
            viimeisin = etsiLyhinNaapuri(viimInd);
            reitti.setSeuraavaPiste(lista.get(viimeisin), matkat[viimInd][viimeisin]);
            kayty[viimeisin] = true;
            viimInd++;
        }
        reitti.setSeuraavaPiste(lista.get(0), matkat[viimInd-1][0]);
        
        return reitti;
    }

    /**
     * Etsii lähimmän naapurin
     * 
     * @param   lähtöpiste etsintään
     * @return  lähimmän naapurin indeksi
     */
    private int etsiLyhinNaapuri(int mistaEtsitaan) {
        int rp = -1;
        double etaisyys = -1;
        for (int i = 0; i < kayty.length; i++){
            if (!kayty[i]){
                if (etaisyys == -1){ // ensimmäinen
                    etaisyys = matkat[mistaEtsitaan][i];
                } else if (etaisyys > matkat[mistaEtsitaan][i]){
                    etaisyys = matkat[mistaEtsitaan][i];
                    rp = i;
                }
            }
        }
        return rp;
    }
 
    /**
     * Palauttaa lyhimmän reitin
     * @return  lyhin reitti
     */
    public Reitti getLyhinReitti(){
        return reitti;
    }
}
