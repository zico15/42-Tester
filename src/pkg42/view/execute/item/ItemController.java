/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.execute.item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;
import pkg42.util.system.FileBase;
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
public class ItemController implements Initializable {

    private double v;

    @FXML
    private Label text_title;

    @FXML
    private Label text_ok;

    @FXML
    private ProgressBar progress;

    @FXML
    private Label text_ko;

    @FXML
    private Label text_segm;

    public Pane pane;

    public ObjectTest tester;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void setTextes()
    {
        if (tester != null)
        {
            text_title.setText(tester.name);
            text_ok.setText("0 / 0");
            text_ko.setText("0 / 0");
            text_segm.setText("0 / 0");
            v = 1 / (double) (tester.qtdChecks);
        }
    }

    public void initTester(File project)
    {
        if (tester != null && project.exists()) {
            progress.setProgress(0);
            Terminal.exec(new File(project, tester.name), progress, v, "make");
        }
    }


    
}
