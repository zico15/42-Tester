/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.system;

import javafx.scene.control.TreeItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pkg42.util.objects.ObjectCheck;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

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

    public static void deleteFolder(File file)
    {
        if (file.exists()) {
            for (File f : file.listFiles()) {

                if (f != null && f.exists()) {
                    if (f.isDirectory())
                        deleteFolder(f);
                    f.delete();
                }
            }
        }
    }


    public static void copyFile(File source, File dest)
    {
        try {
            File t;
            dest = new File(dest, source.getName());
            if (!dest.exists())
                dest.mkdirs();
           //System.out.println("Directory: " + dest);
            for(File f : source.listFiles()){

                t = new File(dest, f.getName());
                if(f != null && f.exists())
                {

                    if (f.isDirectory())
                    {
                        System.out.println("Directory: " + t);
                        copyFile(f, dest);
                    }
                    else if (f.isFile())
                    {
                        System.out.println("File: " + t);

                            creadFile(f, t);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creadFile(File sourceFile, File destFile) throws IOException {

        if (!destFile.exists())
            destFile.createNewFile();
        FileChannel source = new FileInputStream(sourceFile).getChannel();;
        FileChannel destination =  new FileOutputStream(destFile).getChannel();;
        if (destination != null && source != null)
            destination.transferFrom(source, 0, source.size());
        if (source != null)
            source.close();
        if (destination != null)
            destination.close();
    }

    public static void saveData() {
        JSONObject data = new JSONObject();
        JSONArray pro = new JSONArray();
        JSONArray tes = new JSONArray();
        //JSONObject pro = new JSONObject();
        data.put("projects", pro);
        // JSONObject tes = new JSONObject();
        data.put("testers", tes);
        // data.add(pro);
        //data.add(tes);
        Data.PROJECTS.forEach(p -> {
            pro.add(p.json());
        });
        Data.TESTES.forEach(t -> {
            tes.add(t.json());
        });
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

        try (FileReader reader = new FileReader(Data.FILE_NAME))
        {
            JSONObject list = (JSONObject) new JSONParser().parse(reader);
            //System.out.println("employeeObject: " +list);
            JSONArray pro = (JSONArray) list.get("projects");
            if (pro != null)
                pro.forEach(e -> {
                    Data.PROJECTS.add(new ObjectProject((JSONObject) e));
                });
            JSONArray tes = (JSONArray) list.get("testers");
            if (tes != null)
                tes.forEach(e -> {
                    Data.TESTES.add(new ObjectTest((JSONObject) e));
                });
        } catch (FileNotFoundException e) {
            System.out.println("(Error) FileReader: " + e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static File createFolder(String file)
    {
        File f = new File(file);
        if (!f.exists())
            f.mkdirs();
        return (f);

    }

    public static ArrayList<ObjectCheck> checkProject(File file) {
        ArrayList<ObjectCheck> projects = new ArrayList<>();
        int check;

        for (int i = 0; i < Data.PROJECTS.size(); i++) {
            check = checkFilesProject(file, Data.PROJECTS.get(i));
            if (check > 0) {
                projects.add(new ObjectCheck(check, Data.PROJECTS.get(i)));
            }
        }
        return (organizeCheck(projects));
    }

    private static ArrayList<ObjectCheck> organizeCheck(ArrayList<ObjectCheck> list) {
        ArrayList<ObjectCheck> projects = new ArrayList<>();
        int max;
        int mix = Integer.MAX_VALUE;
        while (projects.size() != list.size()) {
            max = -1;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).check > max && list.get(i).check < mix)
                    max = list.get(i).check;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).check == max)
                    projects.add(list.get(i));
            }
            mix = max;
        }
        list.clear();
        return (projects);
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
                    for(int a = 0; a < p.files.size(); a++)
                    {
                        if (p.files.get(a) != null && p.files.get(a).equals(f.getName()))
                            i++;
                    }
                }

            }
        }
        return (i);
    }

    public static ArrayList<ObjectTest> getTesters(ObjectProject p)
    {
        ArrayList<ObjectTest> list = new ArrayList<>();

        if (p != null)
        {
            Data.TESTES.forEach(t ->
            {
                if (t.type.equalsIgnoreCase(p.name))
                    list.add(t);
            });
        }
        return (list);
    }
}
