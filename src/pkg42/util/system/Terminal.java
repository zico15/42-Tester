package pkg42.util.system;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {



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
