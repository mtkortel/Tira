/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkortelainen
 */
public class ReittiReader {
    private final String tiedosto;
    private boolean isAddress = false;
    
    public ReittiReader(String tiedosto){
        this.tiedosto = tiedosto;
    }
    
    public TaulukkoLista<ReittiPiste> lue(){
        TaulukkoLista<ReittiPiste> pisteet = new TaulukkoLista<>();
        try {
            
            //File t = new File(".").getCanonicalFile();
            //System.out.println(t.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
            String rivi;
            int row = 0;
            while ((rivi = reader.readLine()) != null){
                ReittiPiste piste;
                if (row==0){
                    if (rivi.equalsIgnoreCase("ADDR")){
                        isAddress=true;
                    } else {
                        isAddress=false;
                    }
                    row++;
                } else { // GPS pisteet
                    String[] jako = rivi.split(",");
                    if (jako.length > 2){
                        piste = new ReittiPiste(Double.parseDouble(jako[0].trim()), Double.parseDouble(jako[1].trim()), jako[2].trim());
                    } else {
                        piste = new ReittiPiste(Double.parseDouble(jako[0].trim()), Double.parseDouble(jako[1].trim()), jako[0] + "-" + jako[1]);
                    }
                    pisteet.add(piste);
                }
            }
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(ReittiReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return pisteet;
    }
}
