// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void sqrtException() throws Exception {
        try {
            Main.sqrt(-100);
        } catch (IllegalArgumentException e) {
            assertEquals("Expected non-negative number, got -100.0", e.getMessage());
            return;
        }

        fail("Called sqrt(-100). Expected IllegalArgumentException");
    }

    @Test
    public void sqrtNotException() throws Exception {
        double value = Main.sqrt(100);

        assertEquals(10.0, value, 0);
    }

}