public class CompareLine {
    private int _line1num;
    private int _line2num;
    private String _line1;
    private String _line2;
    
    private static String NULL_VALUE_INDICATOR = "null";

    public CompareLine(int x, int y, String line1, String line2) {
        _line1num = x;
        _line2num = y;
        _line1 = line1.trim();
        _line2 = line2.trim();
    }

    public int getLine1Num() {
        return _line1num;
    }

    public int getLine2Num() {
        return _line2num;
    }

    public String getLine1() {
        return _line1;
    }

    public String getLine2() {
        return _line2;
    }

    private String pad(String s, int padLength) {
    	return pad(s, padLength, " ", true);
    }
    
    private String pad(String s, int padLength, String padChar, boolean padRight) {
        String result = s;
        for(int x = s.length(); x < padLength; x++) {
        	if (padRight)
        		result += padChar;
        	else
        		result = padChar + result;
        }

        return result;
    }

    public String toString(int padLength, int length) {
        if (getLine1().equals(getLine2())) {
            return pad(Integer.toString(getLine1Num()), length, "0", false) + ": " + pad(getLine1(), padLength) + "  ==   " + pad(Integer.toString(getLine2Num()), length, "0", false) + ": " + getLine2();
        }

        String l1 = getLine1();
        if (l1.equals(NULL_VALUE_INDICATOR))
            l1 = "";

        if (getLine1().equals(NULL_VALUE_INDICATOR)) {
            String l2 = getLine2();
            if (l2.equals(NULL_VALUE_INDICATOR)) {
                l2 = "";
            }
            return pad(Integer.toString(getLine2Num()), length, "0", false) + ": " + pad(l1, padLength) + " <del> " + l2;
        }

        return pad(Integer.toString(getLine1Num()), length, "0", false) + ": " + pad(l1, padLength) + " <add>";
    }
}
