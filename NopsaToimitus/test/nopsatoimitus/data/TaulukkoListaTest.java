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
public class TaulukkoListaTest {
    TaulukkoLista instance = new TaulukkoLista();
    ReittiPiste obj = new ReittiPiste(20.20, 20.50, "Jossain");
    
    public TaulukkoListaTest() {
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
     * Test of get method, of class TaulukkoLista.
     */
    @Test
    public void testGet() {
        int index = 0;
        instance.add(obj);
        Object result = instance.get(index);
        assertEquals(obj, result);
    }

    /**
     * Test of size method, of class TaulukkoLista.
     */
    @Test
    public void testSize() {
        instance.add(obj);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class TaulukkoLista.
     */
    @Test
    public void testAdd() {
        
        boolean expResult = true;
        boolean result = instance.add(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class TaulukkoLista.
     */
    @Test
    public void testRemove() {
        int index = 0;
        instance.add(obj);
        Object result = instance.remove(index);
        assertEquals(obj, result);
    }
}
