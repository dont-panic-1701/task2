package du;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SuppressWarnings("WeakerAccess")

public class Du {

    private final boolean isFormat;
    private final boolean isSum;
    private final int base;

    public Du(int base, boolean isFormat, boolean isSum) {
        this.isFormat = isFormat;
        this.isSum = isSum;
        this.base = base;
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

    private static long getSize(File path) {
        long size = 0;

        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null){
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

    public List<String> getSizes (List<File> files){
        long sum = 0;
        List<String> finalSizes = new ArrayList<String>();

        for(File file: files){
            long size = getSize(file);

            if (isSum) {
                sum += size;
            } else {
                finalSizes.add(isFormat ? getUnit(size, base) : String.valueOf(size/base));
            }
        }

        if (isSum) finalSizes.add(isFormat? getUnit(sum, base): String.valueOf(sum/base));

        return finalSizes;
    }

}
