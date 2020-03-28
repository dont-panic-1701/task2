package du;


import java.io.File;
import java.util.List;
@SuppressWarnings("WeakerAccess")

public class Du {

    public Du() { } //what is this

    public long getSize(File path) { // possible: directory is currently modified??
        long size = 0;

        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null){ //nothing to do if path is an empty folder
                for (File file:files) {
                    size += getSize(file);
                }
            }
        }
        else {
            size += path.length();
        }

        return size;
    }

    public long[] getSizes (List<String> paths){

        long[] sizes = new long[paths.size()];

        for (int i = 0; i < paths.size(); i++) {
            File file = new File(paths.get(i));
            sizes[i] = getSize(file);
        }

        return sizes;
    }

}
