import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diff {
    public void DoDiff(String[] args, IFactory factory) throws Exception {
        IFileReader fileReader = factory.createFileReader();
        IView view = factory.createDisplayView();

        if (args.length < 2) {
            view.DisplayLn("Usage: LCS <revised> <original> [options]");
            view.DisplayLn("   Options:");
            view.DisplayLn("    -help, --h - help information");
            view.DisplayLn("    " + Utilities.SUPPRESS_LINE_VERBOSE_FLAG + ", " + Utilities.SUPPRESS_LINE_FLAG + " - suppress lines that match");
            view.DisplayLn("    " + Utilities.MERGE_LINE_VERBOSE_FLAG + ", " + Utilities.MERGE_LINE_FLAG + " - merge the files after diff");
            return;
        }

        List<String> arguments = Arrays.asList(args);

        String mergeOutFile = "temp.out";

        if (arguments.contains(Utilities.MERGE_OUT_LINE_FLAG) || arguments.contains(Utilities.MERGE_OUT_LINE_VERBOSE_FLAG)) {
        	int index = arguments.indexOf(Utilities.MERGE_OUT_LINE_FLAG);

            if (index < 0) {
                index = arguments.indexOf(Utilities.MERGE_OUT_LINE_VERBOSE_FLAG);
            }

            if (index > 1) {
                mergeOutFile = arguments.get(index + 1);
            }
        }

        int longest = 0;

        // Read files
        List<String> revised = fileReader.readFile(arguments.get(0));
        List<String> original = fileReader.readFile(arguments.get(1));

        List<String> shortestList = revised;
        List<String> longestList = original;

        int maxLength = original.size();

        boolean originalShortest = false;

        if (original.size() < revised.size()) {
            shortestList = original;
            longestList = revised;
            maxLength = revised.size();
            originalShortest = true;
        }

        List<CompareLine> compareLines = new ArrayList<>();

        for (int x = 0; x < maxLength; x++) {
            String l1 = Utilities.NULL_VALUE_INDICATOR;
            String l2 = Utilities.NULL_VALUE_INDICATOR;

            if (originalShortest) {
                l1 = Utilities.rtrim(longestList.get(x));
            } else {
                l2 = Utilities.rtrim(longestList.get(x));
            }

            if (longest < l1.length())
                longest = l1.length();

            compareLines.add(new CompareLine(x + 1, x + 1, l1, l2));
        }

        int longestIndex = 0;
        boolean found = false;
        for (int x = 0; x < shortestList.size(); x++) {
            found = false;
            for (int y = longestIndex; y < maxLength; y++) {
                if (Utilities.rtrim(longestList.get(y)).equals(Utilities.rtrim(shortestList.get(x)))) {                    
                    found = true;
                    String l1 = longestList.get(y);
                    compareLines.get(y).setLine1(l1);
                    compareLines.get(y).setLine2(shortestList.get(x));
                    if (longest < Utilities.rtrim(longestList.get(longestIndex)).length())
                        longest = Utilities.rtrim(longestList.get(longestIndex)).length();
                    longestIndex = y + 1;
                    break;
                }
            }

            if (!found) {
                if (longest < Utilities.rtrim(longestList.get(longestIndex)).length())
                    longest = Utilities.rtrim(longestList.get(longestIndex)).length();
                if (!originalShortest) {
                    String l1 = shortestList.get(x);
                    compareLines.get(longestIndex).setLine2(longestList.get(longestIndex));
                    compareLines.get(longestIndex).setLine1(l1);
                } else {
                    String l1 = longestList.get(longestIndex);
                    compareLines.get(longestIndex).setLine1(l1);
                    compareLines.get(longestIndex).setLine2(shortestList.get(x));
                }
                longestIndex++;
            }
        }

        compareLines = Utilities.cleanup(compareLines);
        
        // print out results
        boolean showLine = true;
        view.DisplayLn(Utilities.pad("", 7) + Utilities.pad("", countDigits(compareLines.size()) + 1) + arguments.get(0) + Utilities.pad("", longest - arguments.get(0).length()) + "       " + arguments.get(1));
        for (int x = 0; x < compareLines.size(); x++) {
            if (arguments.contains(Utilities.SUPPRESS_LINE_FLAG) || arguments.contains(Utilities.SUPPRESS_LINE_VERBOSE_FLAG)) {
                showLine = false;
                if (compareLines.get(x).getChangeSet() > 0) {
                    showLine = true;
                }
            }

            if (showLine)
                view.DisplayLn(compareLines.get(x).toString(longest, countDigits(compareLines.size())));
        }

        if (arguments.contains(Utilities.MERGE_LINE_FLAG) || arguments.contains(Utilities.MERGE_LINE_VERBOSE_FLAG)) {
            Merge merge = new Merge(view, compareLines, factory, mergeOutFile);
            merge.DoMerge();
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
