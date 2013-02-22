/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/** Oma Geneerinen ArrayList-luokka
 *
 * @author Marko Kortelainen
 */
public class TaulukkoLista<T> implements java.io.Serializable{
    int size = 0;
    private T[] tallessa;
    
    /**
     * Konstruktori, joka saa parametrina taulukon  
     * @param array taulukko 
     */
    public TaulukkoLista(T[] array){
        tallessa = array;
        size = array.length;
    }

    /**
     * Oletuskonstruktori, joka asettaa taulukon, jossa on 20 objektia
     */
    public TaulukkoLista() {
        size = 0;
        tallessa = (T[]) new Object[20];
    }
    
    /**
     * Palauttaa halutun indeksin sisältämän objektin
     * @param index Haluttu indeksi
     * @return      Palautettava objekti
     */
    public T get(int index) {
        return tallessa[index];
    }

    /**
     * Palauttaa taulukon käytetyn ko'on
     * @return 
     */
    public int size() {
        return size;
    }
    
    /**
     * Lisäys metodi
     * @param obj   Geneerinen objekti, joka halutaan lisätä taulukkoon
     * @return      Onnistuiko lisäys vai ei
     */
    public boolean add(T obj){
        try{
            if (tallessa.length == size){
                kasvataTaulukkoa();
            }
            tallessa[size] = obj;
            size++;
        } catch (Exception e){
            return false;
        }
        return true;
    }
    
    /**
     * Objektin poisto
     * 
     * @param index poistettavan objektin indeksi
     * @return      Onnistuiko poisto vai ei
     */
    public T remove(int index){
        T removed = (T) tallessa[index];
        try{
            for (int i = index; i < tallessa.length-1; i++){
                tallessa[i] = tallessa[i+1];
            }
            tallessa[tallessa.length-1] = null;
            size--;
            
        } catch (Exception e){
            return null;
        }
        return removed;
    }
    
    /**
     * Tulostaa Taulukosta String-listauksen
     * @return  tekstiesitys taulukosta
     */
    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < size; i++){
            T obj = tallessa[i];
            if (obj instanceof ReittiPiste){
                ReittiPiste rp = (ReittiPiste) tallessa[i];
                str += rp.getNimi();
                if (i < (size-2)){
                    str += "/";
                }
            }
        }
        return str;
        
    }

    /**
     * Kasvattaa taulukon kokoa kaksinkertaiseksi
     */
    private void kasvataTaulukkoa() {
        T[] tmp = (T[]) new Object[tallessa.length*2];
        for (int i = 0; i < tallessa.length; i++){
            tmp[i] = tallessa[i];
        }
        tallessa = tmp;
    }
    
}
