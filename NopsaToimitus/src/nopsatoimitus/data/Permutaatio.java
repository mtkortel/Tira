/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Luokka Reittipisteiden permutaatioiden luomista varten
 * @author Marko Kortelainen
 */
public class Permutaatio {
    private static ReittiPiste[] pisteet;
    private static boolean[] kaynnit;
    private static ReittiPiste[] permutaatiot;
    public static ReittiPiste[][] kaikki;
    private static int maara=0;
    private static ReittiPiste lahto;
    public static int max;
    private static int cur=0;

    /**
     * Construktori
     * 
     * @param alkuperäinen  Alkuperäinen reitti (ilman lähtöä ja loppua)
     * @param lahto         Lähtöpiste (samalla myös loppupiste
     * @param mahd          Mahdollisten reittien määrä
     */
    public Permutaatio(ReittiPiste[] alkuperäinen, ReittiPiste lahto, int mahd) {
        Permutaatio.lahto = lahto;
        max = mahd;
        pisteet = alkuperäinen;
        kaynnit = new boolean[pisteet.length];
        permutaatiot = new ReittiPiste[pisteet.length];
        kaikki = new ReittiPiste[max][pisteet.length+2];
        teePermutaatio(0);
    }

    /**
     * Rakentaa mahdolliset permutaatiot.
     * Metodi tallentaa permutaation taulukkoon myöhempää käyttöä varten ja 
     * kutsuu toista metodia permutaation tekemiseksi.
     * 
     * @param indeksi   Permutaation numero (=monesko)
     */
    private void teePermutaatio(int indeksi) {
        int pe = permutaatiot.length;
        if (indeksi >= permutaatiot.length){
            maara++;
            int i = 1;
            kaikki[cur][0] = lahto;
            kaikki[cur][pisteet.length+1] = lahto;
            for(ReittiPiste piste: permutaatiot){
                kaikki[cur][i] = piste;
                i++;
            }
            cur++;
            return;
        }
        rakennaPermutaatiota(indeksi);
    }
    /**
     * Metodi rakentaa permutaatiota vähän pidemmälle.
     * Ensin tarkastetaan onko kyseinen permutaation osa jo tehty ja 
     * jos on niin mennään seuraavaan, jos ei niin otataan käsittelyyn ja 
     * käydään loppuun.
     * 
     * @param indeksi Monesko permutaatio on kyseessä.
     */
    private void rakennaPermutaatiota(int indeksi) {
        for (int i=0; i < pisteet.length;i++){
            if (kaynnit[i]){
                continue;
            }
            kaynnit[i] = true;
            permutaatiot[indeksi] = pisteet[i];
            teePermutaatio(indeksi + 1);
            kaynnit[i] = false;
        }
    }
}
