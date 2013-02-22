/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Taulukko Clarke-Wrigth algoritmin reittien tekoa varten
 *
 * @author Marko Kortelainen
 */
public class CWLista {
    int[] reitti;
    
    /**
     * Konstruktori, joka ottaa syötteenä alku ja loppupisteen indeksinumerot
     * 
     * @param alku  Alkupiste
     * @param loppu Loppupiste 
     */
    public CWLista(int alku, int loppu){
        reitti = new int[2];
        reitti[0] = alku;
        reitti[1] = loppu;
    }

    /**
     * Konstruktori, joka ottaa syötteenä kaksi CWListaa, ja yhdistää ne yhdeksi
     * (merge)
     * 
     * @param osa1  uuden CWListan alkuosa
     * @param osa2  uuden CWListan loppuosa
     */
    CWLista(CWLista osa1, CWLista osa2) {
        reitti = new int[osa1.reitti.length + osa2.reitti.length];
        for(int i=0; i < reitti.length; i++){
            if (i<osa1.reitti.length){
                reitti[i]=osa1.reitti[i];
            } else {
                reitti[i]=osa2.reitti[i-osa1.reitti.length];
            }
        }
    }
    
    /** Lisätään reitille joko alkupäähän tai loppupäähän
     * 
     * @param piste     lisättävä piste
     * @param append    lisätäänkö loppuun (true) vai alkuun (false)
     */
    public void add(int piste, boolean append){
        int nyt = reitti.length;
        kasvataTaulukko(1);
        if (append){
            reitti[nyt] = piste;
        } else {
            insert(piste);
        }
    }
    
    /**
     * Lisää pätkän reittiä joko alkuun tai loppuun.
     * 
     * @param mistä reitin lähtöpiste
     * @param mihin reitin loppupiste
     */
    private void add(int mistä, int mihin){
        boolean append=false;
        if (reitti[0] == mistä){
            append=true;
        }
        add(mihin, append);
    }
    /**
     * Lisää pisteen reitin alkuun
     * 
     * @param piste lisättävä piste
     */
    private void insert(int piste) {
        int[] tmp = new int[reitti.length];
        tmp[0] = piste;
        for (int i = 0; i < reitti.length-1; i++){
            tmp[i+1] = reitti[i];
        }
        reitti = tmp;
    }

    /**
     * Kasvattaa taulukoin kokoa halutun arvon verran
     * 
     * @param uusi kasvatettava koko
     */
    private void kasvataTaulukko(int uusi) {
        int[] tmp = new int[reitti.length + uusi];
        for(int i=0; i < reitti.length; i++){
            tmp[i] = reitti[i];
        }
        reitti = tmp;
    }

    /**
     * Tarkastaa löytyykö piste reitiltä
     * @param piste     lisättävän pisteen indeksinumero
     * @return löytyikö?
     */
    public boolean onkoReitilla(int piste) {
        boolean onReitilla=false;
        for(int i=0; i < reitti.length; i++){
            if (piste==reitti[i]){
                onReitilla=true;
            }
        }
        return onReitilla;
    }
    
    /**
     * Tarkastaa ovatko molemmat pisteet jo samalla reitillä.
     * 
     * @param piste1    Tarkastettava piste 1
     * @param piste2    Tarkastettava piste 2
     * @return          Löytyykö molemmat pisteet reitiltä
     */
    public boolean ovatkoReitilla(int piste1, int piste2){
        boolean on1=false;
        boolean on2=false;
        for (int i=0; i < reitti.length; i++){
            if (piste1==reitti[i]){
                on1=true;
            }
            if (piste2==reitti[i]){
                on2=true;
            }
            if (on1&&on2){
                return true;
            }
        }
        if (piste1 == reitti[0] || piste1 == reitti[reitti.length-1]){
            // Sallittu paikka
        }
        if (piste2 == reitti[0] || piste2 == reitti[reitti.length-1]){
            // Sallittu paikka
        }
        
        return on1&&on2;
    }
    
    /** 
     * Tarkastaa onko piste sallitussa paikassa reitillä, eli joko reitin alussa
     * tai lopussa
     * 
     * @param  piste    reittipiste
     * @return boolean  onko sallitussa paikassa
     */
    public boolean sallittuPaikka(int piste){
        int alku = reitti[0];
        int loppu = reitti[reitti.length-1];
        
        if (piste == reitti[0] || piste == reitti[reitti.length-1]){
            return true;
        }
        return false;
    }

    /**
     * Tarkastaa onko piste reitin alussa
     * 
     * @param alku  tarkastettava piste
     * @return      onko piste reitin alussa vai ei
     */
    boolean onkoAlussa(int alku) {
        if (reitti[0]==alku) {
            return true;
        }
        return false;
    }

    /**
     * toString() metodi omaan käyttöön
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reitti.length; i++){
            sb.append(reitti[i]).append(" ");
            if (i > reitti.length-1){
                sb.append(" - > ");
            }
        }
        return sb.toString();
    }
}
