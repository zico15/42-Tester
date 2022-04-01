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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pkg42.util.FileBase;
import pkg42.util.project.ObjectProject;
import pkg42.util.project.ObjectProjectFile;
import pkg42.util.tester.ObjectTest;
import pkg42.view.MainViewController;
import pkg42.view.register.tableObject.ObjectTableFile;
import pkg42.view.register.tableObject.ObjectTableTest;

/**
 * FXML Controller class
 *
 * @author zequi
 */
public class RegisterProjectController implements Initializable {

    @FXML
    private TableView<ObjectTableFile> table_project;    
    @FXML
    private TableView<ObjectTableTest> table_tests;    
    @FXML
    private TabPane tabView;
    @FXML
    private TextField projectField;    
    private ObjectProject project;    
     private ObservableList<ObjectTableFile> files;
     private ObservableList<ObjectTableTest> tests;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        project = new ObjectProject(null);
        this.files = FXCollections.observableArrayList();
        this.tests = FXCollections.observableArrayList();
        initTableProject();
        initTableTests();        
    }
    
    @FXML
    void insert(ActionEvent event) {
        Platform.runLater(() -> {
            if (tabView.getSelectionModel().getSelectedIndex() == 0)
                files.add(new ObjectTableFile(null,null));
            else if (tabView.getSelectionModel().getSelectedIndex() == 1)
               tests.add(new ObjectTableTest(null,null,null));
        });           
    }

    @FXML
    void remove(ActionEvent event) {

    }
    
    private void initTableProject(){
        table_project.setEditable(true);
        table_project.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        TableColumn<ObjectTableFile, String>  type = new TableColumn("type");
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<ObjectTableFile, String>  name = new TableColumn("Name");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<>("file"));
        type.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        name.setMaxWidth( 1f * Integer.MAX_VALUE * 85 );
        table_project.getColumns().addAll(type, name);
        table_project.setItems(files);
    }
    
    private void initTableTests(){
        table_tests.setEditable(true);
        table_tests.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        TableColumn<ObjectTableTest, String>  name = new TableColumn("name");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<ObjectTableTest, String>  author = new TableColumn("author");
        author.setCellFactory(TextFieldTableCell.forTableColumn());
        author.setEditable(true);
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<ObjectTableTest, String>  git = new TableColumn("git");
        git.setCellFactory(TextFieldTableCell.forTableColumn());
        git.setEditable(true);
        git.setCellValueFactory(new PropertyValueFactory<>("git"));
        name.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        author.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        git.setMaxWidth( 1f * Integer.MAX_VALUE * 65 );       
        table_tests.getColumns().addAll(name, author, git);
        table_tests.setItems(tests);
    }
    
    @FXML
    void saveProject(ActionEvent event)
    {    
       if (!projectField.getText().isEmpty())
       {
           project.setName(projectField.getText().trim().toLowerCase());
           project.getFiles().clear();
           project.getTests().clear();
           files.forEach(c -> {              
               project.getFiles().add(new ObjectProjectFile(c.getType(), c.getFile()));
           });
           tests.forEach(c -> {
               project.getTests().add(new ObjectTest(c.getName(), c.getGit(), c.getAuthor()));
           });
           MainViewController.PROJECT.put(project.getName(), project);
           FileBase.saveObject(MainViewController.PROJECT, "list_project.42");
           System.out.println("saveObject: "+ "list_project.42");
       }
    }
    
    @FXML
    void loadProject(ActionEvent event){                
        if (!projectField.getText().isEmpty() && !MainViewController.PROJECT.containsKey(projectField.getText().trim().toLowerCase()))
            return ;
        System.out.println("loadProject: " + projectField.getText().trim());
        project = MainViewController.PROJECT.get(projectField.getText().trim().toLowerCase());
        project.setName(projectField.getText().trim());
        files.clear();
        tests.clear();
        project.getFiles().forEach(c -> {
            files.add(new ObjectTableFile(c.getType(),c.getFile()));
        });
        project.getTests().forEach(c -> {
           tests.add(new ObjectTableTest(c.getName(), c.getAuthor(), c.getGit()));
        });
    }
}
