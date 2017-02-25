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
    private static final String MESSAGE_TEMPLATE = "Main.doubleExpression(%f, %f, %f)";
    private static Method doubleExpression;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        doubleExpression = TestUtils.getMethod(mainClass,
                "doubleExpression",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Boolean.TYPE, Double.TYPE, Double.TYPE, Double.TYPE);
    }

    @Test(timeout = 2000)
    public void doubleExpressionSamples() throws Throwable {
        String message = String.format(MESSAGE_TEMPLATE, 0.1, 0.2, 0.3);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, doubleExpression, 0.1, 0.2, 0.3));
    }

    @Test(timeout = 2000)
    public void doubleExpressionFalse1() throws Throwable {
        String message = String.format(MESSAGE_TEMPLATE, 5.1006, 4.2, 9.3);
        assertEquals(message, false, TestUtils.invokeMethod(mainClass, doubleExpression, 5.1006, 4.2, 9.3));
    }

    @Test(timeout = 2000)
    public void doubleExpressionFalse2() throws Throwable {
        String message = String.format(MESSAGE_TEMPLATE, 90500.0, -100500.0, -9000.00025);
        assertEquals(message, false, TestUtils.invokeMethod(mainClass, doubleExpression, 90500, -100500, -9000.00025));
    }

    @Test(timeout = 2000)
    public void doubleExpressionTrue1() throws Throwable {
        String message = String.format(MESSAGE_TEMPLATE, 5.0, 0.0001, 5.0);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, doubleExpression, 5.0, 0.0001, 5.0));
    }

    @Test(timeout = 2000)
    public void doubleExpressionTrue2() throws Throwable {
        String message = String.format(MESSAGE_TEMPLATE, -5.0, 0.0001, -5.0);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, doubleExpression, -5.0, 0.0001, -5.0));
    }
}