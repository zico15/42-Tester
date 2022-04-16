/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.tester;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import pkg42.util.objects.ObjectCheck;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;
import pkg42.util.system.FileBase;
import pkg42.util.system.MensagemBox;
import pkg42.util.system.Terminal;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class TesterController implements Initializable {

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
            if (e != null && e.getDragboard() != null && e.getDragboard().getFiles() != null) {
                ArrayList<ObjectCheck> checks = FileBase.checkProject(e.getDragboard().getFiles().get(0));

                if (checks.size() > 0) {
                    ArrayList<ObjectTest> testers = FileBase.getTesters(checks.get(0).project);
                    MensagemBox.showAlertOption(checks.get(0).project.name, "start tester (" + testers.size() + ")!");
                    initTester(e.getDragboard().getFiles().get(0), dir, checks.get(0).project, testers);
                }
                e.consume();
            }

    }

    private void initTester(File file, File dir, ObjectProject project, ArrayList<ObjectTest> testers)
    {
        //create folder
       File pro =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers/"+file.getName());

       if (dir.exists() && dir.isDirectory())
       {

           FileBase.copyFile(file, dir);
           testers.forEach(t -> {
              Terminal.exec(pro, null, "git", "clone", t.git);
           });
           try {
               Thread.sleep(5 * 1000);
               System.out.println("sleep: ok");
               Terminal.exec(new File(pro, "gnlTester"), null, "make");
           } catch (InterruptedException ie) {
               Thread.currentThread().interrupt();
           }

       }

    }
    
}
