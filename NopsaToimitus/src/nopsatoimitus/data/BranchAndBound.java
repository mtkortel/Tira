/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/**
 * Breach And Bound algoritmin toteutus
 * 
 * @author Marko Kortelainen
 */
public class BranchAndBound {
    ///TODO: Toteutus
    double[][] matkat;
    TaulukkoLista paikat;
    Osoite osoitteet;
    
    public BranchAndBound(double[][] matkat, TaulukkoLista<ReittiPiste> paikat){
        this.matkat = matkat;
        this.paikat = paikat;
        ReittiPiste rp = paikat.get(0); // Koti
        Osoite osoite = new Osoite(rp);
        
        for (int i = 0; i < matkat.length; i++){
            for (int j = 0; j < matkat[i].length; j++){
                if (i != j){ // Ei mennä kotiin....
                    rp = paikat.get(i);
                    double matka = matkat[i][j];
                    Osoite tmp = new Osoite(osoite, matka, rp);
                    osoite.lisaa(tmp);
                }
            }
        }
        
    }
}
