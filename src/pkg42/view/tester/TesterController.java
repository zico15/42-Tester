/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import pkg42.util.objects.ObjectProject;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class TesterController implements Initializable {

    @FXML
    private TreeView<String> treeViewProjct;
    private  ObjectProject projectSelect;
    @FXML
    private TextArea textArea;
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     textArea.appendText("console\n");
    }    
    

    /**
     * Event emited by "AnchorPane" when the user drag a file
    */
    @FXML
    void dragOver(DragEvent e) {
        if (e.getDragboard().hasFiles())
        {
            e.acceptTransferModes(TransferMode.LINK);
        }
    }
    /**
     *  Function when the user drop the file in the treeView 
    */
    @FXML
    void dragDropped(DragEvent e) {

    }
    
 
    
    
}
