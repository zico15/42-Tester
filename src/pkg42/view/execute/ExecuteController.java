/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.execute;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;
import pkg42.util.system.FileBase;
import pkg42.util.system.Terminal;
import pkg42.view.Run;
import pkg42.view.execute.item.ItemController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class ExecuteController implements Initializable {

    @FXML
    private ListView<ItemController> list_view;

    @FXML
    private ProgressIndicator progess;

    private ObservableList<ItemController> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list_view.setCellFactory(new Callback<ListView<ItemController>, ListCell<ItemController>>() {
            @Override
            public ListCell<ItemController> call(ListView<ItemController> listView) {
                return new CustomListCell();
            }
        });
        list_view.setItems(data);
        progess.setVisible(true);
        list_view.setVisible(false);
        Thread run = new Thread(){
            public void run() {
                    initTester(Data.PROJECT_SELECT.file_origem, Data.PROJECT_SELECT.file_tester, Data.PROJECT_SELECT, Data.PROJECT_SELECT.testers);
            }
        };
        run.start();

    }


    public void initTester(File file, File dir, ObjectProject project, ArrayList<ObjectTest> testers)
    {
        data.clear();
        //create folder
        File pro =  FileBase.createFolder(Data.DIR_PROJECT + "/Testers/"+file.getName());
        if (dir.exists() && dir.isDirectory())
        {

                FileBase.copyFile(file, dir);
                testers.forEach(t -> {
                    Terminal.exec(pro, "git", "clone", t.git);
                    System.out.println("V: "+t.qtdChecks);
                    data.add(getItem("item/ItemView.fxml" ,t));

                });
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("sleep: ok");
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
        }
        progess.setVisible(false);
        list_view.setVisible(true);
        data.forEach(e -> {
            Thread run = new Thread() {
                public void run() {
                    e.initTester(pro);
                }
            };
            run.start();
        });

    }

    private ItemController getItem(String pane, ObjectTest terter){
        Pane root;
        try {
            FXMLLoader loader = new FXMLLoader(ExecuteController.class.getResource(pane));
            root = loader.load();
            //AnchorPane.setLeftAnchor(root, 0.0);
           // AnchorPane.setRightAnchor(root, 0.0);
            ItemController c = loader.getController();
            if (c != null)
            {
                c.tester = terter;
                c.pane = root;
                c.setTextes();
            }
            return (c);
        } catch (IOException ex) {
            System.out.println("loadPane: " + ex.getLocalizedMessage());
        }
        return (null);
    }

    @FXML
    void home(ActionEvent event) {
        Run.setPane("tester/Tester.fxml");
    }
    
}
