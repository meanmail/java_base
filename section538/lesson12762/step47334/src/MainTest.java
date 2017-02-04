// Don't edit this file

import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000L)
    public void factorialSample1() throws Exception {
        BigInteger actual = Main.factorial(1);
        assertEquals(ONE, actual);
    }

    @Test(timeout = 8000L)
    public void factorialSample2() throws Exception {
        BigInteger actual = Main.factorial(3);
        assertEquals(BigInteger.valueOf(6), actual);
    }

    @Test(timeout = 8000L)
    public void factorialSample3() throws Exception {
        BigInteger actual = Main.factorial(20);
        assertEquals(BigInteger.valueOf(2432902008176640000L), actual);
    }
}