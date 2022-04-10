package pkg42.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pkg42.util.objects.ObjectProject;
import pkg42.util.objects.ObjectTest;
import pkg42.util.system.Data;

import java.io.IOException;
import java.io.Reader;

class JsonEncodeDemo {

    public static void main(String[] args) {

        /*ObjectProject project = new ObjectProject();
        project.name = "get next line";
        project.files.add("get_next_line.c");
        project.files.add("get_next_line_utils.c");
        project.files.add("get_next_line.h");

        Data.PROJECTS.put(project.name, project);

        ObjectProject project2 = new ObjectProject();
        project2.name = "teste";
        project2.files.add("file1.c");
        project2.files.add("file2.c");
        project2.files.add("file3.h");

        Data.PROJECTS.put(project2.name, project2);

        ObjectTest tester = new ObjectTest();
        tester.type = "get next line";
        tester.git = ("git teste");
        tester.author = "author tester";

        Data.TESTES.add(tester);*/

        //FileBase.saveData();
        FileBase.readData();

    }
}