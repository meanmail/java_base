// Don't edit this file
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void getCallerClassAndMethodNameNotNull() throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Main.main();

        String output = new String(outStream.toByteArray());
        Scanner scanner = new Scanner(output);

        assertTrue(scanner.hasNextLine());
        assertEquals("MainTest#getCallerClassAndMethodNameNotNull", scanner.nextLine());
        assertTrue(scanner.hasNextLine());
        assertEquals("Main#main", scanner.nextLine());
    }
}