/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import pkg42.util.FileBase;
import pkg42.util.project.ObjectProject;
import pkg42.util.system.MensagemBox;
import pkg42.view.MainViewController;

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
    
    private void download(){
         System.out.println("Download");
        projectSelect.getTests().forEach(g -> {       
            System.out.println("Start Download");
            try {
                String line = "";
                BufferedReader reader = FileBase.execuTerminal("cd");
                System.out.println("reader: "+reader.toString());
                while((line = reader.readLine()) != null)
                    textArea.appendText(line + "\n");
            } catch (IOException ex) {
                System.out.println("(ERRO) download: " + ex.getLocalizedMessage());
            }
         
        });
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
        projectSelect = null;
        List<File> files = e.getDragboard().getFiles();
        // If folder droped has more than one file lets create a treeItem
        if (files.size() > 0)
        {
            TreeItem<String> item = new TreeItem<> (files.get(0).getName());
            FileBase.getFilesProject(files.get(0), item);
            MainViewController.PROJECT.values().forEach(p -> {
                System.out.println("Logo abaixo");
                System.out.println(FileBase.checkFilesProject(files.get(0), p));
                if (FileBase.checkFilesProject(files.get(0), p) > 0) 
                {
                    projectSelect = p;
                    System.out.println("P: " + p.getName());   
                }                               
            });
            treeViewProjct.setRoot(item);
            if (projectSelect !=  null && MensagemBox.showAlertOption("Project: " + projectSelect.getName(), "Start Download?"))
                download();
            else {
                MensagemBox.showAlertErr("Project not found!");
                // Case project not found reset the treeView
                treeViewProjct.setRoot(null);
            }
        }
        // If no files was found lets reset treeView
        else treeViewProjct.setRoot(null);
    }
    
 
    
    
}
