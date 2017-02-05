// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    private static final String MESSAGE_TEMPLATE = "Main.factorial(%d)";
    private static Method factorial;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        factorial = TestUtils.getMethod(mainClass,
                "factorial",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                BigInteger.class,
                Integer.TYPE);
    }

    @Test(timeout = 8000L)
    public void factorialSample1() throws Throwable {
        int value = 1;
        BigInteger actual = (BigInteger) TestUtils.invokeMethod(mainClass, factorial, value);
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, ONE, actual);
    }

    @Test(timeout = 8000L)
    public void factorialSample2() throws Throwable {
        int value = 3;
        BigInteger actual = (BigInteger) TestUtils.invokeMethod(mainClass, factorial, value);
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, BigInteger.valueOf(6), actual);
    }

    @Test(timeout = 8000L)
    public void factorialSample3() throws Throwable {
        int value = 20;
        BigInteger actual = (BigInteger) TestUtils.invokeMethod(mainClass, factorial, value);
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, BigInteger.valueOf(2432902008176640000L), actual);
    }
}