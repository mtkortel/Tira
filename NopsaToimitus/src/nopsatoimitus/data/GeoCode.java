/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.List;

/**
 * GPS koordinaattien koodausta ja tulkkausta
 *
 * @author Marko Kortelainen
 */
public class GeoCode {
    
    /**
     * Kahden GPS pisteen etäisyys kilometreissä.
     * http://www.movable-type.co.uk/scripts/latlong.html
     * 
     * @param lat_a Lähtöpisteen latitude
     * @param lon_a Lähtöpisteen longitude
     * @param lat_b Kohdepisteen latitude
     * @param lon_b Kohdepisteen longitude
     * @return Etäisyys kilometreinä
     */
    private static double distance(double lat_a, double lon_a, double lat_b, double lon_b) {
        int R = 6371; // km
        double dLat = Math.toRadians(lat_b - lat_a);
        double dLon = Math.toRadians(lon_b - lon_a);
        double lat1 = Math.toRadians(lat_a);
        double lat2 = Math.toRadians(lat_b);
        
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1)*Math.cos(lat2);
        return R*(2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
    }
    /**
     * Palauttaa kahden reittipisteen välisen etäisyyden.
     * 
     * @param point_a   Lähtöpiste
     * @param point_b   Kohdepiste
     * @return          Etäisyys kilometreinä
     */
    public static double distance(ReittiPiste point_a, ReittiPiste point_b){
        return GeoCode.distance(point_a.getLat(), point_a.getLon(), point_b.getLat(), point_b.getLon());
    }

    /*
    public static Double[][] doMatrix(List<ReittiPiste> lista) {
        int koko = lista.size();
        Double[][] matriisi = new Double[koko][koko];
        for(int i = 0; i < koko; i++){
            for(int j = 0; j < koko; j++){
                if (matriisi[i][j] == null){
                    Double matka = GeoCode.distance(lista.get(i), lista.get(j));
                    matriisi[i][j] = matka;
                    matriisi[j][i] = matka;
                }
            }
        }
        
        return matriisi;
    }
*/
    /*
    public static void findShortest(List<ReittiPiste> lista, Double[][] matriisi, int lahtö) {
        int koko = matriisi.length;
        //Double pituus = 0;
        ReittiPiste piste = lista.get(lahtö);
        for(int i = 0; i < koko; i++){
            for(int j = 0; j < koko; j++){
                
                if (matriisi[i][j] == null){
                    Double matka = GeoCode.distance(lista.get(i), lista.get(j));
                    matriisi[i][j] = matka;
                    matriisi[j][i] = matka;
                }
            }
        }
    }
*/
    /*
    public static ReittiPiste[][] doMatrix2(List<ReittiPiste> lista) {
        int koko = lista.size();
        ReittiPiste[][] matriisi = new ReittiPiste[koko][koko];
        for(int i = 0; i < koko; i++){
            for(int j = 0; j < koko; j++){
                if (matriisi[i][j] == null){
                    if (j==0){
                        matriisi[i][j] = lista.get(0);
                    } else {
                        matriisi[i][j] = lista.get(j);
                    }
                }
            }
        }
        
        return matriisi;
    }*/
}
