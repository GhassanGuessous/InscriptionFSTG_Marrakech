/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.PieceJointe;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class PieceJointeFacade extends AbstractFacade<PieceJointe>{
    
    public PieceJointeFacade() {
        super(PieceJointe.class);
    }
    
    public List<PieceJointe> findPieceJointeByFilierePJF(String nom){
        return getEntityManager().createQuery("SELECT pj FROM PieceJointe pj WHERE pj.filiere.nom = '" + nom + "'").getResultList();
    }
    
}
