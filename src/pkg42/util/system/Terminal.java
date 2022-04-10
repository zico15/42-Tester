package pkg42.util.system;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;


public class Terminal {


    private static void editMake(File patch)
    {
        try {
            FileReader make = new FileReader(new File(patch, "MakeFile"));
            char[] array = new char[256];

            try (FileWriter file = new FileWriter(Data.FILE_NAME)) {
                file.write("export TERM=xterm-256color");
                while (make.ready())
                {
                    make.read(array);
                    file.write(array);
                }
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    private static boolean appendText(final InputStreamReader inputStreamReader, TextArea textArea) {
        try {
            final char[] buf = new char[256];
            final int read = inputStreamReader.read(buf);
            if (read < 1) {
                return false;
            }
            Platform.runLater(() -> {
                textArea.appendText(new String(buf));
            });
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void exec(String command, File patch, TextArea textArea) {
        Thread a = new Thread() {
            @Override
            public void run() {
                try {
                    if ("make".equalsIgnoreCase(command))
                        editMake(patch);
                    ProcessBuilder builder = new ProcessBuilder(command).directory(patch);
                    Process proc = builder.start();
                    final InputStreamReader inputStreamReader = new InputStreamReader(proc.getInputStream());
                    while (appendText(inputStreamReader, textArea)) {
                        ;
                    }
                    proc.waitFor();
                    proc.destroy();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        a.start();
    }
}
