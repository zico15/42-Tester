/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.objects;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 *
 * @author zequi
 */
public class ObjectTest implements Serializable {
    
    private static final long serialVersionUID = 1;

    public String author;
    public String git;
    public String cmdStart;
    public int    qtdChecks;
    public String KeywordSuccess;
    public String KeywordFail;
    public String type;

    /**
     * Constructor (ObjectTest Class)
     */
    public ObjectTest()
    {
        type = "NULL";
    }
    
    public ObjectTest(JSONObject employeeObject){

        //Get employee first name
        author = (String) employeeObject.get("author");

        //Get employee last name
        git = (String) employeeObject.get("git");

        //Get employee first name
        cmdStart = (String) employeeObject.get("cmdStart");

        //Get employee last name
        qtdChecks = (int) ((long) employeeObject.get("qtdChecks"));

        //Get employee website name
        KeywordSuccess = (String) employeeObject.get("KeywordSuccess");

        KeywordFail = (String) employeeObject.get("KeywordFail");

        //Get employee website name
        type = (String) employeeObject.get("type");
    }


    public  JSONObject json()
    {

        JSONObject   j = new JSONObject();
        j.put("author", author);
        j.put("git", git);
        j.put("cmdStart", cmdStart);
        j.put("qtdChecks", qtdChecks);
        j.put("KeywordSuccess", KeywordSuccess);
        j.put("KeywordFail", KeywordFail);
        j.put("type", type);
        return (j);
    }




}
