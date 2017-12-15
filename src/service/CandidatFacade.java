/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Candidat;
import bean.Inscription;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import util.DateUtil;

/**
 *
 * @author Ghassan
 */
public class CandidatFacade extends AbstractFacade<Candidat>{
    
    public CandidatFacade() {
        super(Candidat.class);
    }
    
    public List<Candidat> chercherListCandidat(List<Inscription> inscriptions){
        List<Candidat> candidats = new ArrayList();
        for (int i = 0; i < inscriptions.size(); i++) {
            Inscription inscription = inscriptions.get(i);
            candidats.add(inscription.getCandidat());
        }
        return candidats;
    }
    
    
    
}
   
