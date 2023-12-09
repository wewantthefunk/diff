import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Merge {
    private IView _view;
    private List<CompareLine> _compareLines;
    private Factory _factory;

    public Merge(IView view, List<CompareLine> compareLines, Factory factory) {
        _view = view;
        _compareLines = compareLines;
        _factory = factory;
    }

    public void DoMerge() {
        Scanner scan = new Scanner(System.in);

        _view.Display("Enter comma delimited list of changesets to include or 'all': ");
        String included = scan.nextLine();

        _view.Display("Enter comma delimited list of changesets to exclude or 'none': ");
        String excluded = scan.nextLine();

        scan.close();

        MergeFiles(included, excluded);
    }

    private void MergeFiles(String include, String exclude) {
        IView outFile = _factory.createFileView("temp.out");

        List<Integer> includedSets = cleanupList(Arrays.asList(include.split(",")));
        List<Integer> excludedSets = cleanupList(Arrays.asList(exclude.split(",")));

        for(int x = 0; x < _compareLines.size(); x++) {
            if ((includedSets.contains(_compareLines.get(x).getChangeSet())   || 
                  includedSets.contains(-999)                                 || 
                  _compareLines.get(x).getChangeSet() == 0)                  &&
                (_compareLines.get(x).getChangeSet() == 0                     ||
                  excludedSets.contains(-999)                                 ||
                  !excludedSets.contains(_compareLines.get(x).getChangeSet())
                                                  )) {
                outFile.DisplayLn(_compareLines.get(x).getLine1());
            }
        }
    }

    private List<Integer> cleanupList(List<String> list) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).trim().toLowerCase().equals("all")) {
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
