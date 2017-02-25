// Don't edit this file

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test(timeout = 8000)
    public void sample1() throws Throwable {
        try (ByteArrayInputStream in = new ByteArrayInputStream("1 2 3 4 5 6 7".getBytes())) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                TestUtils.runMain(in, new PrintStream(out));

                assertEquals("6 4 2", out.toString().trim());
            }
        }
    }
}