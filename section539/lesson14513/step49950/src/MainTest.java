// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000L)
    public void integrateSample1() throws Exception {
        double actual = Main.integrate(x -> 1, 0, 10);

        assertEquals(10, actual, 1e-5);
    }

    @Test(timeout = 8000L)
    public void integrateSample2() throws Exception {
        double actual = Main.integrate(x -> x, -458, 100);

        assertEquals(-99882.0004536563, actual, 1e-5);
    }
}