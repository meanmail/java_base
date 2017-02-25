// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    private static final String MESSAGE_TEMPLATE = "Main.isPowerOfTwo(%d)";
    private static Method charExpression;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        charExpression = TestUtils.getMethod(mainClass,
                "isPowerOfTwo",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Boolean.TYPE, Integer.TYPE);
    }

    @Test(timeout = 8000)
    public void isPowerOfTwoSample1() throws Throwable {
        int value = 0;
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, false, TestUtils.invokeMethod(mainClass, charExpression, value));
    }

    @Test(timeout = 8000)
    public void isPowerOfTwoSample2() throws Throwable {
        int value = 1;
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, charExpression, value));
    }

    @Test(timeout = 8000)
    public void isPowerOfTwoSample3() throws Throwable {
        int value = -2;
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, charExpression, value));
    }

    @Test(timeout = 8000)
    public void isPowerOfTwoSample4() throws Throwable {
        int value = 1458;
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, false, TestUtils.invokeMethod(mainClass, charExpression, value));
    }

    @Test(timeout = 8000)
    public void isPowerOfTwoSample5() throws Throwable {
        int value = -536_870_912;
        String message = String.format(MESSAGE_TEMPLATE, value);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, charExpression, value));
    }
}