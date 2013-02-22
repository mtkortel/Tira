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
public class CWListaTest {
    
    public CWListaTest() {
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
     * Test of add method, of class CWLista.
     */
    @Test
    public void testAdd() {
        int piste = 0;
        boolean append = false;
        CWLista instance = new CWLista(1,2);
        instance.add(piste, append);
        assertEquals(piste, instance.reitti[0]);
    }

    /**
     * Test of onkoReitilla method, of class CWLista.
     */
    @Test
    public void testOnkoReitilla() {
        int piste = 0;
        CWLista instance = new CWLista(1,2);
        boolean expResult = false;
        boolean result = instance.onkoReitilla(piste);
        assertEquals(expResult, result);
    }

    /**
     * Test of ovatkoReitilla method, of class CWLista.
     */
    @Test
    public void testOvatkoReitilla() {
        int piste1 = 0;
        int piste2 = 1;
        CWLista instance = new CWLista(2,3);
        boolean expResult = false;
        boolean result = instance.ovatkoReitilla(piste1, piste2);
        assertEquals(expResult, result);
    }

    /**
     * Test of sallittuPaikka method, of class CWLista.
     */
    @Test
    public void testSallittuPaikka() {
        int piste = 0;
        CWLista instance = new CWLista(1,2);
        boolean expResult = false;
        boolean result = instance.sallittuPaikka(piste);
        assertEquals(expResult, result);
    }

    /**
     * Test of onkoAlussa method, of class CWLista.
     */
    @Test
    public void testOnkoAlussa() {
        int alku = 0;
        CWLista instance = new CWLista(1,2);
        boolean expResult = false;
        boolean result = instance.onkoAlussa(alku);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CWLista.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CWLista instance = new CWLista(1,2);
        CWLista instance2 = new CWLista(1,2);
        String result = instance.toString();
        assertEquals(instance2.toString(), result);
    }
}
