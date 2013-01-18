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
public class XMLParserTest {
    
    public XMLParserTest() {
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
     * Test of getDistance method, of class XMLParser.
     */
    @Test
    public void testGetDistance() {
        ReittiPiste origin = new ReittiPiste(60.197279, 24.946214, "Elimäenkatu 28, Helsinki");
        ReittiPiste dest = new ReittiPiste(60.159865, 24.948484, "Kasarminkatu 2, Helsinki");
        
        XMLParser instance = new XMLParser();
        double expResult = 0.0;
        double koe = GeoCode.distance(origin, dest);
        System.out.println(koe);
        double result = instance.getDistance(origin, dest);
        assertEquals(expResult, result, 0.0);
    }
}
