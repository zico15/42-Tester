/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.tester;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zequi
 */
public class ObjectTest implements Serializable {
    
    private static final long serialVersionUID = 1;

    private String name;
    private String git;
    private String author;
    private final ArrayList<String> outInput;
    
    public ObjectTest(String name, String git, String author){
        this.name = name;
        this.git = git;
        this.author = author;
        this.outInput = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the git
     */
    public String getGit() {
        return git;
    }

    /**
     * @param git the git to set
     */
    public void setGit(String git) {
        this.git = git;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the outInput
     */
    public ArrayList<String> getOutInput() {
        return outInput;
    } 
    
     @Override
    public String toString() {
        return "N: " + getName()+ "A: " + getAuthor() + "G: " + getGit(); //To change body of generated methods, choose Tools | Templates.
    }
}
