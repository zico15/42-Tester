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
public class ObjectTableFile {
    
    public SimpleStringProperty type;
    public SimpleStringProperty file;
    
    public ObjectTableFile(String type, String file)
    {
        this.type = new SimpleStringProperty(type);
        this.file= new SimpleStringProperty(file);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type.get();
    }
    
    public SimpleStringProperty typeProperty() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type.set(type);
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file.get();
    }
    
    public SimpleStringProperty fileProperty() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file.set(file);
    }
}
