public class Factory {
    public IFileReader createFileReader() {
        return new JavaFileReader();
    }
}
