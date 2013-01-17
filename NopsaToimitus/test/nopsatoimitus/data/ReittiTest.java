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
public class ReittiTest {
    
    ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
    ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
    ReittiPiste kolmas = new ReittiPiste(60.233345, 25.019796, "Ketokiventie"); // Ketokiventie
    ReittiPiste neljäs = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
    
    public ReittiTest() {
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
     * Test of setSeuraavaPiste method, of class Reitti.
     */
    @Test
    public void testSetSeuraavaPiste() {
        Reitti instance = new Reitti(1);
        boolean expResult = true;
        boolean result = instance.setSeuraavaPiste(eka);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPituus method, of class Reitti.
     */
    @Test
    public void testGetPituus() {
        Reitti instance = new Reitti(2);
        instance.setSeuraavaPiste(eka);
        instance.setSeuraavaPiste(toka);
        double expResult = 2.0861046134477608;
        double result = instance.getPituus();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getReitti method, of class Reitti.
     */
    @Test
    public void testGetReitti() {
        Reitti instance = new Reitti(2);
        instance.setSeuraavaPiste(eka);
        instance.setSeuraavaPiste(toka);
        Reitti tmp = new Reitti(2);
        tmp.setSeuraavaPiste(eka);
        tmp.setSeuraavaPiste(toka);
        ReittiPiste[] expResult = tmp.getReitti();
        ReittiPiste[] result = instance.getReitti();
        assertArrayEquals(expResult, result);
    }
}
