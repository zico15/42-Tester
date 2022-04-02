/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Compiler.command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TreeItem;
import pkg42.util.project.ObjectProject;

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
            if (!file.exists())
                file.createNewFile();
            obj = new ObjectOutputStream(new FileOutputStream(file, true));
            obj.writeObject(ob);
            obj.close();
        } catch (IOException e) {
            System.out.println("(ERRO) SaveObject: " + e.getLocalizedMessage());
        }
    }
    
    public static BufferedReader execuTerminal(String command){
    
        Process proc;
        BufferedReader reader = null;
        try {
            proc = Runtime.getRuntime().exec(command);
            reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        } catch (IOException e) {
             System.out.println("(ERRO) execuTerminal: " + e.getLocalizedMessage());
        }
        return (reader);
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
            System.out.println("(ERRO) ReadObject: " + e.getLocalizedMessage());
        } 
        return ob;
    }
    
    public static int checkFilesProject(File file, ObjectProject p)
    {
        int i =  0;
        for(File f : file.listFiles()){
                
            if(f != null && f.exists())
            {
                if (f.isDirectory())
                {
                    i += checkFilesProject(f, p);
                }
                  
                else if (f.isFile())
                {
                    for(int a = 0; a < p.getFiles().size(); a++)
                    {
                        if (p.getFiles().get(a).getType().equals("FILE") && p.getFiles().get(a).getFile().equals(f.getName()))
                            i++;
                    }
                }

            }
        }
        return (i);
    }

}
