/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pkg42.util.system.Data;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class MainViewController implements Initializable {

     @FXML
    private AnchorPane view;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Data.VIEW_MAIN = view;
        Run.setPane("tester/Tester.fxml");
        //Run.setPane("execute/ExecuteView.fxml");
     }    
    

    @FXML
    void teste(ActionEvent event) {

    }
    
    @FXML
    void tester(ActionEvent event) {
        Run.setPane("tester/Tester.fxml");
    }
    
    @FXML
    void registerProject(ActionEvent event) {
        Run.setPane("register/RegisterProject.fxml");
    } 
  
    
    
}
