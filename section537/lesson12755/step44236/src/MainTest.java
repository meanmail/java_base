// Don't edit this file
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test
    public void mainTest() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Main.main();

        assertEquals("It's alive! It's alive!", outStream.toString());
    }
}