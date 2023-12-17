public class JavaDisplayView implements IView {

    // ANSI Escape Codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_LIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_LIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_LIGHT_RED = "\u001B[91m";

    public void DisplayLn(String msg) {
        writeOut(msg + "\n");
    }
    public void Display(String msg) {
        writeOut(msg);
    }
    private void writeOut(String msg) {
        System.out.print(ANSI_RESET);
        if (msg.contains(Utilities.LINE_UPD)) {
            System.out.print(ANSI_LIGHT_YELLOW);
        } else if (msg.contains(Utilities.LINE_ADD)) {
            System.out.print(ANSI_LIGHT_GREEN);
        } else if (msg.contains(Utilities.LINE_DEL)) {
            System.out.print(ANSI_LIGHT_RED);
        }
        System.out.print(msg);
        System.out.print(ANSI_RESET);
    }
}
