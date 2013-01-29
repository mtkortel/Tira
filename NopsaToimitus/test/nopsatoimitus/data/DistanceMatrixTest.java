/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mkortelainen
 */
public class DistanceMatrixTest {
    
    public DistanceMatrixTest() {
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
     * Test of getMatrix method, of class DistanceMatrix.
     */
    @Test
    public void testGetMatrix() {
        TaulukkoLista<ReittiPiste> lista = new TaulukkoLista<>();
        ReittiPiste lähtö = new ReittiPiste(60.197094, 24.947634, "Lähtö");
        lähtö.setVisit(true);
        ReittiPiste eka = new ReittiPiste(60.205273, 24.963062, "HY"); //HY
        ReittiPiste toka = new ReittiPiste(60.222541, 24.948299, "Perkiöntie"); // Perkiöntie
        ReittiPiste neli = new ReittiPiste(60.212128, 24.812322, "Linnoitustie");
        lista.add(eka);
        lista.add(toka);
        lista.add(neli);
        
        DistanceMatrix instance = new DistanceMatrix();
        double[][] expResult = new double[lista.size][lista.size];
        expResult[0][0] = 1.0;
        expResult[0][1] = 1.0;
        expResult[0][2] = 0.0;
        expResult[1][0] = 0.0;
        expResult[1][1] = 0.0;
        expResult[1][2] = 0.0;
        expResult[2][0] = 0.0;
        expResult[2][1] = 0.0;
        expResult[2][2] = 0.0;
        double[][] result = instance.getMatrix(lista);
        
        assertArrayEquals(expResult, result);
    }
}
