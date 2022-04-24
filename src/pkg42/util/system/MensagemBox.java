/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.system;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg42.util.objects.ObjectCheck;
import pkg42.util.objects.ObjectTest;
import pkg42.view.Run;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public static boolean showOption(ArrayList<ObjectCheck> checks) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        ObservableList<String> options = FXCollections.observableArrayList();
        Data.PROJECTS.forEach(p->
        {
            options.add(p.name);
        });
        final ComboBox comboBox = new ComboBox(options);

        AnchorPane.setTopAnchor(comboBox, 20.0);
        AnchorPane.setLeftAnchor(comboBox, 10.0);
        AnchorPane.setRightAnchor(comboBox, 10.0);
        AnchorPane pane = new AnchorPane(comboBox);
        pane.setMinWidth(350);
        comboBox.getSelectionModel().select(0);
        dialog.setHeaderText("Project: "+comboBox.getSelectionModel().getSelectedItem());
        comboBox.setOnAction(e->{
            checks.clear();
            checks.add(new ObjectCheck(1, Data.PROJECTS.get(comboBox.getSelectionModel().getSelectedIndex())));
            dialog.setHeaderText("Project: "+comboBox.getSelectionModel().getSelectedItem());
        });
        dialog.getDialogPane().setContent(pane);
        dialog.setTitle("Project select");
        dialog.setOnCloseRequest(event -> {
            ((Dialog) event.getSource()).hide();
        });
        return (dialog.showAndWait().get() == ButtonType.OK);
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
