/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.tester;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zequi
 */
public class ObjectTest implements Serializable {
    
    private static final long serialVersionUID = 1;

    private final SimpleStringProperty name;
    private final SimpleStringProperty git;
    private final SimpleStringProperty author;
    private final ArrayList<String> outInput;
    
    public ObjectTest(String name, String git, String author){
        this.name = new SimpleStringProperty(name);
        this.git = new SimpleStringProperty(git);
        this.author = new SimpleStringProperty(author);
        this.outInput = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the git
     */
    public String getGit() {
        return git.get();
    }

    /**
     * @param git the git to set
     */
    public void setGit(String git) {
        this.git.set(git);
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author.get();
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author.set(author);
    }

    /**
     * @return the outInput
     */
    public ArrayList<String> getOutInput() {
        return outInput;
    } 
}
