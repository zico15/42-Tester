/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pkg42.util.system.FileBase;

/**
 *
 * @author zequi
 */
public class Run extends Application {
 

    @Override
    public void start(Stage primaryStage) {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));   
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root, 350, 350);
        primaryStage.setTitle("42 Tester - C");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
