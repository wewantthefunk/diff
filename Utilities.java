public class Utilities {
    public static String rtrim(String s) {
        if (s.trim().equals(""))
            return s;
        String n = "_" + s;
        String result = n.trim();
        return result.substring(1);
    }
}
