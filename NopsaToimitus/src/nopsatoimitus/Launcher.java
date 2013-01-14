/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus;

import java.util.ArrayList;
import java.util.List;
import nopsatoimitus.data.GeoCode;
import nopsatoimitus.data.ReittiPiste;

/**
 * Kuljetusliike NopsaToimitus
 * Lyhin reitti annettujen reittipisteiden välissä.
 * Yhdessä pisteessä käydään vain kerran.
 * Kauppamatkustajan ongelma.
 * 
 * @author Marko Kortelainen
 * @version 1.0
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(GeoCode.distance(60.197094f, 24.947634f, 60.205273f, 24.963062f));
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        lähtö.setVisit(true);
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste kolmas = new ReittiPiste(60.233345, 25.019796, "Ketokiventie"); // Ketokiventie
        List<ReittiPiste> lista = new ArrayList<>();
        lista.add(lähtö);
        lista.add(eka);
        lista.add(toka);
        lista.add(kolmas);
        
        System.out.println("L -> 1 -> 2 - > 3 : " + (GeoCode.distance(lähtö, eka) + GeoCode.distance(eka, toka) + GeoCode.distance(toka, kolmas)));
        System.out.println("L -> 1 -> 3 - > 2 : " + (GeoCode.distance(lähtö, eka) + GeoCode.distance(eka, kolmas) + GeoCode.distance(kolmas, toka)));
        System.out.println("L -> 2 -> 1 - > 3 : " + (GeoCode.distance(lähtö, toka) + GeoCode.distance(toka, eka) + GeoCode.distance(eka, kolmas)));
        System.out.println("L -> 2 -> 3 - > 1 : " + (GeoCode.distance(lähtö, toka) + GeoCode.distance(toka, kolmas) + GeoCode.distance(kolmas, eka)));
        System.out.println("L -> 3 -> 2 - > 1 : " + (GeoCode.distance(lähtö, kolmas) + GeoCode.distance(kolmas, toka) + GeoCode.distance(toka, eka)));
        System.out.println("L -> 3 -> 1 - > 2 : " + (GeoCode.distance(lähtö, kolmas) + GeoCode.distance(kolmas, eka) + GeoCode.distance(eka, toka)));
        
        Double[][] koe = GeoCode.doMatrix(lista);
        for (int i = 0; i < koe.length; i++){
            for (int j = 0; j < koe.length; j++){
                System.out.print(koe[i][j] + " " );
            }
            System.out.println();
        }
        
        GeoCode.findShortest(lista, koe, 0);
    }
}
