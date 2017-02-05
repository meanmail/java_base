// Don't edit this file

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000)
    public void readAsStringANSII() throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream(new byte[]{48, 49, 50, 51});
        String actual = Main.readAsString(input, StandardCharsets.US_ASCII);

        assertEquals("0123", actual);
    }

    @Test(timeout = 8000)
    public void readAsStringUTF16() throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream(new byte[]{0x04, 0x10, 0x04, 0x11});
        String actual = Main.readAsString(input, StandardCharsets.UTF_16);

        assertEquals("\u0410\u0411", actual);
    }
}