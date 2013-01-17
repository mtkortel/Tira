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
    private static boolean[] käynnit;
    private static ReittiPiste[] permutaatiot;
    public static ReittiPiste[][] kaikki;
    private static int määrä;
    private static ReittiPiste lähtö;
    public static int max;
    private static int cur;
    
    public Permutaatio(ReittiPiste[] alkuperäinen, ReittiPiste lähtö, int mahd) {
        this.lähtö = lähtö;
        max = mahd;
        cur = 0;
        pisteet = alkuperäinen;
        käynnit = new boolean[pisteet.length];
        permutaatiot = new ReittiPiste[pisteet.length];
        kaikki = new ReittiPiste[max][pisteet.length+2];
        määrä = 0;
        //for(cur=0; cur < max; cur++){
            teePermutaatio(0);
        //}
        //System.out.println("Määrä: " + määrä);
    }

    
        
    

    private void teePermutaatio(int indeksi) {
        int pe = permutaatiot.length;
        if (indeksi >= permutaatiot.length){
            määrä++;
            int i = 1;
            kaikki[cur][0] = lähtö;
            kaikki[cur][pisteet.length+1] = lähtö;
            for(ReittiPiste piste: permutaatiot){
                //System.out.print(" " + piste.getNimi() + " ");
                kaikki[cur][i] = piste;
                i++;
            }
            cur++;
           // System.out.println();
            return;
        }
        for (int i=0; i < pisteet.length;i++){
            /*if (i==1){
                permutaatiot[cur][0] = lähtö;
                käynnit[0]=true;
            }*/
            if (käynnit[i]){
                continue;
            }
            käynnit[i] = true;
            permutaatiot[indeksi] = pisteet[i];
            teePermutaatio(indeksi + 1);
            käynnit[i] = false;
        }
    }
}
