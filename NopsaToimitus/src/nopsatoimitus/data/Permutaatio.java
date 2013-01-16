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
    public static ReittiPiste[][] permutaatiot;
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
        permutaatiot = new ReittiPiste[max][pisteet.length+1];
        määrä = 0;
        //for(cur=0; cur < max; cur++){
            teePermutaatio(0);
        //}
        System.out.println("Määrä: " + määrä);
    }

    
        
    

    private void teePermutaatio(int indeksi) {
        int pe = permutaatiot[0].length;
        if (indeksi >= permutaatiot[0].length-1){
            määrä++;
            for(ReittiPiste piste: permutaatiot[cur]){
                System.out.print(" " + piste.getNimi() + " ");
            }
            System.out.println();
            return;
        }
        for (int i=0; i < pisteet.length;i++){
            if (i==1){
                permutaatiot[cur][0] = lähtö;
                käynnit[0]=true;
            }
            if (käynnit[i]){
                continue;
            }
            käynnit[i] = true;
            permutaatiot[cur][indeksi+1] = pisteet[i];
            teePermutaatio(indeksi + 1);
            käynnit[i] = false;
        }
    }
}
