/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkortelainen
 */
public class ReittiReaderTest {
    static TaulukkoLista<ReittiPiste> lista = new TaulukkoLista<ReittiPiste>();
    public ReittiReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste kolmas = new ReittiPiste(60.233345, 25.019796, "Ketokiventie"); // Ketokiventie
        ReittiPiste neljäs = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
        ReittiPiste viides = new ReittiPiste(60.271791, 24.806442, "Sarkatie");
        ReittiPiste kuudes = new ReittiPiste(60.130991, 24.514039, "LM Ericsson");
        ReittiPiste seiska = new ReittiPiste(60.31271, 25.539694, "Neste");
        ReittiPiste kasi = new ReittiPiste(60.866459, 26.704373, "Kouvola VR");
        ReittiPiste ysi = new ReittiPiste(59.81955,22.944968, "Hanko Satama");
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
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lue method, of class ReittiReader.
     */
    @Test
    public void testLue() {
        ReittiReader instance = new ReittiReader("paikat.txt");
        TaulukkoLista expResult = lista;
        TaulukkoLista result = instance.lue();
        assertEquals(expResult.size(), result.size());
        
    }
}
