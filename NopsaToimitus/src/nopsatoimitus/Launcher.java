/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus;

import java.util.Calendar;
import nopsatoimitus.data.BranchAndBound;
import nopsatoimitus.data.DistanceMatrix;
import nopsatoimitus.data.LahinNaapuri;
import nopsatoimitus.data.Permutaatio;
import nopsatoimitus.data.Reitti;
import nopsatoimitus.data.ReittiPiste;
import nopsatoimitus.data.ReittiReader;
import nopsatoimitus.data.TaulukkoLista;

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
        /*
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        lähtö.setVisit(true);
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste kolmas = new ReittiPiste(60.233345, 25.019796, "Ketokiventie"); // Ketokiventie
        ReittiPiste neljäs = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
        ReittiPiste viides = new ReittiPiste(60.271791, 24.806442, "Sarkatie");
        ReittiPiste kuudes = new ReittiPiste(60.130991, 24.514039, "LM Ericsson");
        ReittiPiste seiska = new ReittiPiste(60.31271, 25.539694, "Neste");
        ReittiPiste kasi = new ReittiPiste(60.866459, 26.704373, "Kouvola VR");
        ReittiPiste ysi = new ReittiPiste(59.81955,22.944968, "Hanko Satama");
        */
        
        //List<ReittiPiste> lista = new ArrayList<>();
        
        TaulukkoLista<ReittiPiste> lista;
        ReittiReader lukija = new ReittiReader("paikat.txt");
        lista = lukija.lue();
        boolean isAddress = lukija.isAddress();
        /*
        lista.add(lähtö);
        lista.add(eka);
        lista.add(toka);
        lista.add(kolmas);
        lista.add(neljäs);
        lista.add(viides);
        lista.add(kuudes);
        lista.add(seiska);
        lista.add(kasi);
        lista.add(ysi);
        */ 
        ReittiPiste[] test = new ReittiPiste[lista.size()-1];
        Reitti reitti = new Reitti(lista.size());
        /*
        for (int i = 0; i < lista.size(); i++){//ReittiPiste piste: lista){
            reitti.setSeuraavaPiste(lista.get(i));
        }
        */
        // Ilman lähtö/maalipistettä
        for (int i = 1; i < lista.size(); i++){
            test[i-1] = lista.get(i);
        }
         
        int mahd = 1;
        for (int i = 1; i <= lista.size()-1; i++){
            mahd *= i;
        }
        Calendar cal1 = Calendar.getInstance();
        Permutaatio perm = new Permutaatio(test, lista.get(0), mahd);
        
        ReittiPiste[][] kaikki = Permutaatio.kaikki;
        Reitti parasReitti = new Reitti(Permutaatio.kaikki[0].length);
        Reitti huonoin = new Reitti(Permutaatio.kaikki[0].length);
        if (!isAddress){
            for (int i = 0; i < mahd; i++){
                Reitti yritys = new Reitti(Permutaatio.kaikki[i].length);
                for (int j=0; j< kaikki[i].length; j++){
                    //System.out.print(kaikki[i][j].getNimi());
                    yritys.setSeuraavaPiste(kaikki[i][j], false);
                    if (yritys.getPituus() > parasReitti.getPituus() && parasReitti.getPituus()>0){
                        j=kaikki[i].length;
                    } else {
                        if (j == kaikki[i].length-1){
                            parasReitti = yritys;
                        }
                    }
                    if (j < kaikki[i].length-1){
                        //System.out.print(" -> ");
                    } else {
                        //System.out.print(" = " + yritys.getPituus());
                        //if (huonoin.getPituus() < yritys.getPituus()){
                          //  huonoin = yritys;
                        //}
                    }
                }
                //System.out.println();
            }
        }
        
        
        System.out.println("Reittimahdollisuuksia: " + mahd + " kpl.");
        System.out.println("Paras reitti linnuntietä");
        System.out.println("Reitti (BF): "+ parasReitti.getPituus());
        System.out.println("Reitti     : "+ parasReitti.toString());
        /*
        for (int j=0; j< parasReitti.getReitti().length; j++){
            System.out.print(parasReitti.getReitti()[j].getNimi());
            if (j < kaikki[j].length-1){
                System.out.print(" -> ");
            } else {
                System.out.println(" = " + parasReitti.getPituus());
            }
        }
        */ 
        Calendar cal2 = Calendar.getInstance();
        /*
        System.out.println("Huonoin reitti linnuntietä");
        for (int j=0; j< huonoin.getReitti().length; j++){
            System.out.print(huonoin.getReitti()[j].getNimi());
            if (j < kaikki[j].length-1){
                System.out.print(" -> ");
            } else {
                System.out.println(" = " + huonoin.getPituus());
            }
        }
        */ 
        //System.out.println();
        
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        
        cal1 = Calendar.getInstance();
        System.out.println("Google Maps etäisyydet autolla");
        DistanceMatrix dm = new DistanceMatrix();
        double[][] matkat = dm.getMatrix(lista);
        LahinNaapuri ln = new LahinNaapuri(lista, matkat);
        Reitti nn = ln.etsiLyhinReitti();
        System.out.println("Reitti (NN): "+ nn.getPituus());
        System.out.println("Reitti     : "+ nn.toString());
        cal2 = Calendar.getInstance();
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        cal1 = Calendar.getInstance();
        System.out.println("Branch-and-Bound algoritmi");
        
        cal2 = Calendar.getInstance();
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        
        // Google Maps Distance Matrix hakee nopeammin
        // https://developers.google.com/maps/documentation/distancematrix/
        // http://maps.googleapis.com/maps/api/distancematrix/json?origins=Hyvink%C3%A4%C3%A4+Finland|Porvoo+Finland&destinations=Hyvink%C3%A4%C3%A4+Finland|Porvoo+Finland&language=fi-FI&sensor=false
        
        ///TODO: Haetaan distancematrixilla etäisyydet matriisiin
        /*
         * Esimerkkimatriisi
         *   L  1  2  3  M
         *   0  5  2  3  0
         *   5  0  2  2  5
         *   2  2  0  4  2
         *   3  2  2  0  3
         *   0  5  2  3  0
         * Sitten etsimään reittiä siten että L on ensimmäinen ja M viimeinen....
         * - bruteforce
         * - breach-and-bound ???
         */
        /*
        System.out.println("Haku kestää kauan....");
        for (int i = 0; i < mahd; i++){
            Reitti yritys = new Reitti(Permutaatio.kaikki[i].length);
            for (int j=0; j< kaikki[i].length; j++){
                yritys.setSeuraavaPiste(kaikki[i][j], true);
                if (yritys.getPituus() > parasReitti.getPituus() && parasReitti.getPituus()>0){
                } else {
                    if (j == kaikki[i].length-1){
                        parasReitti = yritys;
                    }
                }
                if (j < kaikki[i].length-1){
                } else {
                    if (huonoin.getPituus() < yritys.getPituus()){
                        huonoin = yritys;
                    }
                }
            }
        }
        System.out.println("Reittimahdollisuuksia: " + mahd + " kpl.");
        System.out.println("Paras reitti autotietä");
        for (int j=0; j< parasReitti.getReitti().length; j++){
            System.out.print(parasReitti.getReitti()[j].getNimi());
            if (j < kaikki[j].length-1){
                System.out.print(" -> ");
            } else {
                System.out.println(" = " + parasReitti.getPituus());
            }
        }
        System.out.println("Huonoin reitti autotietä");
        for (int j=0; j< huonoin.getReitti().length; j++){
            System.out.print(huonoin.getReitti()[j].getNimi());
            if (j < kaikki[j].length-1){
                System.out.print(" -> ");
            } else {
                System.out.println(" = " + huonoin.getPituus());
            }
        }
        System.out.println();
        cal2 = Calendar.getInstance();
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        */
    }
}
