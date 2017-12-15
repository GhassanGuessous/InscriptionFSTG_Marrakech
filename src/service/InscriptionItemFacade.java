/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Inscription;
import bean.InscriptionItem;
import bean.PieceJointe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class InscriptionItemFacade extends AbstractFacade<InscriptionItem>{
    
    public InscriptionItemFacade() {
        super(InscriptionItem.class);
    }
    
    
    public int mainService(InscriptionItem inscriptionItem){
        Inscription inscription = inscriptionItem.getInscription();
        PieceJointe pieceJointe = inscriptionItem.getPieceJointe();
        //verifier si le candidat a deja deposé cet element
        Object[] res2 = findInscriptionItemByInscriptionAndPieceJointe(inscription, pieceJointe);
        InscriptionItem inscriptionItemBd = (InscriptionItem) res2[1];
        int j = (int) res2[0];
        int retour = 0;
        if (j < 0) { //ila deja deposé la piece avec le nombre demandé
            return -1;
            //alertExistInscriptionItem(actionEvent);
        } else if (j == 2) { //il manque encore des copies
            inscriptionItemBd.setNombrePieceFournis(inscriptionItemBd.getNombrePieceFournis()
                    + inscriptionItem.getNombrePieceFournis());
            comparaisonNbrFourniNbrAFournir(inscriptionItemBd);//on compare le nombre fourni avec celui demandé
            edit(inscriptionItemBd); // on modifie le nombre au niveau de la BD
            retour = 2;
        } else if (j == 1) { // la piece n'est pas encore deposée
            comparaisonNbrFourniNbrAFournir(inscriptionItem);//on compare le nombre fourni avec celui demandé
            create(inscriptionItem);
            retour = 1;
        }
        return retour;
    }
    
    private void comparaisonNbrFourniNbrAFournir(InscriptionItem inscriptionItem) {
        if (inscriptionItem.getPieceJointe().getNombre() > inscriptionItem.getNombrePieceFournis()) {
            inscriptionItem.setEtat(0);
        } else if(inscriptionItem.getPieceJointe().getNombre() <= inscriptionItem.getNombrePieceFournis()){
            inscriptionItem.setNombrePieceFournis(inscriptionItem.getPieceJointe().getNombre());
            inscriptionItem.setEtat(1);
        }
    }
    
    public Object[] findInscriptionItemByInscriptionAndPieceJointe(Inscription inscription, PieceJointe pieceJointe){
        String requete = "SELECT ii FROM InscriptionItem ii WHERE ii.inscription.id = " +inscription.getId() 
                + " AND ii.pieceJointe.id = " + pieceJointe.getId();
        List<InscriptionItem> inscriptionItems = getEntityManager().createQuery(requete).getResultList();
        if(inscriptionItems.isEmpty()){
            return new Object[]{1,null}; //l'element n'existe pas
        }
        else{
            InscriptionItem inscriptionItem = inscriptionItems.get(0);
            if(inscriptionItem.getEtat() == 0){
                return new Object[]{2,inscriptionItems.get(0)}; // il manque quelques copies
            }
            return new Object[]{-1,inscriptionItems.get(0)}; // complete
        }
    }

    public List<InscriptionItem> findInscriptionItemsByInscription(Inscription inscription){
        String requete = "SELECT ii FROM InscriptionItem ii WHERE ii.inscription.id = " + inscription.getId();
        List<InscriptionItem> inscriptionItems = getEntityManager().createQuery(requete).getResultList();
        if(!inscriptionItems.isEmpty()){
            return inscriptionItems;
        }else{
            return null;
        }
    }
    
    public InscriptionItem findInscriptionItemByPieceJointe(List<InscriptionItem> inscriptionItems, PieceJointe pieceJointe){
        for (int i = 0; i < inscriptionItems.size(); i++) {
            InscriptionItem ii = inscriptionItems.get(i);
            if(ii.getPieceJointe().getId() == pieceJointe.getId()){
                return ii;
            }
        }
        return null;
    }
    
    public List<InscriptionItem> siZouaniLigalha(List<PieceJointe> pieceJointes, List<InscriptionItem> inscriptionItems){
        List<InscriptionItem> items = new ArrayList() ;
        InscriptionItem item;
        for (int i = 0; i < pieceJointes.size(); i++) {
            PieceJointe pj = pieceJointes.get(i);
            item = findInscriptionItemByPieceJointe(inscriptionItems, pj);
            if(item != null){
                items.add(item);
            }else{
                items.add(new InscriptionItem(0));
            }
        }
        return items;
    }
    
}

