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
public class PisteTest {
    
    public PisteTest() {
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
     * Test of getAlku method, of class Piste.
     */
    @Test
    public void testGetAlku() {
        Piste instance = new Piste(1,2,1.2);
        int expResult = 1;
        int result = instance.getAlku();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAlku method, of class Piste.
     */
    @Test
    public void testSetAlku() {
        int alku = 0;
        Piste instance =  new Piste(1,2,1.2);
        instance.setAlku(alku);
        assertEquals(0, instance.getAlku());
    }

    /**
     * Test of getLoppu method, of class Piste.
     */
    @Test
    public void testGetLoppu() {
        Piste instance = new Piste(1,2,1.2);
        int expResult = 2;
        int result = instance.getLoppu();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoppu method, of class Piste.
     */
    @Test
    public void testSetLoppu() {
        int loppu = 0;
        Piste instance = new Piste(1,2,1.2);
        instance.setLoppu(loppu);
        assertEquals(0, instance.getLoppu());
    }

    /**
     * Test of getSaasto method, of class Piste.
     */
    @Test
    public void testGetSaasto() {
        Piste instance = new Piste(1,2,1.2);
        double expResult = 1.2;
        double result = instance.getSaasto();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSaasto method, of class Piste.
     */
    @Test
    public void testSetSaasto() {
        double saasto = 0.0;
        Piste instance = new Piste(1,2,1.2);
        instance.setSaasto(saasto);
        assertEquals(0.0, instance.getSaasto(), 0.0);
    }

    /**
     * Test of toString method, of class Piste.
     */
    @Test
    public void testToString() {
        Piste instance = new Piste(1,2,1.2);
        String expResult = "1 -> 2 = 1.2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
