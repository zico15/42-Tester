package pkg42.util.project;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import pkg42.util.FileBase;
import pkg42.util.objects.ObjectProject;
import pkg42.util.system.MensagemBox;
import pkg42.view.MainViewController;

import java.io.File;
import java.util.List;

public class ProjectController {

    private ObjectProject projectSelect;

    private void download(TextArea textArea){
       /* System.out.println("Download");
        projectSelect.getTests().forEach(g -> {
            System.out.println("Start Download");
            try {
                String line = "";
                BufferedReader reader = FileBase.execuTerminal("cd");
                System.out.println("reader: "+reader.toString());
                while((line = reader.readLine()) != null)
                    textArea.appendText(line + "\n");
            } catch (IOException ex) {
                System.out.println("(ERRO) download: " + ex.getLocalizedMessage());
            }

        });*/
    }

    void initProject(TreeView<String> treeViewProjct, DragEvent e, TextArea textArea) {
     /*   projectSelect = null;
        List<File> files = e.getDragboard().getFiles();
        // If folder droped has more than one file lets create a treeItem
        if (files.size() > 0)
        {
            TreeItem<String> item = new TreeItem<> (files.get(0).getName());
            FileBase.getFilesProject(files.get(0), item);
            MainViewController.PROJECT.values().forEach(p -> {
                System.out.println("Logo abaixo");
                System.out.println(FileBase.checkFilesProject(files.get(0), p));
                if (FileBase.checkFilesProject(files.get(0), p) > 0)
                {
                    projectSelect = p;
                  //  System.out.println("P: " + p.getName());
                }
            });
            treeViewProjct.setRoot(item);
            if (projectSelect !=  null && MensagemBox.showAlertOption("Project: " + projectSelect.getName(), "Start Download?"))
                download(textArea);
            else {
                MensagemBox.showAlertErr("Project not found!");
                // Case project not found reset the treeView
                treeViewProjct.setRoot(null);
            }
        }
        // If no files was found lets reset treeView
        else treeViewProjct.setRoot(null);*/
    }
}
