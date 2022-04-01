/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.view.register.tableObject;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zequi
 */
public class ObjectTableTest {
    
    public SimpleStringProperty name;
    public SimpleStringProperty author;
    public SimpleStringProperty git;
    
    public ObjectTableTest(String name, String author, String git)
    {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.git = new SimpleStringProperty(git);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }
    
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author.get();
    }
    
    public SimpleStringProperty authorProperty() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(SimpleStringProperty author) {
        this.author = author;
    }

    /**
     * @return the git
     */
    public String getGit() {
        return git.get();
    }
    
    public SimpleStringProperty gitProperty() {
        return git;
    }
    /**
     * @param git the git to set
     */
    public void setGit(SimpleStringProperty git) {
        this.git = git;
    }
}
