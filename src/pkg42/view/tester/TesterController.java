/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import static java.awt.SystemColor.text;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int i;
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
    
    @FXML
    void dragOver(DragEvent e) {
        if (e.getDragboard().hasFiles())
        {
            e.acceptTransferModes(TransferMode.LINK);
        }
    }
    
       @FXML
    void dragDropped(DragEvent e) {
        projectSelect = null;
        List<File> files = e.getDragboard().getFiles();
        if (files.size() > 0)
        {
            TreeItem<String> item = new TreeItem<> (files.get(0).getName());
            FileBase.getFilesProject(files.get(0), item);
            MainViewController.PROJECT.values().forEach(p -> {
                if (FileBase.checkFilesProject(files.get(0), p) > 0) 
                {
                    projectSelect = p;
                    System.out.println("P: " + p.getName());   
                }                               
            });
            treeViewProjct.setRoot(item);
            if (projectSelect !=  null && MensagemBox.showAlertOption("Project: " + projectSelect.getName(), "Start Download?"))
                download();
            else
                MensagemBox.showAlertErr("Project not found!");
        }
        else
            treeViewProjct.setRoot(null);      
    }
    
 
    
    
}
