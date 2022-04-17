/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.system;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
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

    public static void openDialog() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane pane = loader.load(Run.class.getResource("DialogFxmla.fxml").openStream());
            Stage stage = new Stage();
            // now that we want to open dialog, we must use this line:
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void creadDialogConsole(ObjectTest tester) {



        if (tester != null && tester.textArea != null){
            Alert windows = new Alert(Alert.AlertType.WARNING);
            windows.setResizable(true);
            windows.setTitle(tester.name);
            windows.setOnCloseRequest(e->{
                windows.close();
            });
           // windows.get
            DialogPane d = windows.getDialogPane();
            /*AnchorPane.setTopAnchor(tester.textArea, 0.0);
            AnchorPane.setBottomAnchor(tester.textArea, 0.0);
            AnchorPane.setLeftAnchor(tester.textArea, 0.0);
            AnchorPane.setRightAnchor(tester.textArea, 0.0);*/
            d.setContent(tester.textArea);
            //alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            windows.showAndWait();
           /* Stage stage = new Stage();
            Scene scene = new Scene(tester.textArea, 400,600);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();*/
        }

    }
    
}
