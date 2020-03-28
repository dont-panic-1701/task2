package du;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DuLauncher {



    @Option(name = "-h", usage = "Include format")
    private boolean hFormat = true;

    @Option(name = "-c", usage = "Get sum of sizes")
    private boolean cSum = true;

    @Option(name = "--si", usage = "Use base 1000")
    private boolean changeBase = true;

    @Argument(required = true, usage = "File paths")
    private List<String> paths = new ArrayList<String>();

    public static void main(String[] args) {
        new DuLauncher().launch(args);
    }


    private static String getUnit(long size, int base) {
        List<String> units = new ArrayList<String>(Arrays.asList("B", "KB", "MB", "GB"));
        int unit = 0;
        for(int i = 1; i < 4; i++){
            if (size/base == 0)
                break;
            size /= base;
            unit = i;
        }
        return size + " " + units.get(unit);
    }


    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar du.jar [-h] [-c] [--si] file1 file2 file3...");
            parser.printUsage(System.err);
            return;
        }

        Du du = new Du();
        int base = changeBase ? 1000 : 1024; //if there was no --si flag will changeBase be null?

        long[] sizes = du.getSizes(paths);
        System.out.println("Requested files sizes:");
        long sum = 0;
        for(long size: sizes){
            if (cSum) {
                sum += size;
            } else {
                System.out.println(hFormat ? getUnit(size, base) : size);
            }
        }
        if (cSum) System.out.println(hFormat? getUnit(sum, base): sum);

    }
}
