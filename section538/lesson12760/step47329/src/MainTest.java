// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void isPowerOfTwoSample1() throws Exception {
        assertFalse(Main.isPowerOfTwo(0));
    }

    @Test
    public void isPowerOfTwoSample2() throws Exception {
        assertTrue(Main.isPowerOfTwo(1));
    }

    @Test
    public void isPowerOfTwoSample3() throws Exception {
        assertTrue(Main.isPowerOfTwo(-2));
    }

    @Test
    public void isPowerOfTwoSample4() throws Exception {
        assertFalse(Main.isPowerOfTwo(1458));
    }

    @Test
    public void isPowerOfTwoSample5() throws Exception {
        assertTrue(Main.isPowerOfTwo(-536_870_912));
    }
}