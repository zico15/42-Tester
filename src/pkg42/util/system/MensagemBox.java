/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.system;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg42.util.objects.ObjectTest;
import pkg42.view.Run;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author zequi
 */
public class MensagemBox {
    
    public static void showAlertErr(String header) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(header);
            //alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        });
    }
    
     public static boolean showAlertOption(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         System.out.println("executeProject: "+text);
       // Platform.runLater(() -> {
            alert.setHeaderText(header);
            alert.setContentText(text);
              //alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            //System.out.println("Resul: "+ alert.getResult().getText());
       // });
        return (alert.showAndWait().get() == ButtonType.OK);
    }

    public static void creadDialogConsole(ObjectTest tester) {
        Platform.runLater(() -> {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText(" Author: " + tester.author);
            dialog.setResizable(true);
            dialog.getDialogPane().setContent(tester.textArea);
            dialog.setTitle("Tester: " + tester.name);
            dialog.setOnCloseRequest(event -> {
                ((Dialog) event.getSource()).hide();
            });
            dialog.show();
        });
    }
    
}
