package du;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class DuTest {
    @Test
    public void getSizes() {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File temp1 = File.createTempFile("111", ".txt");
            File example = new File(classLoader.getResource("example1.txt").getFile());
            File example2 = new File(classLoader.getResource("example2.txt").getFile());

            Du du = new Du(1024, true, false);
            Du du2 = new Du(1024, true, true);

            assertEquals(Arrays.asList("0 B", "4 KB", "5 KB"), du.getSizes(Arrays.asList(temp1, example, example2)));
            assertEquals(Arrays.asList("10 KB"), du2.getSizes(Arrays.asList(temp1, example, example2)));
        } catch (IOException ignored) {}
    }
}