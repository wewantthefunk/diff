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
            String l1 = file1Lines.get(x).trim();
            for (int y = lastKnownY; y < file2Lines.size(); y++) {
            	String l2 = file2Lines.get(y).trim();
                if (l1.equals(l2)) {
                    compareLines.add(new CompareLine(x + 1, y + 1, l1, l2));
                    if (file1Lines.get(x).trim().length() > longest) 
                        longest = file1Lines.get(x).trim().length();
                    found = true;
                    lastKnownY = y;
                    if (l1.equals(""))
                    	lastKnownY++;
                    break;
                }
            }
            
            if (!found) {
            	//lastKnownY++;
            }
        }

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
