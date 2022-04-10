package pkg42.util;


import pkg42.util.system.FileBase;
import pkg42.util.system.Terminal;

import java.io.File;

public class teste {


    public static void main(String args[])
    {
        FileBase.readData();
        // FileBase.saveData();
       // System.out.println("checkProject: " + FileBase.checkProject(new File("/Users/edos-san/Desktop/Get_next_line/git_next_line")));
        Terminal.editMake(new File("/Users/edos-san/Documents/GitHub/42-Tester"));
    }



}
