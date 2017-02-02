// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void sample1() {
        String string = "I'm string";
        Main.AsciiCharSequence asciiCharSequence = new Main.AsciiCharSequence(string.getBytes());

        assertEquals("AsciiCharSequence.length()", string.length(), asciiCharSequence.length());
        assertEquals(string, asciiCharSequence.toString());

        for (int i = 0; i < string.length(); i++) {
            assertEquals(String.format("AsciiCharSequence.charAt(%d)", i), string.charAt(i), asciiCharSequence.charAt(i));
        }

        String substring = asciiCharSequence.subSequence(4, 8).toString();
        assertEquals("AsciiCharSequence.substring(4, 8)", string.substring(4, 8), substring);
    }
}