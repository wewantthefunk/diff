public class CompareLine {
    private int _line1num;
    private int _line2num;
    private String _line1;
    private String _line2;
    private int _changeset;
    
    private static String NULL_VALUE_INDICATOR = "null";

    public CompareLine(int x, int y, String line1, String line2) {
        _line1num = x;
        _line2num = y;
        _line1 = Utilities.rtrim(line1);
        _line2 = Utilities.rtrim(line2);
        _changeset = 0;
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

    public void setLine1(String value) {
        _line1 = Utilities.rtrim(value);
    }

    public String getLine2() {
        return _line2;
    }

    public void setLine2(String value) {
        _line2 = Utilities.rtrim(value);
    }

    public int getChangeSet() {
        return _changeset;
    }

    public void setChangeSet(int value) {
        _changeset = value;
    }

    public String getChangeType() {
        String l1 = getLine1();
        /*if (l1.equals(NULL_VALUE_INDICATOR))
            l1 = "";*/

        String l2 = getLine2();
        /*if (l2.equals(NULL_VALUE_INDICATOR)) {
            l2 = "";
        }*/

        return getChangeType(l1, l2);
    }

    public String getChangeType(String l1, String l2) {

        if (l1.equals(l2)) {
            return Utilities.LINE_EQUALS;
        }

        if (getLine1().equals(NULL_VALUE_INDICATOR)) {            
            return " |del| ";
        }

        if (getLine2().equals(NULL_VALUE_INDICATOR)) {
            return " |add| ";
        }

        return " |upd| ";
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
        String l1 = getLine1();
        String l2 = getLine2();

        String ct = getChangeType(l1, l2);
        if (l1.equals(NULL_VALUE_INDICATOR))
            l1 = "";

        if (l2.equals(NULL_VALUE_INDICATOR)) {
            l2 = "";
        }

        String changeSet = getChangeSet() > 0 ? pad(Integer.toString(getChangeSet()), 5, "0", false) : "     ";

        return changeSet + " " +  pad(Integer.toString(getLine1Num()), length, "0", false) + ": " + pad(l1, padLength) + ct + l2;
    }
}
