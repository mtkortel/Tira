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
public class ReittiPisteTest {
    ReittiPiste instance = new ReittiPiste(60.197094, 24.947634, "Lähtö");
    
    public ReittiPisteTest() {
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
     * Test of getLon method, of class ReittiPiste.
     */
    @Test
    public void testGetLon() {
        double expResult = 24.947634;
        double result = instance.getLon();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLon method, of class ReittiPiste.
     */
    @Test
    public void testSetLon() {
        double lon = 0.0;
        instance.setLon(lon);
        double result = instance.getLon();
        assertEquals(lon, result, 0.0);
    }

    /**
     * Test of getLat method, of class ReittiPiste.
     */
    @Test
    public void testGetLat() {
        double expResult = 60.197094;
        double result = instance.getLat();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLat method, of class ReittiPiste.
     */
    @Test
    public void testSetLat() {
        double lat = 0.0;
        instance.setLat(lat);
        double result = instance.getLat();
        assertEquals(lat, result, 0.0);
    }

    /**
     * Test of isVisit method, of class ReittiPiste.
     */
    @Test
    public void testIsVisit() {
        boolean expResult = false;
        boolean result = instance.isVisit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisit method, of class ReittiPiste.
     */
    @Test
    public void testSetVisit() {
        boolean visit = true;
        boolean expResult = true;
        instance.setVisit(visit);
        boolean result = instance.isVisit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class ReittiPiste.
     */
    @Test
    public void testGetWeight() {
        double expResult = 0.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setWeight method, of class ReittiPiste.
     */
    @Test
    public void testSetWeight() {
        double weight = 0.0;
        instance.setWeight(weight);
    }

    /**
     * Test of getNimi method, of class ReittiPiste.
     */
    @Test
    public void testGetNimi() {
        String expResult = "Lähtö";
        String result = instance.getNimi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNimi method, of class ReittiPiste.
     */
    @Test
    public void testSetNimi() {
        String nimi = "Maali";
        instance.setNimi(nimi);
        String result = instance.getNimi();
        assertEquals(nimi, result);
    }
}
