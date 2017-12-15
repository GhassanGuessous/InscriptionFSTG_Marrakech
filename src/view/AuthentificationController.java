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
public class AuthentificationController implements Initializable {
    
    AdministrateurFacade administrateurFacade = new AdministrateurFacade();
    
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    
    @FXML
    public void sAuthentifier(ActionEvent actionEvent) throws IOException{
        Administrateur administrateur = getParamAdmin();
        if(administrateur != null){
            Object[] res = administrateurFacade.findAdmin(administrateur);
            int res1 = (int) res[0];
            if (res1 == 1) {
                Session.setAttribut(res[1], "admin");
                ViewLauncher.forward(actionEvent, "MenuAdmin.fxml", this.getClass());
            } else {
                alertAuthentification(actionEvent);
            }
        }else{
            alertChampVide(actionEvent);
        }
        
    }
    
    @FXML
    public void retour(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Ouverture.fxml", this.getClass());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void alertAuthentification(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentification !");
        alert.setContentText("Login ou password incorrect !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private Administrateur getParamAdmin(){
        boolean x = testChamps();
        if(x){
            return new Administrateur(login.getText(), password.getText());
        }else{
            return null;
        }
        
    }
    
    private boolean testChamps(){
        if(login.getText().equals(""))
            return false;
        else if(password.getText().equals(""))
            return false;
        else
            return true;
    }
    
    private void alertChampVide(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Champs !");
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public TextField getLogin() {
        return login;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }
    
}
