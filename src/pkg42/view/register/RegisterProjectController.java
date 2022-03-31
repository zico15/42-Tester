/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pkg42.util.project.ObjectProjectFile;
import pkg42.util.tester.ObjectTest;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class RegisterProjectController implements Initializable {

    @FXML
    private TableView<ObjectProjectFile> table_project;    
    @FXML
    private TableView<ObjectTest> table_tests;    
    @FXML
    private TabPane tabView;
    
     final ObservableList<ObjectProjectFile> data = FXCollections.observableArrayList(
            new ObjectProjectFile("sdf", "rthb"),
            new ObjectProjectFile("sdf", "err"),
            new ObjectProjectFile("dfdf", "fdf")
        );   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        initTableProject();
        initTableTests();
        saveProject();          
    }
    
    @FXML
    void insert(ActionEvent event) {
        Platform.runLater(() -> {
            if (tabView.getSelectionModel().getSelectedIndex() == 0)
                data.add(new ObjectProjectFile("sd", "w"));
            else if (tabView.getSelectionModel().getSelectedIndex() == 1)
                table_tests.getItems().add(new ObjectTest(null, null, null));
        });           
    }

    @FXML
    void remove(ActionEvent event) {

    }
    
    private void initTableProject(){
        table_project.setEditable(true);
        table_project.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        TableColumn<ObjectProjectFile, String>  type = new TableColumn("type");
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<ObjectProjectFile, String>  name = new TableColumn("Name");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<>("file"));
        type.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        name.setMaxWidth( 1f * Integer.MAX_VALUE * 85 );
        table_project.getColumns().addAll(type, name);
    }
    
    private void initTableTests(){
        table_tests.setEditable(true);
        table_tests.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        TableColumn<ObjectTest, String>  name = new TableColumn("name");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<ObjectTest, String>  author = new TableColumn("author");
        author.setCellFactory(TextFieldTableCell.forTableColumn());
        author.setEditable(true);
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<ObjectTest, String>  git = new TableColumn("git");
        git.setCellFactory(TextFieldTableCell.forTableColumn());
        git.setEditable(true);
        git.setCellValueFactory(new PropertyValueFactory<>("git"));
        name.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        author.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        git.setMaxWidth( 1f * Integer.MAX_VALUE * 65 );       
        table_tests.getColumns().addAll(name, author, git);
    }
    
    public void saveProject()
    {    
       
       table_project.setItems(data);
       data.add(new ObjectProjectFile("casa", "w"));
       
    }
}
