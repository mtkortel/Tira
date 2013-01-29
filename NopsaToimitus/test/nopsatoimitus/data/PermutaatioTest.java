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
public class PermutaatioTest {
    
    public PermutaatioTest() {
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

    @Test
    public void testSomeMethod() {
        TaulukkoLista<ReittiPiste> lista = new TaulukkoLista<>();
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        lähtö.setVisit(true);
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste neli = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
        lista.add(eka);
        lista.add(toka);
        lista.add(neli);
        ReittiPiste[] test = new ReittiPiste[lista.size()-1];
        int mahd = 1;
        for (int i = 1; i <= lista.size()-1; i++){
            mahd *= i;
        }
        Permutaatio perm = new Permutaatio(test, lista.get(0), mahd);
        int result = Permutaatio.max;
        int expResult = 2;
        assertEquals(expResult, result);
    }
}
