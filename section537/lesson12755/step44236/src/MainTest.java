// Don't edit this file

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test
    public void mainTest() throws IOException {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(outStream));

            TestUtils.runMain();

            assertEquals("It's alive! It's alive!", outStream.toString());
        }
    }
}