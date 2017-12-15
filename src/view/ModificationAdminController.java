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
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class ModificationAdminController implements Initializable {
    
    @FXML
    private TextField loginAdmin;
    @FXML
    private PasswordField ancienMdp;
    @FXML
    private PasswordField nouveauMdp;
    @FXML
    private PasswordField confirmMdp;
    
    AdministrateurFacade administrateurFacade = new AdministrateurFacade();
    
    @FXML
    public void changerMdp(ActionEvent actionEvent){
        Administrateur administrateur= getParamAdmin();
        if(administrateur != null){
            Administrateur administrateurBD = administrateurFacade.find(administrateur.getLogin());
            if (administrateurBD != null) {
                if (administrateur.getPassword().equals(administrateurBD.getPassword())) {
                    if (nouveauMdp.getText().equals(confirmMdp.getText())) {
                        administrateurBD.setPassword(nouveauMdp.getText());
                        administrateurFacade.edit(administrateurBD);
                    } else {
                        alertNotConfirm(actionEvent);
                    }
                } else {
                    alertMdpIncorrect(actionEvent);
                }
            } else {
                alertNotExist(actionEvent);
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
        Administrateur administrateur = (Administrateur) Session.getAttribut("admin");
        loginAdmin.setText(administrateur.getLogin());
    }  
    
    private Administrateur getParamAdmin(){
        Administrateur administrateur = new Administrateur();
        boolean x = testerChamps();
        if(x){
            administrateur.setLogin(loginAdmin.getText());
            administrateur.setPassword(ancienMdp.getText());
            return administrateur;
        }else{
            return null;
        }
    }
    
    private boolean testerChamps(){
        if(loginAdmin.getText().equals(""))
            return false;
        else if(ancienMdp.getText().equals(""))
            return false;
        else if(nouveauMdp.getText().equals(""))
            return false;
        else if(confirmMdp.getText().equals(""))
            return false;
        else
            return true;
    }
    
    private void alertNotExist(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not Exist !");
        alert.setContentText("Aucun administrateur ne correspond a ce login !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertMdpIncorrect(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect !");
        alert.setContentText("Mot de password incorrect !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertNotConfirm(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirm !");
        alert.setContentText("Confrimer le mot de passe !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertChamps(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides !");
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public TextField getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(TextField loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public PasswordField getAncienMdp() {
        return ancienMdp;
    }

    public void setAncienMdp(PasswordField ancienMdp) {
        this.ancienMdp = ancienMdp;
    }

    public PasswordField getNouveauMdp() {
        return nouveauMdp;
    }

    public void setNouveauMdp(PasswordField nouveauMdp) {
        this.nouveauMdp = nouveauMdp;
    }

    public PasswordField getConfirmMdp() {
        return confirmMdp;
    }

    public void setConfirmMdp(PasswordField confirmMdp) {
        this.confirmMdp = confirmMdp;
    }
    
    
    
    
}
