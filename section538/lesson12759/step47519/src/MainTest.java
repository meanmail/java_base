// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test
    public void booleanExpression() {
        for (int i = 0; i < 16; i++) {
            boolean a = (i % 2) == 1;
            boolean b = ((i >> 1) % 2) == 1;
            boolean c = ((i >> 2) % 2) == 1;
            boolean d = ((i >> 3) % 2) == 1;

            boolean actualValue = Main.booleanExpression(a, b, c, d);
            boolean expectedValue = Integer.bitCount(i) == 2;

            assertEquals(expectedValue, actualValue);
        }
    }
}