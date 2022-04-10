package pkg42.util.system;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;


public class Terminal {


    private static void editMake(File patch)
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
