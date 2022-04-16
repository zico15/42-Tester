package pkg42.util.system;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;


public class Terminal {


    public static void editMake(File patch)
    {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(new File(patch, "Makefile")));

            for (String line = buffRead.readLine(); line != null; line = buffRead.readLine()) {
                lines.add(line);
            }
            try (FileWriter file = new FileWriter(new File(patch, "Makefile"))) {
                file.write("export TERM=xterm-256color\n\n");
                lines.forEach(s ->
                {
                    try {
                        file.write(s + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                file.flush();
                buffRead.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.clear();

    }

    private static boolean appendText(final InputStreamReader inputStreamReader, TextArea textArea) {
            try {
            final char[] buf = new char[256];
            final int read = inputStreamReader.read(buf);
            if (read < 1) {
                return false;
            }

            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean app(InputStreamReader is, TextArea textArea)
    {
        String line = null;
        try {

            Reader reader = is;
            BufferedReader buffer = new BufferedReader(reader);
            line = buffer.readLine();
            //Platform.runLater(() -> {
                if (textArea != null)
                    textArea.appendText(line);
                else
                    System.out.println(line);
            //});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (line != null);
    }

    public static void exec(File patch, TextArea textArea, String... command) {
        Thread a = new Thread() {
            @Override
            public void run() {
                try {
                    if (command != null && "make".equalsIgnoreCase(command[0]))
                        editMake(patch);
                    ProcessBuilder builder = new ProcessBuilder(command).directory(patch);
                    Process proc = builder.start();
                    InputStreamReader inputStreamReader = new InputStreamReader(proc.getInputStream());
                    while (app(inputStreamReader, textArea)) {
                        ;
                    }
                   inputStreamReader = new InputStreamReader(proc.getErrorStream());
                    while (app(inputStreamReader, textArea)) {
                        ;
                    }
                    proc.waitFor();
                    proc.destroy();
                    System.out.println("final -> exec");
                } catch (IOException e) {
                    System.out.println("ERROR -> exec: " + e.getLocalizedMessage());
                } catch (InterruptedException e) {
                    System.out.println("ERROR -> exec: " + e.getLocalizedMessage());
                }
            }
        };
        a.start();
    }
}
