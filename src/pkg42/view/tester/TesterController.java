/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import pkg42.util.FileBase;
import pkg42.util.tester.TesterBase;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class TesterController implements Initializable {

    @FXML
    private TreeView<String> treeViewProjct;
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    
    
    @FXML
    void dragOver(DragEvent e) {
        if (e.getDragboard().hasFiles())
        {
            e.acceptTransferModes(TransferMode.LINK);
        }
    }
    
       @FXML
    void dragDropped(DragEvent e) {
        List<File> files = e.getDragboard().getFiles();
        if (files.size() > 0)
        {
               TreeItem<String> item = new TreeItem<> (files.get(0).getName());
               FileBase.getFilesProject(files.get(0), item);
               treeViewProjct.setRoot(item);
        }
        else
            treeViewProjct.setRoot(null);
      
    }
}
