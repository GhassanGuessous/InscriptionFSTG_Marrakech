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
import helper.CandidatFxHelper;
import helper.InscriptionItemFxHelper;
import helper.PieceJointeFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import service.AnneeUniversitaireFacade;
import service.CandidatFacade;
import service.FiliereFacade;
import service.InscriptionFacade;
import service.InscriptionItemFacade;
import service.PieceJointeFacade;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class RechercheController implements Initializable {
    
    FiliereFacade filiereFacade = new FiliereFacade();
    PieceJointeFacade pieceJointeFacade = new PieceJointeFacade();
    AnneeUniversitaireFacade anneeUniversitaireFacade = new AnneeUniversitaireFacade();
    InscriptionFacade inscriptionFacade = new InscriptionFacade();
    CandidatFacade candidatFacade = new CandidatFacade();
    InscriptionItemFacade inscriptionItemFacade = new InscriptionItemFacade();
            
    private CandidatFxHelper candidatFxHelper;
    private PieceJointeFxHelper pieceJointeFxHelper;
    private InscriptionItemFxHelper inscriptionItemFxHelper;
    
    @FXML
    private ComboBox<Filiere> filiereComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> etatInscriptionComboBox = new ComboBox<>();
    @FXML
    private ComboBox<AnneeUniversitaire> anneUComboBox = new ComboBox<>();
    @FXML
    private TableView candidatTableView = new TableView();
    @FXML
    private TableView pieceJointeTableView = new TableView();
    @FXML
    private TableView inscriptionItemTableView = new TableView();
    
    private void initComboBox() {
        filiereComboBox.setItems(FXCollections.observableArrayList(filiereFacade.findAll()));
        etatInscriptionComboBox.setItems(FXCollections.observableArrayList(Arrays.asList("Complétes","Incomplétes","Les deux")));
        anneUComboBox.setItems(FXCollections.observableArrayList(anneeUniversitaireFacade.findAll()));
    }
    
    private void initHelper(){
        candidatFxHelper = new CandidatFxHelper(candidatTableView);
        pieceJointeFxHelper = new PieceJointeFxHelper(pieceJointeTableView);
        inscriptionItemFxHelper = new InscriptionItemFxHelper(inscriptionItemTableView);
    }
    
    @FXML
    public void searchList(ActionEvent actionEvent){
        List<Inscription> inscriptions = new ArrayList<>();
        Filiere filiere = getParamFiliere();
        int x = getParamEtat();
        AnneeUniversitaire anneeUniversitaire = getParamAnnee();
        if(filiere != null && anneeUniversitaire != null){
            if(x == 2){
                inscriptions = inscriptionFacade.chercherListInscriptions(filiere, x, anneeUniversitaire);
                candidatFxHelper.setList(candidatFacade.chercherListCandidat(inscriptions));
            }else if(x == 1){
                inscriptions = inscriptionFacade.chercherListInscriptions(filiere, x, anneeUniversitaire);
                candidatFxHelper.setList(candidatFacade.chercherListCandidat(inscriptions));
            }else if(x <= 0){
                inscriptions = inscriptionFacade.chercherListInscriptions(filiere, 2, anneeUniversitaire);
                inscriptions.addAll(inscriptionFacade.chercherListInscriptions(filiere, 1, anneeUniversitaire));
                candidatFxHelper.setList(candidatFacade.chercherListCandidat(inscriptions));
            }
        }else{
            alertChoix(actionEvent);
        }
    }
    
    @FXML
    public void detail(){
        Candidat candidat = candidatFxHelper.getSelected();
        if(candidat != null){
            Inscription inscription = inscriptionFacade.findInscriptionByCandidatAndAnnee(candidat, getParamAnnee());
            List<PieceJointe> pieceJointes = pieceJointeFacade.findPieceJointeByFilierePJF(inscription.getFiliere().getNom());
            List<InscriptionItem> inscriptionItems = inscriptionItemFacade.findInscriptionItemsByInscription(inscription);
            pieceJointeFxHelper.setList(pieceJointes);
            List<InscriptionItem> itemsVirtuel = inscriptionItemFacade.siZouaniLigalha(pieceJointes, inscriptionItems);
            inscriptionItemFxHelper.setList(itemsVirtuel);
        }else{
            alertSelection();
        }
    }
    
    @FXML
    public void menu(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "MenuAdmin.fxml", this.getClass());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
    }
    
    private void alertChoix(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Choix !");
        alert.setContentText("Filiere ou Année non choisie !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertSelection(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Selection !");
        alert.setContentText("Aucun candidat selectionné !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private Filiere getParamFiliere(){
        if(filiereComboBox.getValue() == null){
            return null;
        }else{
            return filiereComboBox.getValue();
        }
    }
    
    private int getParamEtat(){
        if(etatInscriptionComboBox.getValue() != null){
            if (etatInscriptionComboBox.getValue().equals("Complétes")) {
                return 2;
            } else if (etatInscriptionComboBox.getValue().equals("Incomplétes")) {
                return 1;
            }else if(etatInscriptionComboBox.getValue().equals("Les deux")){
                return 0;
            }
        }
        return -1;
    }
    
    private AnneeUniversitaire getParamAnnee(){
        if(anneUComboBox.getValue() == null){
            return null;
        }else{
            return anneUComboBox.getValue();
        }
    }
    
    public ComboBox<Filiere> getFiliereComboBox() {
        return filiereComboBox;
    }

    public void setFiliereComboBox(ComboBox<Filiere> filiereComboBox) {
        this.filiereComboBox = filiereComboBox;
    }

    public ComboBox<String> getEtatInscriptionComboBox() {
        return etatInscriptionComboBox;
    }

    public void setEtatInscriptionComboBox(ComboBox<String> etatInscriptionComboBox) {
        this.etatInscriptionComboBox = etatInscriptionComboBox;
    }

    public ComboBox<AnneeUniversitaire> getAnneUComboBox() {
        return anneUComboBox;
    }

    public void setAnneUComboBox(ComboBox<AnneeUniversitaire> anneUComboBox) {
        this.anneUComboBox = anneUComboBox;
    }

    public TableView getCandidatTableView() {
        return candidatTableView;
    }

    public void setCandidatTableView(TableView candidatTableView) {
        this.candidatTableView = candidatTableView;
    }
    
    
    
}
