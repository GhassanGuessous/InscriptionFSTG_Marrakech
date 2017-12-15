/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.AnneeUniversitaire;
import bean.Candidat;
import bean.Filiere;
import bean.Inscription;
import bean.InscriptionItem;
import bean.PieceJointe;
import helper.InscriptionItemFxHelper;
import helper.PieceJointeFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.AnneeUniversitaireFacade;
import service.CandidatFacade;
import service.InscriptionFacade;
import service.InscriptionItemFacade;
import service.PieceJointeFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class VerificationController implements Initializable {
    
    private Date dateActuelle = new Date();
    
    @FXML
    private TextField cne;
    @FXML
    private TextField anneeU;
    @FXML
    private TableView piecesJointesTableView = new TableView();
    @FXML
    private TableView inscriptionItemsTableView = new TableView();
   
    PieceJointeFacade pieceJointeFacade =  new PieceJointeFacade();
    CandidatFacade candidatFacade = new CandidatFacade();
    InscriptionFacade inscriptionFacade = new InscriptionFacade();
    InscriptionItemFacade inscriptionItemFacade = new InscriptionItemFacade();
    AnneeUniversitaireFacade anneeUniversitaireFacade = new AnneeUniversitaireFacade();
    
    private PieceJointeFxHelper pieceJointeFxHelper;
    private InscriptionItemFxHelper inscriptionItemFxHelper;
    
    private void initHelper(){
        pieceJointeFxHelper = new PieceJointeFxHelper(piecesJointesTableView);
        inscriptionItemFxHelper = new InscriptionItemFxHelper(inscriptionItemsTableView);
    }
    
    @FXML
    public void verifierInscription(ActionEvent actionEvent){
        AnneeUniversitaire anneeUniversitaire = anneeUniversitaireFacade.find(anneeU.getText());
        if(!cne.getText().equals("")){
            Candidat candidat = candidatFacade.find(cne.getText());
            if (candidat != null) {
                Inscription inscription = inscriptionFacade.findInscriptionByCandidatAndAnnee(candidat, anneeUniversitaire);
                if (inscription != null) {
                    Filiere filiere = inscription.getFiliere();
                    List<PieceJointe> pieceJointes = pieceJointeFacade.findPieceJointeByFilierePJF(filiere.getNom());
                    List<InscriptionItem> inscriptionItems = inscriptionItemFacade.findInscriptionItemsByInscription(inscription);
                    pieceJointeFxHelper.setList(pieceJointes);
                    if (inscriptionItems != null) {
                        List<InscriptionItem> itemsVirtuel = inscriptionItemFacade.siZouaniLigalha(pieceJointes, inscriptionItems);
                        inscriptionItemFxHelper.setList(itemsVirtuel);
                    } else {
                        alertAucunePJ(actionEvent);
                    }
                } else {
                    alertInscription(actionEvent);
                }
            } else {
                alertInscription(actionEvent);
            }
        }else{
            alertChampVide(actionEvent);
        }
        
        
    }
    
    @FXML
    public void clickDeposerPiecesFromVerification(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "InscriptionItem.fxml", this.getClass());
    }
    
    @FXML
    public void versCandidatureFromVerification(ActionEvent actionEvent) throws IOException{
        AnneeUniversitaire anneeUniversitaire = anneeUniversitaireFacade.find(anneeU.getText());
        Date d1 = anneeUniversitaire.getDateDebut();
        Date d2 = anneeUniversitaire.getDateFin();
        if(d1.getTime() <= dateActuelle.getTime() && d2.getTime() >= dateActuelle.getTime()){
            clickSInscrireFromVerification(actionEvent);
        }else{
            alertDateInscriptionFromVerification(actionEvent);
        }
        
    }
    
    public void clickSInscrireFromVerification(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Candidature.fxml", this.getClass());
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initHelper();
//        Candidat candidat = (Candidat) Session.getAttribut("candidat");
//        cne.setText(candidat.getId());
    }
    
    private void alertInscription(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Inscription !");
        alert.setContentText("Vous n'etes pas inscrit !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    public void alertDateInscriptionFromVerification(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Date d'inscription !");
        alert.setContentText("La période d'inscription est terminée !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    public void alertAucunePJ(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Piece Jointe !");
        alert.setContentText("Vous n'avez déposer aucune piece jointe !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertChampVide(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Champs !");
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    public TextField getCne() {
        return cne;
    }

    public void setCne(TextField cne) {
        this.cne = cne;
    }

    public TextField getAnneeU() {
        return anneeU;
    }

    public void setAnneeU(TextField anneeU) {
        this.anneeU = anneeU;
    }

    public TableView getPiecesJointesTableView() {
        return piecesJointesTableView;
    }

    public void setPiecesJointesTableView(TableView piecesJointesTableView) {
        this.piecesJointesTableView = piecesJointesTableView;
    }

    public TableView getInscriptionItemsTableView() {
        return inscriptionItemsTableView;
    }

    public void setInscriptionItemsTableView(TableView inscriptionItemsTableView) {
        this.inscriptionItemsTableView = inscriptionItemsTableView;
    }

    public PieceJointeFxHelper getPieceJointeFxHelper() {
        return pieceJointeFxHelper;
    }

    public void setPieceJointeFxHelper(PieceJointeFxHelper pieceJointeFxHelper) {
        this.pieceJointeFxHelper = pieceJointeFxHelper;
    }

    public InscriptionItemFxHelper getInscriptionItemFxHelper() {
        return inscriptionItemFxHelper;
    }

    public void setInscriptionItemFxHelper(InscriptionItemFxHelper inscriptionItemFxHelper) {
        this.inscriptionItemFxHelper = inscriptionItemFxHelper;
    }
    
    
}
