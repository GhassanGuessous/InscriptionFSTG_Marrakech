/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Filiere;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class FiliereFacade extends AbstractFacade<Filiere>{
    
    public FiliereFacade() {
        super(Filiere.class);
    }
    
    public Filiere findFiliereByNom(String nom){
        List<Filiere> filieres = getEntityManager().createQuery("SELECT f FROM Filiere f WHERE f.nom = '" + nom + "'").getResultList();
        return filieres.get(0);
    }
}
