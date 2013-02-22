/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Clarke-Wright algoritmi
 * @author Marko Kortelainen
 * @see http://www.seas.gwu.edu/~simhaweb/champalg/tsp/tsp.html
 * @see http://web.mit.edu/urban_or_book/www/book/chapter6/6.4.12.html
 * Suunnitelma:
 *  1. lähtöpisteen määrittäminen
 *  2. lasketaan säästö matkalle verrattuna siihen että kuljettaisiin 
 *     molempiin pisteisiin lähtöpisteen kautta ja lisätään
 *     jonoon
 *  3. Otetaan jonosta suurimman säästön tuoma reitti ja lisätään pisteet reitille
 *     JOS:
 *      a) kumpikaan piste ei ole reiteillä, jolloin tulee uusi pätkä reittiä
 *          -> CWLista.ovatReitillä
 *      b) toinen pisteistä on olemassa olevalla reitillä, mutta kyseinen piste
 *         on reitin päässä (ei keskellä), jolloin toinen (ei reitillä oleva piste)
 *         lisätään olemassa olevalle reitille
 *      c) molemmat pisteet ovat eri reiteillä ja molemmat pisteet ovat eri reiteillä,
 *         sekä molemmat pisteet ovat reittien päissä, jolloin reitit yhdistetään
 *  4. Palataan kohtaan 3 kunnes säästölista on käyty läpi.
 * 
 */
public class CW {
    double pituus = Double.MAX_VALUE;
    double[][] saasto;
    int hub = 0;
    private final double[][] matkat;
    Comparator<Piste> comparator = new PisteComparator();
    PriorityQueue<Piste> jono = new PriorityQueue<Piste>(10, comparator);
    TaulukkoLista<CWLista> reitit = new TaulukkoLista<CWLista>();
    private final TaulukkoLista<ReittiPiste> lista;
    
    /**
     * Konstruktori, joka ottaa talteen matriisin etäisyyksistä, sekä listan
     * reittipisteistä
     * 
     * @param matkat    etäisyysmatriisi
     * @param lista     reittipisteet
     */
    public CW(double[][] matkat, TaulukkoLista<ReittiPiste> lista) {
        this.matkat = matkat;
        this.lista = lista;
        pituus = laskeAlkuReitti(matkat);
        saasto = new double[matkat.length][matkat.length];
        laskeSaasto();
        //debugSaastot();
        rakennaReitti();
        lisääLähtöPaikka();
        tulostaReitti();
    }

    /**
     * Lasketaan alustava reitti 1-n järjestyksessä
     * 
     * @param matkat matriisi etäisyyksistä
     * @return  alustavan reitin pituus
     */
    private double laskeAlkuReitti(double[][] matkat) {
        double matka = 0;
        for (int i = 0 ; i < matkat.length-1; i++){
            matka += matkat[i][i+1];
        }
        matka = matkat[matkat.length-1][0];
        return matka;
    }

    /**
     * Lasketaan eri pisteiden välinen säästö matkaan verrattuna siihen että
     * reitti kulkisi aluspisteen kautta.
     */
    private void laskeSaasto() {
        for (int i = 1; i < saasto.length; i++){
            for (int j = 1; j < saasto.length; j++){
                if (i != j){
                    double ss = matkat[hub][i] + matkat[hub][j] - matkat[i][j];
                    Piste piste = new Piste(i, j, ss);
                    jono.add(piste);
                }
            }
        }
    }

    /**
     * Tulostaa reittiä, poistetaan tulevaisuudessa....
     */
    private void debugSaastot() {
        while(jono.size() != 0){
            System.out.println(jono.remove());
        }
    }

    /**
     * Rakentaa reittiä valmiiksi
     */
    private void rakennaReitti() {
        while (jono.size() != 0){
            Piste piste = jono.remove(); // Pisimmän säästön aikaansaava pätkä
            // Tarkastetaan ettei kumpikaan ole reitillä kielletyssä paikassa
            if (!ovatkoMilläänReitillä(piste)){
                luoUusiReitti(piste);
            } else {
                if (onkoVainToinenSallitussaPaikassaJollainReitillä(piste)){
                    lisääReitille(piste);
                } else if (ovatkoMolemmatEriReiteilläSallituissaPaikoissa(piste)){
                    mergeReitit(piste);
                }
            }
            
        }
    }

    /**
     * Tarkastaa ovatko molemmat olemassa olevalla (samalla) reitillä, 
     * 
     * @param piste tarkastettava reitinpätkä
     * @return      löytyvätkö samalta reitiltä
     */
    private boolean ovatkoMilläänReitillä(Piste piste) {
        for (int i = 0; i < reitit.size; i++){
            CWLista reitti = reitit.get(i);
            boolean eka = reitti.onkoReitilla(piste.getAlku());
            boolean tok = reitti.onkoReitilla(piste.getLoppu());
            if (eka||tok){
                return true;
            }
        }
        return false;
    }

    /**
     * Lisätään uusi pätkä reitti reittitaulukkoon
     * @param piste Lisättävä reittipisteet
     */
    private void luoUusiReitti(Piste piste) {
        CWLista reitti = new CWLista(piste.getAlku(), piste.getLoppu());
        reitit.add(reitti);
    }

    /**
     * Metodi tarkastaa onko VAIN toinen sallitussa paikassa jollain reitillä.
     * Mikäli molemmat löytyvät palautetaan false.
     * 
     * @param piste Etsittävä piste
     * @return      Löytyykö vain toinen sallitussa paikassa reitillä
     */
    private boolean onkoVainToinenSallitussaPaikassaJollainReitillä(Piste piste) {
        boolean ok1=false;
        boolean ok2=false;
        boolean ok1S=false;
        boolean ok2S=false;
        for (int i=0; i<reitit.size; i++){
            CWLista reitti = reitit.get(i);
                if (reitti.onkoReitilla(piste.getAlku())){
                    ok1S = reitti.sallittuPaikka(piste.getAlku());
                    ok1=true;
                } 
                if (reitti.onkoReitilla(piste.getLoppu())){
                    ok2S = reitti.sallittuPaikka(piste.getLoppu());
                    ok2=true;
                }
        }
        // Molemmat ovat => false
        if (ok1&&ok2){
            return false;
        }
        // Vain toinen on, onko vielä salllittu paikka
        if (ok1 || ok2){
            return ok1S || ok2S;
        }
        return ok1 || ok2;
    }

    /**
     * Tarkastaa ovatko molemmat pisteet sallituissa paikoissa eri reiteillä
     * 
     * @param piste etsittävä pätkä reittiä
     * @return      ovatko sallituissa paikoissa eri reiteillä
     */
    private boolean ovatkoMolemmatEriReiteilläSallituissaPaikoissa(Piste piste) {
        boolean ok1=false;
        boolean ok2=false;
        boolean ok1S=false;
        boolean ok2S=false;
        int reitti1=-1;
        int reitti2=-1;
        for (int i=0; i<reitit.size; i++){
            CWLista reitti = reitit.get(i);
                if (reitti.onkoReitilla(piste.getAlku())){
                    ok1S = reitti.sallittuPaikka(piste.getAlku());
                    ok1=true;
                    reitti1=i;
                }
                if (reitti.onkoReitilla(piste.getLoppu())){
                    ok2S = reitti.sallittuPaikka(piste.getLoppu());
                    ok2=true;
                    reitti2=i;
                }
        }
        // Molemmat ovat, sallitut paikat, eri reiteillä => true
        if (ok1&&ok2){
            if (ok1S&&ok2S){
                if (reitti1 != reitti2){
                    return true;
                }
            }
        }
        // Muuten false
        return false;
        
    }

    /**
     * Lisätään pätkä reittiä reitille
     * 
     * @param piste lisättävä pätkä
     */
    private void lisääReitille(Piste piste) {
        CWLista reitti;
        boolean jatka=true;
        for (int i = 0; i < reitit.size; i++){
            reitti = reitit.get(i);
            if (reitti.onkoReitilla(piste.getAlku())){
                if (reitti.sallittuPaikka(piste.getAlku())){
                    if (reitti.onkoAlussa(piste.getAlku())){
                        reitti.add(piste.getLoppu(), false); // on alussa
                    } else {
                        reitti.add(piste.getLoppu(), true);
                    }
                    reitit.remove(i);
                    reitit.add(reitti);
                    jatka=false;
                    i=reitit.size;
                }
            }
            if(jatka){
                if (reitti.onkoReitilla(piste.getLoppu())){
                    if (reitti.sallittuPaikka(piste.getLoppu())){
                        if (reitti.onkoAlussa(piste.getLoppu())){
                            reitti.add(piste.getAlku(), false);
                        } else {
                            reitti.add(piste.getAlku(), true);
                        }
                        reitit.remove(i);
                        reitit.add(reitti);
                        i=reitit.size;
                    }
                }
            }
        }        
    }

    /**
     * Yhdistää kaksi reittiä toisiinsa
     * 
     * @param piste reittipätkä, joka yhdistää kaksi erillistä reittiä toisiinsa
     */
    private void mergeReitit(Piste piste) {
        CWLista reitti;
        int id1=haeReittiId(piste.getAlku());
        int id2=haeReittiId(piste.getLoppu());
        reitti = new CWLista(reitit.get(id1), reitit.get(id2));
        reitit.remove(id1);
        reitit.remove(id2);
        reitit.add(reitti);
    }
    
    private int haeReittiId(int pisteId){
        for (int i = 0; i < reitit.size; i++){
            CWLista reitti = reitit.get(i);
            if (reitti.onkoReitilla(pisteId)){
                if (reitti.sallittuPaikka(pisteId)){
                    return i;
                }
            }
        }
        return -1;
    }

    private void tulostaReitti() {
        if (reitit.size != 1){
            System.out.println("Reititys ei mahdollinen");
        } else {
            CWLista reitti = reitit.get(0);
            double pituus = 0;
            int ed = 0;
            for (int i = 0; i < reitti.reitti.length; i++){
                if (ed != reitti.reitti[i]){
                    pituus += matkat[ed][reitti.reitti[i]];
                } else {
                    pituus += 0;
                }
                ed=reitti.reitti[i];
                System.out.print(lista.get(reitti.reitti[i]).getNimi());
                if (i < reitti.reitti.length-1){
                    System.out.print(" -> ");
                }


            }
            System.out.println("");
            System.out.println("CW: Pituus: " + pituus);
        }
    }

    private void lisääLähtöPaikka() {
        CWLista eka = reitit.get(0);
        eka.add(0, true);
        eka.add(0, false);
        reitit.remove(0);
        reitit.add(eka);
    }
}
