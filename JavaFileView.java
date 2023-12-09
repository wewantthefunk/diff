import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaFileView implements IView {
    private String _filename;

    public JavaFileView(String filename) {
        _filename = filename;
    }

    public void Display(String msg) {
        File file = new File(_filename);
        try {
        FileWriter fr = new FileWriter(file, true);
        fr.write("data");
        fr.close();
        } catch (IOException ioe) {
            System.out.println("Unable to write to file");
        }
    }
}
