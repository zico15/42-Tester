/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.project;

import java.io.Serializable;

/**
 *
 * @author zequi
 */
public class ObjectProjectFile implements Serializable{
   
    private static final long serialVersionUID = 1;

    private String type;
    private String file;
 
    public ObjectProjectFile(String type, String file) {
        this.type = type;
        this.file = file;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "T: " + getType() + "F: " + getFile(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
