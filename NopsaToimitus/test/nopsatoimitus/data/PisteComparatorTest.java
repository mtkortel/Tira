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
public class PisteComparatorTest {
    
    public PisteComparatorTest() {
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
     * Test of compare method, of class PisteComparator.
     */
    @Test
    public void testCompare() {
        Piste o1 = new Piste(1, 2, 1.2);
        Piste o2 = new Piste(2, 3, 3.4);
        PisteComparator instance = new PisteComparator();
        int expResult = 1;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
    }
}
