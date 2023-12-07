import java.util.ArrayList;
import java.util.List;

public class LCS {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: LCS <file1> <file2>");
            return;
        }

        int longest = 0;

        Factory factory = new Factory();

        IFileReader fileReader = factory.createFileReader();

        // Read files
        List<String> file1Lines = fileReader.readFile(args[0]);
        List<String> file2Lines = fileReader.readFile(args[1]);

        List<CompareLine> compareLines = new ArrayList<>();

        int lastKnownY = 0;

        for (int x = 0; x < file1Lines.size(); x++) {
            boolean found = false;
            for (int y = lastKnownY; y < file2Lines.size(); y++) {
                if (file1Lines.get(x).equals(file2Lines.get(y))) {
                    compareLines.add(new CompareLine(x + 1, y + 1, file1Lines.get(x), file2Lines.get(y)));
                    if (file1Lines.get(x).length() > longest) 
                        longest = file1Lines.get(x).length();
                    found = true;
                    lastKnownY = y;
                    break;
                }
            }

            if (!found) {
                compareLines.add(new CompareLine(x + 1, 0, file1Lines.get(x), "null"));
                if (file1Lines.get(x).length() > longest) 
                        longest = file1Lines.get(x).length();
            }
        }

        int lookingForLine = 1;
        lastKnownY = 0;
        
        for (int x = 0; x < file2Lines.size(); x++) {
            boolean found = false;
            for (int y = 0; y < compareLines.size(); y++) {
                int l2n = compareLines.get(y).getLine2Num();
                if (l2n > lookingForLine) {
                    found = true;
                    int diff = l2n - lookingForLine;
                    for (int z = 0; z < diff; z++) {
                        compareLines.add(z, new CompareLine(0, (x - z) + 1, "null", file2Lines.get(x - z)));
                        lookingForLine++;
                    }
                    break;
                } else if (l2n == lookingForLine) {
                    found = true;
                    lookingForLine++;
                    break;
                }
            }

            if (!found) {
                compareLines.add(new CompareLine(0, x + 1, "null", file2Lines.get(x)));
                lookingForLine++;
            }
        }

        lookingForLine++;

        for (int x = 0; x < compareLines.size(); x++) {
            System.out.println(compareLines.get(x).toString(longest));
        }
    }
}
