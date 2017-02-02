// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void flipBitAllBitsInZero() throws Exception {
        int value = 0;
        for (int i = 1; i <= 32; i++) {
            value = Main.flipBit(value, i);
        }

        assertEquals(-1, value);
    }

    @Test
    public void flipBitAllBitsInAllOne() throws Exception {
        int value = -1;
        for (int i = 1; i <= 32; i++) {
            value = Main.flipBit(value, i);
        }

        assertEquals(0, value);
    }
}