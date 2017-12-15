/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Inscription;
import bean.InscriptionItem;
import bean.PieceJointe;
import java.util.List;
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
public class InscriptionItemFacadeTest {
    
    public InscriptionItemFacadeTest() {
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
     * Test of findInscriptionItemsByInscription method, of class InscriptionItemFacade.
     */
    @Test
    public void testFindInscriptionItemsByInscription() {
        System.out.println("findInscriptionItemsByInscription");
        Inscription inscription = new Inscription(9L);
        InscriptionItemFacade instance = new InscriptionItemFacade();
        List<InscriptionItem> result = instance.findInscriptionItemsByInscription(inscription);
        System.out.println("ha " + result);
    }

    
    
    
}
