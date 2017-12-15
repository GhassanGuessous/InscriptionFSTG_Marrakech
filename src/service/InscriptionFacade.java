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
import bean.InscriptionItem;
import bean.PieceJointe;
import java.util.ArrayList;
import java.util.List;
import util.DateUtil;

/**
 *
 * @author Ghassan
 */
public class InscriptionFacade extends AbstractFacade<Inscription> {

    InscriptionItemFacade inscriptionItemFacade = new InscriptionItemFacade();
    CandidatFacade candidatFacade = new CandidatFacade();
    FiliereFacade filiereFacade = new FiliereFacade();
    PieceJointeFacade pieceJointeFacade = new PieceJointeFacade();

    public InscriptionFacade() {
        super(Inscription.class);
    }

    public Inscription findInscriptionByCandidatAndAnnee(Candidat candidat, AnneeUniversitaire anneeUniversitaire) {
        String requete = "SELECT i FROM Inscription i WHERE i.candidat.id = '" + candidat.getId() + "' AND i.anneeUniversitaire.annee = '" + anneeUniversitaire.getAnnee() + "'";
        List<Inscription> inscriptions = getEntityManager().createQuery(requete).getResultList();
        if (inscriptions.size() != 0) {
            return inscriptions.get(0);
        } else {
            return null;
        }
    }

    //chercher les inscription d'une filiere dans une annee donnee
    public List<Inscription> findInscriptionsByFiliereAndAnnee(Filiere filiere, AnneeUniversitaire anneeUniversitaire) {
        String requete = "SELECT i FROM Inscription i WHERE i.filiere.id = '" + filiere.getId() + "' AND i.anneeUniversitaire.annee = '" + anneeUniversitaire.getAnnee() + "'";
        List<Inscription> inscriptions = getEntityManager().createQuery(requete).getResultList();
        return inscriptions;
    }

    //modifier l'etat de l'inscription
    public void testerEtatInscription(Inscription inscription) {
        Filiere filiere = inscription.getFiliere();
        List<PieceJointe> pieceJointes = pieceJointeFacade.findPieceJointeByFilierePJF(filiere.getNom());
        List<InscriptionItem> inscriptionItems = new ArrayList<InscriptionItem>();
        inscriptionItems = inscriptionItemFacade.findInscriptionItemsByInscription(inscription);
        if (inscriptionItems != null) {
            inscription.setEtat(1);//incmplete
            edit(inscription);
            if (pieceJointes.size() == inscriptionItems.size()) {
                int cmp = 0;
                for (int i = 0; i < inscriptionItems.size(); i++) {
                    InscriptionItem inscriptionItem = inscriptionItems.get(i);
                    if (inscriptionItem.getEtat() == 1) {
                        cmp++;
                    }
                }
                if (cmp == inscriptionItems.size()) {
                    inscription.setEtat(2);//complete
                    edit(inscription);
                }
            }
        }

    }

    public List<Inscription> chercherListInscriptions(Filiere filiere, Object etat, AnneeUniversitaire anneeUniversitaire) {
        String requete = "SELECT i FROM Inscription i WHERE 1=1";
        requete += DateUtil.addConstraint("i", "filiere.id", "=", filiere.getId());
        requete += DateUtil.addConstraint("i", "etat", "=", etat);
        requete += DateUtil.addConstraint("i", "anneeUniversitaire.annee", "=", anneeUniversitaire.getAnnee());
        return getEntityManager().createQuery(requete).getResultList();
    }

}
