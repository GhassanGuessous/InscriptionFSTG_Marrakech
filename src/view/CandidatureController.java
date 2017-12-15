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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.AnneeUniversitaireFacade;
import service.CandidatFacade;
import service.FiliereFacade;
import service.InscriptionFacade;
import util.DateUtil;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class CandidatureController implements Initializable {
    
    @FXML
    private TextField cne;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField anneeU;
    @FXML
    private DatePicker dateNaissance = new DatePicker();
    @FXML
    private ComboBox<Filiere> filiereComboBox = new ComboBox<>();
    
    CandidatFacade candidatFacade = new CandidatFacade();
    InscriptionFacade inscriptionFacade = new InscriptionFacade();
    FiliereFacade filiereFacade = new FiliereFacade();
    AnneeUniversitaireFacade anneeUniversitaireFacade = new AnneeUniversitaireFacade();
    
    private void initCombobox(){
        filiereComboBox.setItems(FXCollections.observableArrayList(filiereFacade.findAll()));
        filiereComboBox.getSelectionModel().select(0);
    }
    
    public void save(ActionEvent actionEvent){
        Inscription inscription = getParamInscription(); //recuperer la candiature
        if(inscription != null){
            Candidat candidat = inscription.getCandidat();//recuperer le condidat
            Candidat candidat1 = candidatFacade.find(candidat.getId());//chercher si le candidat figure dans la BD
            if (candidat1 != null) {
                //verifier si le candidat est deja inscrit
                Inscription inscription1 = inscriptionFacade.findInscriptionByCandidatAndAnnee(candidat, inscription.getAnneeUniversitaire());
                if (inscription1 != null) {
                    alertExistDeja(actionEvent);
                } else {
                    inscriptionFacade.create(inscription);
                }
            } else {
                candidatFacade.create(candidat);//enregistrer le candidat
                inscriptionFacade.create(inscription);//enregistrer l'inscription
            }
        }else{
            alertChampVide(actionEvent);
        }
    }
    
    @FXML
    public void clickDeposerPiecesFromCandidature(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "InscriptionItem.fxml", this.getClass());
    }
    
    @FXML
    public void clickVerifierFromCandidature(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Verification.fxml", this.getClass());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCombobox();
    }
    
    private boolean testerChamps(){
        if(cne.getText().equals(""))
            return false;
        else if(nom.getText().equals(""))
            return false;
        else if(prenom.getText().equals(""))
            return false;
        else if(dateNaissance.getValue() == null)
            return false;
        else
            return true;
    }
    
    private Candidat getParamCandidat(){
        boolean x = testerChamps();
        if(x){
            return new Candidat(cne.getText(), nom.getText(), prenom.getText(), DateUtil.convert(dateNaissance.getValue().toString()));
        }else{
            return null;
        }
        
    }
    
    private Filiere getParamFiliere(){
        Filiere filiere = filiereComboBox.getValue();
        return filiere;
    }
    
    private AnneeUniversitaire getParamAnneeUniversitaire(){
        AnneeUniversitaire anneeUniversitaire = anneeUniversitaireFacade.find(anneeU.getText());
        return anneeUniversitaire;
    }
    
    private Inscription getParamInscription(){
        Filiere filiere = getParamFiliere();
        Candidat candidat = getParamCandidat();
        if(candidat != null){
            Inscription inscription = new Inscription();
            inscription.setCandidat(candidat);
            inscription.setFiliere(filiere);
            inscription.setAnneeUniversitaire(getParamAnneeUniversitaire());
            return inscription;
        }else{
            return null;
        }

    }
    
    private void alertExistDeja(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert Inscription !");
        alert.setContentText("Vous êtes déjà inscrit !");
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

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getPrenom() {
        return prenom;
    }

    public void setPrenom(TextField prenom) {
        this.prenom = prenom;
    }

    public DatePicker getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(DatePicker dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public ComboBox<Filiere> getFiliereComboBox() {
        return filiereComboBox;
    }

    public void setFiliereComboBox(ComboBox<Filiere> filiereComboBox) {
        this.filiereComboBox = filiereComboBox;
    }
    
    
    
}
