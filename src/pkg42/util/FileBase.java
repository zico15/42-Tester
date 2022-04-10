/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util;

import java.io.*;

import javafx.scene.control.TreeItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;

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

    public static void saveData() {
        JSONArray data = new JSONArray();

        Data.PROJECTS.values().forEach(p -> { data.add(p.json()); });
        Data.TESTES.forEach(t -> { data.add(t.json()); });
        //Write JSON file
        try (FileWriter file = new FileWriter(Data.FILE_NAME)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {

        Data.PROJECTS.clear();
        Data.TESTES.clear();
        ObjectProject project;
        ObjectTest tester;

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(Data.FILE_NAME))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

           /* for (int i = 0; i < employeeList.size(); i++)
            {
                project = new ObjectProject((JSONObject) employeeList.get(i));
                if (project != null)
                    Data.PROJECTS.put(project.name, project);
            }

            for (int i = 0; i < employeeList.size(); i++)
            {
                tester = new ObjectTest((JSONObject) employeeList.get(i));
                if (tester != null)
                    Data.TESTES.add(tester);
            }*/

        } catch (FileNotFoundException e) {
            System.out.println("(Error) FileReader: " + e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public static int checkFilesProject(File file, ObjectProject p)
    {
        int i =  0;
       /* for(File f : file.listFiles()){
                
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
        }*/
        return (i);
    }

}
