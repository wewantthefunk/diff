public class LCS {

    public static void main(String[] args) {
        IFactory factory = new Factory();
        Diff diff = new Diff();

        try {
            diff.DoDiff(args, factory);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
