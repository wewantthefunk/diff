public interface IFactory {
    IFileReader createFileReader();

    IView createDisplayView();

    IView createFileView(String filename);
}
