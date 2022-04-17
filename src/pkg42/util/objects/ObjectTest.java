/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.objects;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 *
 * @author zequi
 */
public class ObjectTest implements Serializable {
    
    private static final long serialVersionUID = 1;

    public String author;
    public String name;
    public String git;
    public String cmdStart;
    public int    qtdChecks;
    public String KeywordSuccess;
    public String KeywordFail;
    public String type;

    // GUI
    public ProgressBar progress;
    public double v;
    public Label text_ok;
    public Label text_ko;
    public Label text_segm;
    public TextArea textArea = new TextArea();

    /**
     * Constructor (ObjectTest Class)
     */
    public ObjectTest()
    {
        type = "NULL";
    }
    
    public ObjectTest(JSONObject employeeObject){

        //Get employee first author
        author = (String) employeeObject.get("author");

        //Get employee first tester
        name = (String) employeeObject.get("name");

        //Get employee last git
        git = (String) employeeObject.get("git");

        //Get employee first cmdStart
        cmdStart = (String) employeeObject.get("cmdStart");

        //Get employee last qtdChecks
        qtdChecks = (int) ((long) employeeObject.get("qtdChecks"));

        //Get employee website KeywordSuccess
        KeywordSuccess = (String) employeeObject.get("KeywordSuccess");

        //Get employee website KeywordFail
        KeywordFail = (String) employeeObject.get("KeywordFail");

        //Get employee website type
        type = (String) employeeObject.get("type");
    }

    public void updateTexts(String s) {
        Platform.runLater(() -> {
            if (s != null && !s.isEmpty()){
                int ok = Integer.valueOf(text_ok.getText());
                int ko = Integer.valueOf(text_ko.getText());
                int segm = Integer.valueOf(text_segm.getText());

                ok += s.split(KeywordSuccess).length - 1;
                ko += s.split(KeywordFail).length - 1;
                //System.out.println("OK: " + ok + " KO: " + ko);
                text_ok.setText(String.valueOf(ok));
                text_ko.setText(String.valueOf(ko));
                text_segm.setText(String.valueOf(segm));
                textArea.appendText(s + "\n");
            }
            progress.setProgress(progress.getProgress() + v);
        });
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
