/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.project;

import java.io.Serializable;
import java.util.ArrayList;
import pkg42.util.tester.ObjectTest;

/**
 *
 * @author zequi
 */
public class ObjectProject implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    public String name;
    private ArrayList<ObjectProjectFile> files;
     private ArrayList<ObjectTest> tests;
     
    public ObjectProject(String name)
    {
        this.name = name;
    }

    /**
     * @return the files
     */
    public ArrayList<ObjectProjectFile> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(ArrayList<ObjectProjectFile> files) {
        this.files = files;
    }

    /**
     * @return the tests
     */
    public ArrayList<ObjectTest> getTests() {
        return tests;
    }

    /**
     * @param tests the tests to set
     */
    public void setTests(ArrayList<ObjectTest> tests) {
        this.tests = tests;
    }
    
}
