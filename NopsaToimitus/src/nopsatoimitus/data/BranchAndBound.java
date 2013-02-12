/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.Arrays;

/**
 * Breach And Bound algoritmin toteutus
 * 
 * @author Marko Kortelainen
 */
public class BranchAndBound {
    double[][] matkat;
    TaulukkoLista paikat;
    Osoite osoitteet;
    int[] pisteet;
    
    public BranchAndBound(double[][] matkat, TaulukkoLista<ReittiPiste> paikat){
        this.matkat = matkat;
        this.paikat = paikat;
        boolean[] visited = new boolean[matkat.length];
        pisteet = new int[matkat.length];
        System.out.println("BnB : " + gen(Double.MAX_VALUE, 0, visited, pisteet, 0, 0));
        for (int i = 0; i < pisteet.length; i++){
            System.out.print(pisteet[i] + " ");
        }
        System.out.println();
    }
    
    /**
     * Branch-and-bound algoritmi.
     * 
     * @param best      Parhaan löytyneen reitin pituus
     * @param length    Tähänasti löytyneen reitin pituus
     * @param visited   Taulukko käydyistä paikoista
     * @param current   Nyt vierailtava paikka
     * @param k         Kuinka monessa kaupunginssa on käyty
     * @return          Parhaan reitin pituus
     */
    public double gen(double best, double length, boolean[] visited, int[] pisteet,
            int current, int k){
        if (k == matkat.length-1){
            return length + matkat[current][0];
        }
        double mybest = Double.MAX_VALUE;
        for (int i = 1; i < matkat.length; i++){
            if (!visited[i]){
                boolean[] visited2 = luoKopio(visited);
                int[] pisteet2 = luoIntKopio(pisteet);
                visited2[i] = true;
                pisteet2[i] = current;
                if (length+matkat[current][i] < best){
                    double newp = gen(best, length+matkat[current][i], visited2, pisteet2, i, k+1);
                    if (newp < mybest){
                        mybest = newp;
                        //pisteet = pisteet2;
                    }
                    if (newp < best){
                        best = newp;
                        this.pisteet = pisteet2;
                    }
                }
            }
        }
        
        
        
        return best;
    }

    /**
     * Luo kopioin boolean taulukosta
     * 
     * @param visited   alkuperäinen
     * @return          kopio
     */
    private boolean[] luoKopio(boolean[] visited) {
        boolean[] visited2 = new boolean[visited.length];
        for(int i = 0; i < visited.length; i++){
            visited2[i] = visited[i];
        }
        return visited2;
    }

    /**
     * Tekee kopion taulukosta.
     * 
     * @param pisteet   int taulukko
     * @return          kopio
     */
    private int[] luoIntKopio(int[] pisteet) {
        return Arrays.copyOf(pisteet, pisteet.length);
    }
}
