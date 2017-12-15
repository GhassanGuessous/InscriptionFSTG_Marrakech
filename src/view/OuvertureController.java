/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class OuvertureController implements Initializable {
    
    @FXML
    public void versCandidat(ActionEvent actionEvent) throws IOException{
        ViewLauncher.forward(actionEvent, "InscriptionItem.fxml", this.getClass());
    }
    
    @FXML
    public void versAdmin(ActionEvent actionEvent) throws IOException{
        ViewLauncher.forward(actionEvent, "Authentification.fxml", this.getClass());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
