/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus;

import java.util.Calendar;
import nopsatoimitus.data.CW;
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
    public static String proxy_host="";
    public static int proxy_port=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TaulukkoLista<ReittiPiste> lista;
        ReittiReader lukija = new ReittiReader("paikat.txt");
        lista = lukija.lue();
        ReittiPiste[] test = new ReittiPiste[lista.size()-1];
        //Reitti reitti = new Reitti(lista.size());
        // Ilman lähtö/maalipistettä
        for (int i = 1; i < lista.size(); i++){
            test[i-1] = lista.get(i);
        }
         
        int mahd = 1;
        for (int i = 1; i <= lista.size()-1; i++){
            mahd *= i;
        }
        Calendar cal1 = Calendar.getInstance();
        // Tehdään permutaatiot kaikista reittivaihtoehdoista
        Permutaatio perm = new Permutaatio(test, lista.get(0), mahd);
        ReittiPiste[][] kaikki = Permutaatio.kaikki;
        
        Reitti parasReitti = new Reitti(Permutaatio.kaikki[0].length);
        for (int i = 0; i < mahd; i++){
            Reitti yritys = new Reitti(Permutaatio.kaikki[i].length);
            for (int j=0; j< kaikki[i].length; j++){
                yritys.setSeuraavaPiste(kaikki[i][j], false);
                if (yritys.getPituus() > parasReitti.getPituus() && parasReitti.getPituus()>0){
                    j=kaikki[i].length;
                } else {
                    if (j == kaikki[i].length-1){
                        parasReitti = yritys;
                    }
                }
            }
        }
        
        System.out.println("Reittimahdollisuuksia: " + mahd + " kpl.");
        System.out.println("Paras reitti linnuntietä");
        System.out.println("Reitti (BF): "+ parasReitti.getPituus());
        System.out.println("Reitti     : "+ parasReitti.toString());
 
        Calendar cal2 = Calendar.getInstance();
   
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        
        cal1 = Calendar.getInstance();
        System.out.println("Google Maps etäisyydet autolla");
        DistanceMatrix dm = new DistanceMatrix();
        double[][] matkat = dm.getMatrix(lista);
        cal2 = Calendar.getInstance();
        System.out.println("DistanceMatrix haku: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        cal1 = Calendar.getInstance();
        LahinNaapuri ln = new LahinNaapuri(lista, matkat);
        Reitti nn = ln.etsiLyhinReitti();
        System.out.println("Reitti (NN): "+ nn.getPituus());
        System.out.println("Reitti     : "+ nn.toString());
        cal2 = Calendar.getInstance();
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        cal1 = Calendar.getInstance();

        CW cw = new CW(matkat, lista);
        cal2 = Calendar.getInstance();
        System.out.println("Kesto: " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()) + " ms.");
        // Google Maps Distance Matrix hakee nopeammin
        // https://developers.google.com/maps/documentation/distancematrix/
        // http://maps.googleapis.com/maps/api/distancematrix/json?origins=Mannerheimintie+2+Helsinki+Finland|Porvoo+Finland&destinations=Mannerheimintie+2+Helsinki+Finland|Porvoo&language=fi-FI&sensor=false
    }
}
