package pkg42.util.system;

import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;

import java.io.File;
import java.util.ArrayList;

public class Data {

    public static final ArrayList<ObjectProject> PROJECTS = new ArrayList<>();
    public static final ArrayList<ObjectTest> TESTES = new ArrayList<>();
    public static String FILE_NAME = "data.json";
    public static String DIR_PROJECT = System.getProperty("user.dir");
    public static String DIR_TESTERS = System.getProperty("user.dir") + "/Testers";

}
