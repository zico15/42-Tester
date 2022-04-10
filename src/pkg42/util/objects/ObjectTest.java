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
        System.out.println(author);

        //Get employee last name
        git = (String) employeeObject.get("git");
        System.out.println(git);

        //Get employee first name
        cmdStart = (String) employeeObject.get("cmdStart");
        System.out.println(cmdStart);

        //Get employee last name
        qtdChecks = (int) ((long) employeeObject.get("qtdChecks"));
        System.out.println(qtdChecks);

        //Get employee website name
        KeywordSuccess = (String) employeeObject.get("KeywordSuccess");
        System.out.println(KeywordSuccess);

        KeywordFail = (String) employeeObject.get("KeywordFail");
        System.out.println(KeywordFail);

        //Get employee website name
        type = (String) employeeObject.get("type");
        System.out.println(type);
    }


    public  JSONObject json()
    {
        JSONObject tester = new JSONObject();

        JSONObject   j = new JSONObject();
        j.put("author", author);
        j.put("git", git);
        j.put("cmdStart", cmdStart);
        j.put("qtdChecks", qtdChecks);
        j.put("KeywordSuccess", KeywordSuccess);
        j.put("KeywordFail", KeywordFail);
        j.put("type", type);
        tester.put("testers" , j);
        return (tester);
    }




}
