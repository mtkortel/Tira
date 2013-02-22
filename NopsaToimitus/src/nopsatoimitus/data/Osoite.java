/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Oma osoitepuu 
 * 
 * @author Marko Kortelainen
 */
public class Osoite {
   
    private TaulukkoLista<Osoite> lapset = new TaulukkoLista<Osoite>();
    private Osoite isapuu = null;
    private double etaisyys = 0;
    ReittiPiste rp = null;
    
    /**
     * Konstruktori, jossa asetetaan reitin edellinen osoite, sekä matka sinne
     * 
     * @param edellinen Edellinen osoite
     * @param matka     Matka
     */
    public Osoite(Osoite edellinen, double matka, ReittiPiste rp){
        isapuu = edellinen;
        etaisyys = matka;
        this.rp = rp;
    }
    
    /**
     * Konstruktori, jolla asetetaan Reittipiste
     * 
     * @param rp    Reittipiste
     */
    public Osoite(ReittiPiste rp){
        this.rp = rp;
    }
    
    /**
     * Uuden osoiteen lisäys
     * @param uusi  uusi osoite
     */
    public void lisaa(Osoite uusi){
        lapset.add(uusi);
    }
    
    /**
     * Palauttaa osoitteen
     * @return 
     */
    public Osoite get(){
        return this;
    }
}
