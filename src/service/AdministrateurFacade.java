/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Administrateur;
import util.HashageUtil;

/**
 *
 * @author Ghassan
 */
public class AdministrateurFacade extends AbstractFacade<Administrateur>{
    
    public AdministrateurFacade() {
        super(Administrateur.class);
    }
    
    public void addAdmin(Administrateur administrateur){
        create(administrateur);
    }
    
    public Object[] findAdmin(Administrateur administrateur){
        Administrateur administrateur1 = find(administrateur.getLogin());
        if(administrateur1 == null){
            return new Object[]{-1, null};
        }else if(!administrateur1.getPassword().equals(HashageUtil.sha256(administrateur.getPassword()))){
            return new Object[]{-2, null};
        }else{
            return new Object[]{1, administrateur1};
        }
    }
    
    
}
