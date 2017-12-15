/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Administrateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.AdministrateurFacade;
import util.HashageUtil;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class NouveauAdminController implements Initializable {
    
    @FXML
    private TextField nvLogin;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private PasswordField nvPassword;
    @FXML
    private PasswordField confirmPassword;
    
    AdministrateurFacade administrateurFacade = new AdministrateurFacade();
    
    @FXML
    public void saveAdmin(ActionEvent actionEvent){
        Administrateur administrateur = getParamAdmin();
        if(administrateur != null){
           Administrateur administrateurBD = administrateurFacade.find(administrateur.getLogin());
           if(administrateurBD != null){
               alertExisteDeja(actionEvent);
           }else{
               if(!administrateur.getPassword().equals(confirmPassword.getText())){
                   alertConfirmerMdp(actionEvent);
               }else{
                   administrateur.setPassword(HashageUtil.sha256(administrateur.getPassword()));
                   administrateurFacade.create(administrateur);
               }
           }
        }else{
            alertChamps(actionEvent);
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
        // TODO
    }    
    
    private Administrateur getParamAdmin(){
        boolean x = testerChamps();
        if(x){
           return new Administrateur(nvLogin.getText(), nvPassword.getText(), nom.getText(), prenom.getText());
        }else{
            return null;
        }
    }
    
    private boolean testerChamps(){
        if(nvLogin.getText().equals(""))
            return false;
        else if(nom.getText().equals(""))
            return false;
        else if(prenom.getText().equals(""))
            return false;
        else if(nvPassword.getText().equals(""))
            return false;
        else if(confirmPassword.getText().equals(""))
            return false;
        else
            return true;
    }
    
    private void alertChamps(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides !");
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertExisteDeja(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exist !");
        alert.setContentText("Ce login existe deja !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertConfirmerMdp(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password !");
        alert.setContentText("Veuillez confirmer votre mot de passe !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public TextField getNvLogin() {
        return nvLogin;
    }

    public void setNvLogin(TextField nvLogin) {
        this.nvLogin = nvLogin;
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

    public PasswordField getNvPassword() {
        return nvPassword;
    }

    public void setNvPassword(PasswordField nvPassword) {
        this.nvPassword = nvPassword;
    }

    public PasswordField getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(PasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    
}
