import java.util.List;

public class Utilities {
    public static String LINE_EQUALS = "  ===  ";
    public static String LINE_ADD    = " |add| ";
    public static String LINE_DEL    = " |del| ";
    public static String LINE_UPD    = " |upd| ";

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
}
