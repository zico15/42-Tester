/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pkg42.util.system.Data;
import pkg42.util.system.FileBase;

/**
 *
 * @author zequi
 */
public class Run extends Application {
 

    @Override
    public void start(Stage primaryStage) {
        FileBase.deleteFolder(new File(Data.DIR_TESTERS));
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));   
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setOnCloseRequest(event -> {
            FileBase.deleteFolder(new File(Data.DIR_TESTERS));
            Platform.exit();
        });
        Scene scene = new Scene(root, 350, 350);
        primaryStage.setResizable(false);
        primaryStage.setTitle("42 Tester - C");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static  Object setPane(String pane){
        Pane root;
        try {
            FXMLLoader loader = new FXMLLoader(Run.class.getResource(pane));
            root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            Data.VIEW_MAIN.getChildren().setAll(root);
            return (loader.getController());
        } catch (IOException ex) {
            System.out.println("loadPane: " + ex.getLocalizedMessage());
        }
        return (null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
