/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus;

import java.util.ArrayList;
import java.util.List;
import nopsatoimitus.data.GeoCode;
import nopsatoimitus.data.Permutaatio;
import nopsatoimitus.data.Reitti;
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
        //ReittiPiste[][] rp_matriisi = GeoCode.doMatrix2(lista);
        //rp_matriisi = new ReittiPiste[lista.size()][lista.size()];
        /*
        for (int i = 0; i < koe.length; i++){
            for (int j = 0; j < koe.length; j++){
                System.out.print(koe[i][j] + " " );
            }
            System.out.println();
        }
        for (int i = 0; i < rp_matriisi.length; i++){
            for (int j = 0; j < rp_matriisi.length; j++){
                ReittiPiste rp = rp_matriisi[i][j];
                System.out.print(rp.getNimi() + " " );
            }
            System.out.println();
        }*/
        //GeoCode.findShortest(lista, koe, 0);
        
        ReittiPiste[] test = new ReittiPiste[lista.size()-1];
        
        Reitti reitti = new Reitti(lista.size());
        for (int i = 0; i < lista.size(); i++){//ReittiPiste piste: lista){
            reitti.setSeuraavaPiste(lista.get(i));
        }
        for (int i = 1; i < lista.size(); i++){//ReittiPiste piste: lista){
            test[i-1] = lista.get(i);
        }
        System.out.println("Reitin pituus: " + reitti.getPituus());
        int mahd = 1;
        for (int i = 1; i <= lista.size()-1; i++){
            mahd *= i;
        }
        System.out.println("Permutaatioita: " + mahd);
        Permutaatio perm = new Permutaatio(test, lista.get(0), mahd);
        Reitti parasReitti = new Reitti(lista.size());
        /*
        //for (int i=0; i < Permutaatio.max; i++){
            Reitti yritys = new Reitti(lista.size());
            for (int j=0; j<lista.size(); j++){
                //yritys.setSeuraavaPiste(Permutaatio.permutaatiot[i][j] );
                yritys.setSeuraavaPiste(Permutaatio.permutaatiot[0][j] );
                if (parasReitti.getPituus()>-1 && parasReitti.getPituus() < yritys.getPituus()){
                    j=lista.size();
                }
            }
            if (parasReitti.getPituus()<0 || parasReitti.getPituus() > yritys.getPituus()){
                parasReitti = yritys;
            }
        //}
        for (int i = 0; i < parasReitti.getReitti().length; i++){
            System.out.print(parasReitti.getReitti()[i].getNimi() + " ");
        }
        System.out.println( parasReitti.getPituus());
        */
    }
}
