import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Merge {
    private IView _view;
    private List<CompareLine> _compareLines;
    private Factory _factory;
    private String _outfile;

    public Merge(IView view, List<CompareLine> compareLines, Factory factory, String outFile) {
        _view = view;
        _compareLines = compareLines;
        _factory = factory;
        _outfile = outFile;
    }

    public void DoMerge() {
        Scanner scan = new Scanner(System.in);

        _view.Display("Enter comma delimited list of changesets to include or 'all': ");
        String included = scan.nextLine();

        scan.close();

        MergeFiles(included);
    }

    private void MergeFiles(String include) {
        IView outFile = _factory.createFileView(_outfile);

        List<Integer> includedSets = cleanupList(Arrays.asList(include.split(",")));

        for(int x = 0; x < _compareLines.size(); x++) {
            if ((includedSets.contains(_compareLines.get(x).getChangeSet())   || 
                  includedSets.contains(-999)                                 || 
                  _compareLines.get(x).getChangeSet() == 0)) {
                if (!_compareLines.get(x).getLine1().equals(Utilities.NULL_VALUE_INDICATOR))                                                    
                    outFile.DisplayLn(_compareLines.get(x).getLine1());                            
            } else {
                if (!_compareLines.get(x).getLine2().equals(Utilities.NULL_VALUE_INDICATOR))
                    outFile.DisplayLn(_compareLines.get(x).getLine2()); 
            }
        }

        IFileReader fileReader = _factory.createFileReader();

        List<String> merged = fileReader.readFile(_outfile);

        _view.DisplayLn("");
        _view.DisplayLn("Merge file results: ");
        _view.DisplayLn("");
        for (int x = 0; x < merged.size(); x++) {
            _view.DisplayLn(merged.get(x));
        }
    }

    private List<Integer> cleanupList(List<String> list) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).trim().equals("")){
                continue;
            }
            else if (list.get(x).trim().toLowerCase().equals("all")) {
                result.clear();
                result.add(-999);
                break;
            }
            else if (list.get(x).trim().toLowerCase().equals("none")) {
                result.clear();
                result.add(-999);
                break;
            } else if (Integer.parseInt(list.get(x).trim().toLowerCase()) > -1) {
                result.add(Integer.parseInt(list.get(x).trim().toLowerCase()));
            }
        }

        return result;
    }
}
