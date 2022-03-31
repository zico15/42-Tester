/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zequi
 */
public class FileBase {

    public static void getFilesProject(File file, TreeItem<String> item)
    {
        for(File f : file.listFiles()){
                
            if(f != null && f.exists())
            {
                TreeItem p = new TreeItem(f.getName());
                if (f.isDirectory())
                    getFilesProject(f, p);
                item.getChildren().add(p);
            }
        }
    }

    public static void saveObject(Object ob, String path) {
        File file;
        ObjectOutputStream obj;
        try {
            file = new File(path);
            obj = new ObjectOutputStream(new FileOutputStream(file, true));
            obj.writeObject(ob);
            obj.close();
        } catch (IOException e) {
            System.out.println("saveObject: " + e.getLocalizedMessage());
        }
    }

    public static Object readObject(String path) {
        FileInputStream file;
        Object ob = null;
        ObjectInputStream obj;
        try {
            file = new FileInputStream(path);
            obj = new ObjectInputStream(file);
             ob = obj.readObject();
            file.close();
            obj.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("readObject: " + e.getLocalizedMessage());
        } 
        return ob;
    }

}
