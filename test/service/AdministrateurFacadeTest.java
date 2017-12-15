/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Administrateur;
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
public class AdministrateurFacadeTest {
    
    public AdministrateurFacadeTest() {
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
     * Test of addAdmin method, of class AdministrateurFacade.
     */
    @Test
    public void testAddAdmin() {
        System.out.println("addAdmin");
        Administrateur administrateur = new Administrateur("gh", "312", "ghassan", "guess");
        AdministrateurFacade instance = new AdministrateurFacade();
        instance.addAdmin(administrateur);
    }
    
}
