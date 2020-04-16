package du;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DuLauncher {



    @Option(name = "-h", usage = "Include format")
    private boolean hFormat = true;

    @Option(name = "-c", usage = "Get sum of sizes")
    private boolean cSum = true;

    @Option(name = "--si", usage = "Use base 1000")
    private boolean changeBase = true;

    @Argument(required = true, usage = "File paths")
    private List<File> paths = new ArrayList<File>();

    public static void main(String[] args) {
        new DuLauncher().launch(args);
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

        System.out.println("Requested file sizes:");
        Du du = new Du(changeBase ? 1000 : 1024, hFormat, cSum);
        for (String size:du.getSizes(paths) ) {
            System.out.println(size);
        }
    }
}
