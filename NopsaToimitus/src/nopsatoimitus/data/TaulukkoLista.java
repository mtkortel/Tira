/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

/** Oma ArrayList-luokka
 *
 * @author Marko Kortelainen
 */
public class TaulukkoLista<T> implements java.io.Serializable{
    int size = 0;
    private T[] tallessa;
    
    public TaulukkoLista(T[] array){
        tallessa = array;
    }

    public TaulukkoLista() {
        size = 0;
        tallessa = (T[]) new Object[20];
    }
    
    public T get(int index) {
        return tallessa[index];
    }

    public int size() {
        return size;
    }
    
    public boolean add(T obj){
        try{
            if (tallessa.length == size){
                T[] tmp = (T[]) new Object[tallessa.length*2];
                for (int i = 0; i < tallessa.length; i++){
                    tmp[i] = tallessa[i];
                }
                tallessa = tmp;
            }
            tallessa[size] = obj;
            size++;
            
        } catch (Exception e){
            return false;
        }
        return true;
    }
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
}
