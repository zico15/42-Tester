/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.objects;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zequi
 */
public class ObjectProject implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    public String name;

    public ArrayList<String> files;



    /**
     * Constructor (ObjectProject Class)
     */
    public ObjectProject()
    {
        name = "NULL";
        files = new ArrayList<>();
    }
    /**
     * Constructor (ObjectProject Class)
    */
    public ObjectProject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("projects");

        //Get employee first name
        name = (String) employeeObject.get("name");
        System.out.println(name);

        //Get employee last name
        files = (ArrayList<String>) employeeObject.get("files");
        System.out.println(files.toArray().toString());

    }

    public  JSONObject json()
    {
        JSONObject project = new JSONObject();

        JSONObject   j = new JSONObject();
        j.put("name", name);
        j.put("files", files);
        project.put("projects", j);
        return (project);
    }
}
