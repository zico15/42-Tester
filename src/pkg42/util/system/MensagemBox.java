/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.system;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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
       // Platform.runLater(() -> {            
            alert.setHeaderText(header);
            alert.setContentText(text);
              //alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            //System.out.println("Resul: "+ alert.getResult().getText());
       // });
        return (true);
    }
     
    public static DialogPane MyDialog() {
        DialogPane d = new DialogPane();
          
        HBox hBox = new HBox(6);
        hBox.getChildren().addAll(new TextField(), new Button("Button"));
        d.setContent(hBox);
        d.setStyle("-fx-padding: 1; -fx-background-color: #1d1d1d;-fx-border-color: #e2e2e2;-fx-border-width: 2;");
        d.getButtonTypes().add(ButtonType.OK);
        return (d);
    }
    
}
