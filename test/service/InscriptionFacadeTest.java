/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.AnneeUniversitaire;
import bean.Candidat;
import bean.Filiere;
import bean.Inscription;
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
public class InscriptionFacadeTest {
    
    public InscriptionFacadeTest() {
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
     * Test of testerEtatInscription method, of class InscriptionFacade.
     */
    @Test
    public void testTesterEtatInscription() {
        System.out.println("testerEtatInscription");
        Inscription inscription = new Inscription();
        InscriptionFacade instance = new InscriptionFacade();
        instance.testerEtatInscription(inscription);
        
    }


    
}
