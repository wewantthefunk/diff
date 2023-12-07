public class CompareLine {
    private int _line1num;
    private int _line2num;
    private String _line1;
    private String _line2;

    public CompareLine(int x, int y, String line1, String line2) {
        _line1num = x;
        _line2num = y;
        _line1 = line1;
        _line2 = line2;
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
        String result = s;
        for(int x = s.length(); x < padLength; x++) {
            result += " ";
        }

        return result;
    }

    public String toString(int padLength) {
        if (getLine1().equals(getLine2())) {
        //if (getLine1Num() == getLine2Num()) {
            return getLine2Num() + ": " + pad(getLine1(), padLength) + "  ==   " + getLine2();
        }

        String l1 = getLine1();
        if (l1.equals("null"))
            l1 = "";

        if (getLine1().equals("null")) {
            String l2 = getLine2();
            if (l2.equals("null")) {
                l2 = "";
            }
            return getLine2Num() + ": " + pad(l1, padLength) + " <del>  " + l2;
        }

        return getLine1Num() + ": " + pad(l1, padLength) + " <add>  ";
    }
}
