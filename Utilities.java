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
        for (CompareLine compareLine : list) {
            if (!compareLine.getChangeType().equals(Utilities.LINE_EQUALS)) {
                compareLine.setChangeSet(changeCount);
                isChanging = true;
            } else if (isChanging) {
                isChanging = false;
                changeCount++;
            }
        }

        return list;
    }
}
