/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.util.List;
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
public class GeoCodeTest {
    
    public GeoCodeTest() {
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
     * Test of distance method, of class GeoCode.
     */
    @Test
    public void testDistance() {
        ReittiPiste point_a = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste point_b = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        
        double expResult = 2.0861046134477608;
        double result = GeoCode.distance(point_a, point_b);
        assertEquals(expResult, result, 0.0);
    }
}
