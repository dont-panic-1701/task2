package du;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class DuTest {
    @Test
    public void getSizes() throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File temp1 = File.createTempFile("111", ".txt");
        File example = new File(classLoader.getResource("example1.txt").getFile());
        File example2 = new File(classLoader.getResource("example2.txt").getFile());

        Du du = new Du(1024, true, false);
        Du du2 = new Du(1024, false, true);
        Du du3 = new Du(1000, false, false);

        assertEquals(Arrays.asList("0 B", "4 KB", "5 KB"), du.getSizes(Arrays.asList(temp1, example, example2)));     //isFormat test
        assertEquals(Arrays.asList("10"), du2.getSizes(Arrays.asList(temp1, example, example2)));                     //isSum test
        assertEquals(Arrays.asList("0", "4", "6"), du3.getSizes(Arrays.asList(temp1, example, example2)));            //base 1000 test

    }
}