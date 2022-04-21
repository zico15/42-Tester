/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import pkg42.util.objects.ObjectCheck;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.*;

import pkg42.view.Run;


/**
 * FXML Controller class
 *
 * @author zequi
 */
public class TesterController implements Initializable {

    @FXML
    private HBox buttonGit1;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FileBase.readData();
    }    
    

    /**
     * Event emited by "AnchorPane" when the user drag a file
    */
    @FXML
    void dragOver(DragEvent e) {
        if (e.getDragboard().hasFiles())
        {
            e.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        }
    }
    /**
     *  Function when the user drop the file in the treeView 
    */
    @FXML
    void dragDropped(DragEvent e) {
            File dir =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers");
            FileBase.deleteFolder(dir);
            if (e != null && e.getDragboard() != null && e.getDragboard().getFiles() != null && e.getDragboard().getFiles().size() > 0) {
                executeProject(e.getDragboard().getFiles().get(0), new File(dir, e.getDragboard().getFiles().get(0).getName()));
                e.consume();
            }

    }

    private void executeProject(File file_origem, File file_tester)
    {
        File dir =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers");
        Data.PROJECT_SELECT = null;
        if (file_origem != null && file_origem.exists()) {
            ArrayList<ObjectCheck> checks = FileBase.checkProject(file_origem);
            if (checks.size() > 0) {
                ArrayList<ObjectTest> testers = FileBase.getTesters(checks.get(0).project);
                //if(MensagemBox.showAlertOption(checks.get(0).project.name, "start tester (" + testers.size() + ")!")) {
                    checks.get(0).project.testers = testers;
                    checks.get(0).project.file_origem = file_origem;
                    checks.get(0).project.file_tester = file_tester;
                    Data.PROJECT_SELECT = checks.get(0).project;
                    Run.setPane("execute/ExecuteView.fxml");
               /* }
                else*
                    FileBase.deleteFolder(dir);*/
            }
        }
    }

    @FXML
    void paste(ActionEvent event) {
        Platform.runLater(() -> {
            Clipboard cb = Clipboard.getSystemClipboard();
            if (!cb.hasString())
                return;
            File dir =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers");
            FileBase.deleteFolder(dir);
            Data.PROJECT_SELECT = null;
            String paste = cb.getString();
            if (paste.contains("https://github.com"))
            {
                String pasta = paste.substring(paste.lastIndexOf("/") + 1, paste.lastIndexOf(".git")).trim();
                System.out.println("git: " + pasta);
                buttonGit1.setVisible(true);
                new TerminalBase() {
                    @Override
                    public void finalize() throws Throwable {
                        buttonGit1.setVisible(false);
                        executeProject(new File(dir, pasta), new File(dir, pasta));
                    }
                }.exec(dir, "git", "clone", paste);

            }
        });
    }


}
