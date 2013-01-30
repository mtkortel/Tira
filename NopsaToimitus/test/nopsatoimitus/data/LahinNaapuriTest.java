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
public class LahinNaapuriTest {
    
    public LahinNaapuriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of etsiLyhinReitti method, of class LahinNaapuri.
     */
    @Test
    public void testEtsiLyhinReitti() {
        TaulukkoLista<ReittiPiste> lista = new TaulukkoLista<>();
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        lähtö.setVisit(true);
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste neli = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
        lista.add(eka);
        lista.add(toka);
        lista.add(neli);
        double[][] matkat = new double[lista.size][lista.size];
        matkat[0][0] = 1.0;
        matkat[0][1] = 1.0;
        matkat[0][2] = 0.0;
        matkat[1][0] = 0.0;
        matkat[1][1] = 0.0;
        matkat[1][2] = 0.0;
        matkat[2][0] = 0.0;
        matkat[2][1] = 0.0;
        matkat[2][2] = 0.0;
        LahinNaapuri instance = new LahinNaapuri(lista, matkat);
        Reitti expResult = new Reitti(3);
        expResult.setSeuraavaPiste(eka, 0);
        expResult.setSeuraavaPiste(neli, 0);
        expResult.setSeuraavaPiste(toka, 0);
        Reitti result = instance.etsiLyhinReitti();
        assertEquals(expResult.toString(), result.toString());
    }

}
