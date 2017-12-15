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
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.AnneeUniversitaireFacade;
import service.CandidatFacade;
import service.FiliereFacade;
import service.InscriptionFacade;
import service.InscriptionItemFacade;
import service.PieceJointeFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class InscriptionItemController implements Initializable {
    
    private Date dateActuelle = new Date();

    FiliereFacade filiereFacade = new FiliereFacade();
    PieceJointeFacade pieceJointeFacade = new PieceJointeFacade();
    CandidatFacade candidatFacade = new CandidatFacade();
    InscriptionFacade inscriptionFacade = new InscriptionFacade();
    InscriptionItemFacade inscriptionItemFacade = new InscriptionItemFacade();
    AnneeUniversitaireFacade anneeUniversitaireFacade = new AnneeUniversitaireFacade();

    @FXML
    private TextField filieree;
    @FXML
    private ComboBox<PieceJointe> pieceJointeComboBox = new ComboBox<>();
    @FXML
    private TextField cne;
    @FXML
    private TextField nombreAfournir;
    @FXML
    private TextField nombreFourni;
    @FXML
    private TextField anneeU;
    
    @FXML
    public void saveInscriptionItem(ActionEvent actionEvent) {
        Object[] res1 = getParamInscriptionItem();
        int i = (int) res1[0];
        InscriptionItem inscriptionItem = (InscriptionItem) res1[1];
        if(i > 0){ //on verifie l'existance de l'inscription
            int res2 = inscriptionItemFacade.mainService(inscriptionItem);
            if(res2 < 0){
                alertExistInscriptionItem(actionEvent);
            }else{
                alertSuccess(actionEvent);
            }
            //on test l'etat de l'inscription
            inscriptionFacade.testerEtatInscription(inscriptionItem.getInscription());
        }else if(i == -3){
            alertInscription(actionEvent);
        }else if(i == -2){
            alertChampNumerique(actionEvent);
        }else if( i == -1){
            alertChampVide(actionEvent);
        }
    }
    
    @FXML
    public void actualiser(ActionEvent actionEvent){
         Inscription inscription = getParamInscription();
         if(inscription != null){
             filieree.setText(inscription.getFiliere().getNom());
             findPieceJointeByFiliere();
         }else{
             alertInscription(actionEvent);
         }
    }

    //chercher les pieces jointes associées a une filiere

    public void findPieceJointeByFiliere() {
        Filiere filiere = getParamFiliere();
        pieceJointeComboBox.setItems(FXCollections.observableArrayList(pieceJointeFacade.findPieceJointeByFilierePJF(filiere.getNom())));
        pieceJointeComboBox.getSelectionModel().select(0);
    }

    //chercher le nombre a fournir correspondant a la piece jointe selectionnée
    @FXML
    public void findNombreAFournir() {
        PieceJointe pieceJointe = getParamPieceJointe();
        if(pieceJointe != null){
            nombreAfournir.setText("" + pieceJointe.getNombre());
        }
        
    }
    
    @FXML
    public void versCandidatureFromDepot(ActionEvent actionEvent) throws IOException{
        AnneeUniversitaire anneeUniversitaire = anneeUniversitaireFacade.find(anneeU.getText());
        Date d1 = anneeUniversitaire.getDateDebut();
        Date d2 = anneeUniversitaire.getDateFin();
        if(d1.getTime() <= dateActuelle.getTime() && d2.getTime() >= dateActuelle.getTime()){
            clickSInscrireFromDepot(actionEvent);
        }else{
            alertDateInscriptionFromDepot(actionEvent);
        }
        
    }
    
    public void clickSInscrireFromDepot(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Candidature.fxml", this.getClass());
    }
    
    @FXML
    public void clickVerifierFromDepot(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Verification.fxml", this.getClass());
    }
    
    public void retour(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Ouverture.fxml", this.getClass());
    }

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }
    
    private void alertExistInscriptionItem(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exist !");
        alert.setContentText("Vous avez deja deposé cette piece jointe !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertInscription(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Inscription !");
        alert.setContentText("Vous n'etes pas inscrit !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    public void alertDateInscriptionFromDepot(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Date d'inscription !");
        alert.setContentText("La période d'inscription est terminée !");
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
    
    private void alertChampNumerique(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Nombre Fourni !");
        alert.setContentText("Veuillez saisir un entier !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void alertSuccess(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Depot terminé avec succés");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    //recuperation du candidat
    private Candidat getParamCandidat() {
        Candidat candidat = candidatFacade.find(cne.getText());
        if(candidat != null){
            //Session.setAttribut(candidat, "candidat");
            return candidat;
        }else{
            return null;
        }
    }

    //recuperation de l'inscription
    private Inscription getParamInscription() {
        AnneeUniversitaire anneeUniversitaire = anneeUniversitaireFacade.find(anneeU.getText());
        Candidat candidat = getParamCandidat();
        if(candidat != null){
            Inscription inscription = inscriptionFacade.findInscriptionByCandidatAndAnnee(candidat, anneeUniversitaire);
            if(inscription != null){
                return inscription;
            }
        }
        return null;
    }

    //recuperation de la filiere
    private Filiere getParamFiliere() {
        //Filiere filiere = filiereComboBox.getValue();
        Filiere filiere = filiereFacade.findFiliereByNom(filieree.getText());
        return filiere;
    }

    //recuperation de la piece jointe
    private PieceJointe getParamPieceJointe() {
        PieceJointe pieceJointe = pieceJointeComboBox.getValue();
        return pieceJointe;
    }

    //recuperation de l'element de l'inscription
    private Object[] getParamInscriptionItem() {
        InscriptionItem inscriptionItem = new InscriptionItem();
        //on recupere l'inscription associée
        Inscription inscription = getParamInscription();
        if (inscription != null) {
            inscriptionItem.setInscription(inscription);
            inscriptionItem.setPieceJointe(getParamPieceJointe());
            if(nombreFourni.getText().equals("")){
                return new Object[]{-1, null};
            }
            //seulement des entiers a saisir (nbr fourni)
            try {
                Integer.parseInt(nombreFourni.getText());
            } catch (NumberFormatException e) {
                return new Object[]{-2, null};
            }
            inscriptionItem.setNombrePieceFournis(Integer.parseInt((nombreFourni.getText())));//convertir un String en int
            return new Object[]{1, inscriptionItem};
        }else{
            return new Object[]{-3, null};
        }
    }

    public TextField getFilieree() {
        return filieree;
    }

    public void setFilieree(TextField filieree) {
        this.filieree = filieree;
    }
    
    public ComboBox<PieceJointe> getPieceJointeComboBox() {
        return pieceJointeComboBox;
    }

    public void setPieceJointeComboBox(ComboBox<PieceJointe> pieceJointeComboBox) {
        this.pieceJointeComboBox = pieceJointeComboBox;
    }

    public TextField getCne() {
        return cne;
    }

    public void setCne(TextField cne) {
        this.cne = cne;
    }

    public TextField getNombreAfournir() {
        return nombreAfournir;
    }

    public void setNombreAfournir(TextField nombreAfournir) {
        this.nombreAfournir = nombreAfournir;
    }

    public TextField getNombreFourni() {
        return nombreFourni;
    }

    public void setNombreFourni(TextField nombreFourni) {
        this.nombreFourni = nombreFourni;
    }

}
