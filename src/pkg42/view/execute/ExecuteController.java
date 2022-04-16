/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.execute;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import pkg42.util.objects.ObjectCheck;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;
import pkg42.util.system.FileBase;
import pkg42.util.system.MensagemBox;
import pkg42.util.system.Terminal;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class ExecuteController implements Initializable {

    private double v;

    @FXML
    private ProgressBar progress;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }


    public void initTester(File file, File dir, ObjectProject project, ArrayList<ObjectTest> testers)
    {

        //create folder
        File pro =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers/"+file.getName());
        progress.setProgress(0);
        if (dir.exists() && dir.isDirectory())
        {

            FileBase.copyFile(file, dir);
            testers.forEach(t -> {
                Terminal.exec(pro, null, 0, "git", "clone", t.git);
                System.out.println("V: "+t.qtdChecks);
                v = 1 / (double)(t.qtdChecks);
            });
           System.out.println("V: "+v);
            try {
                Thread.sleep(5 * 1000);
                System.out.println("sleep: ok");
                Terminal.exec(new File(pro, "gnlTester"), progress, v, "make");
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

        }

    }


    
}
