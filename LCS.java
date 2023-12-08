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
        List<String> revised = fileReader.readFile(args[0]);
        List<String> original = fileReader.readFile(args[1]);

        List<String> shortest = revised;

        int maxLength = original.size();

        if (original.size() < revised.size()) {
            shortest = original;
            maxLength = revised.size();
        }

        List<CompareLine> compareLines = new ArrayList<>();

        int lastMatchShortest = -1;

        for (int i = 0; i < maxLength; i++) {
            String originalLine = i < original.size() ? original.get(i).trim() : null;
            String revisedLine = i < revised.size() ? revised.get(i).trim() : null;

            if (revisedLine != null)
                if (revisedLine.length() > longest)
                    longest = revisedLine.length();

            if (originalLine == null) {
                // revised line
                compareLines.add(new CompareLine(i + 1, i + 1, revisedLine, "null"));
                //compareLines.add("Added: " + revisedLine);
            } else if (revisedLine == null) {
                // original line
                compareLines.add(new CompareLine(i + 1, i + 1, "null", originalLine));
                //compareLines.add("Deleted: " + originalLine);
            } else if (!originalLine.equals(revisedLine)) {
                String ol = "null";
                if (lastMatchShortest == -1) {
                    lastMatchShortest = i;
                    ol = originalLine;
                }
                compareLines.add(new CompareLine(i + 1, i + 1, revisedLine, ol));
                //compareLines.add("Modified: " + "From [" + originalLine + "] to [" + revisedLine + "]");
            } else {
                compareLines.add(new CompareLine(i + 1, i + 1, revisedLine, originalLine));
            }
        }

        int compareCount = compareLines.size() - 1;
        for (int i = shortest.size() - 1; i > lastMatchShortest; i--) {
            if (shortest.get(i).trim().equals(compareLines.get(compareCount).getNotNullLine())) {
                compareLines.get(compareCount).setNotNullLine(shortest.get(i));
                compareCount--;
            };

            compareCount--;
        }

        // print out results
        for (int x = 0; x < compareLines.size(); x++) {
            System.out.println(compareLines.get(x).toString(longest, countDigits(compareLines.size())));
        }
    }
    
    private static int countDigits(int number) {
    	if (number == 0)
    		return 1;
    	
    	int count = 0;
    	
    	while (number != 0) {
    		number /= 10;
    		count++;
    	}
    	
    	return count;
    }
}
