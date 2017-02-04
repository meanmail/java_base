import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public abstract class AbstractMainTest {
    private static ByteArrayOutputStream outStream;
    private final String input;
    private final String output;

    public AbstractMainTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @AfterClass
    public static void afterClass() throws IOException {
        outStream.close();
    }

    @Test
    public void mainSample() throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        outStream.reset();

        TestUtils.runMain(getClass().getClassLoader());

        String actual = outStream.toString().replace(",", ".");
        String message = String.format("Expected %s but was %s", output, actual);
        assertEquals(message, output, actual);
    }
}