import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaFileView implements IView {
    private String _filename;

    public JavaFileView(String filename) {
        _filename = filename;

        File myObj = new File(_filename); 
        if (myObj.delete()) { 
        System.out.println("Deleted the file: " + myObj.getName());
        } else {
        System.out.println("Failed to delete the file.");
        } 
    }

    public void DisplayLn(String msg) {
        Display(msg + "\n");
    }

    public void Display(String msg) {
        File file = new File(_filename);
        try {
        FileWriter fr = new FileWriter(file, true);
        fr.write(msg);
        fr.close();
        } catch (IOException ioe) {
            System.out.println("Unable to write to file");
        }
    }
}
