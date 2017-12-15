/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class PieceJointeFacadeTest {
    
    public PieceJointeFacadeTest() {
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
     * Test of findPieceJointeByFilierePJF method, of class PieceJointeFacade.
     */
    @Test
    public void testFindPieceJointeByFilierePJF() {
        System.out.println("findPieceJointeByFilierePJF");
        String nom = "MIPC";
        PieceJointeFacade instance = new PieceJointeFacade();
        List<PieceJointe> result = instance.findPieceJointeByFilierePJF(nom);
        System.out.println(result);
    }
    
}
