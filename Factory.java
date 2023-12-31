public class Factory implements IFactory {
    public IFileReader createFileReader() {
        return new JavaFileReader();
    }

    public IView createDisplayView() {
        return new JavaDisplayView();
    }

    public IView createFileView(String filename) {
        return new JavaFileView(filename);
    }
}
