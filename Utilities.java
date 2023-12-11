import java.util.List;

public class Utilities {
    public static String LINE_EQUALS = "  ===  ";
    public static String LINE_ADD    = " |add| ";
    public static String LINE_DEL    = " |del| ";
    public static String LINE_UPD    = " |upd| ";

    public static String NULL_VALUE_INDICATOR = "null";

    public static String SUPPRESS_LINE_FLAG         = "--s";
    public static String SUPPRESS_LINE_VERBOSE_FLAG = "-supress";

    public static String MERGE_LINE_FLAG         = "--m";
    public static String MERGE_LINE_VERBOSE_FLAG = "-merge";

    public static String rtrim(String s) {
        if (s.trim().equals(""))
            return s;
        String n = "_" + s;
        String result = n.trim();
        return result.substring(1);
    }

    public static List<CompareLine> cleanup(List<CompareLine> list) {
        int changeCount = 1;
        boolean isChanging = false;
        String lastChangeType = LINE_EQUALS;
        for (CompareLine compareLine : list) {
            if (!compareLine.getChangeType().equals(lastChangeType) && !compareLine.getChangeType().equals(LINE_EQUALS)) {
                compareLine.setChangeSet(changeCount);
                isChanging = true;
                lastChangeType = compareLine.getChangeType();
            }
            else if (!compareLine.getChangeType().equals(LINE_EQUALS)) {
                compareLine.setChangeSet(changeCount);
                isChanging = true;
                lastChangeType = compareLine.getChangeType();
            } else if (isChanging) {
                isChanging = false;
                changeCount++;
            }
        }

        return list;
    }

    public static String pad(String s, int padLength) {
    	return pad(s, padLength, " ", true);
    }
    
    public static String pad(String s, int padLength, String padChar, boolean padRight) {
        String result = s;
        for(int x = s.length(); x < padLength; x++) {
        	if (padRight)
        		result += padChar;
        	else
        		result = padChar + result;
        }

        return result;
    }
}
