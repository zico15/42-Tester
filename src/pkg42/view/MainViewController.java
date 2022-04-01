/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pkg42.util.FileBase;
import pkg42.util.project.ObjectProject;
import pkg42.util.tester.TesterBase;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class MainViewController implements Initializable {

     @FXML
    private AnchorPane view;
    public static HashMap<String, ObjectProject> PROJECT;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PROJECT = (HashMap<String, ObjectProject>) FileBase.readObject("list_project.42");
        if (PROJECT == null)
            PROJECT = new HashMap<>();
        else
            System.out.println("readObject: list_project.42 (OK)");
        setPane("tester/Tester.fxml");
     }    
    
//    FXMLLoader loader = new FXMLLoader(
//    getClass().getResource(this.initialView.getPath());
//);
//loader.setController(this);
//root = loader.load();
    private void setPane(String pane){
        Pane root;
        try {
            root = FXMLLoader.load(getClass().getResource(pane)); 
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
           view.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println("loadPane: " + ex.getLocalizedMessage());
        }
}
    @FXML
    void teste(ActionEvent event) {
         PROJECT.values().forEach(p -> {
             System.out.println("P: " + p.getName() + " Flies: "+ Arrays.toString(p.getFiles().toArray()));
         });
    }
    
    @FXML
    void tester(ActionEvent event) {
        setPane("tester/Tester.fxml");      
    }
    
    @FXML
    void registerProject(ActionEvent event) {
        setPane("register/RegisterProject.fxml");         
    } 
  
    
    
}