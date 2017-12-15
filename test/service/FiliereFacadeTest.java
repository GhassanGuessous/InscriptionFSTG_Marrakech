/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Filiere;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ghassan
 */
public class FiliereFacadeTest {
    
    public FiliereFacadeTest() {
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
     * Test of findFiliereByNom method, of class FiliereFacade.
     */
    @Test
    public void testFindFiliereByNom() {
        System.out.println("findFiliereByNom");
        String nom = "MIP";
        FiliereFacade instance = new FiliereFacade();
        Filiere result = instance.findFiliereByNom(nom);
        System.out.println(result);
    }
    
}
