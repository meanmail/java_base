// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.DoubleUnaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    private static final String MESSAGE_TEMPLATE = "Main.integrate(%s, %f, %f);";
    private static Method integrate;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        integrate = TestUtils.getMethod(mainClass,
                "integrate",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Double.TYPE,
                DoubleUnaryOperator.class, Double.TYPE, Double.TYPE);
    }

    @Test(timeout = 8000L)
    public void integrateSample1() throws Throwable {
        integrate(x -> 1, 0, 10, 10, "x -> 1");
    }

    @Test(timeout = 8000L)
    public void integrateSample2() throws Throwable {
        integrate(x -> x, -458, 100, -99882.0004536563, "x -> x");
    }

    private void integrate(DoubleUnaryOperator operator, double a, double b, double expected, String operatorStr) throws Throwable {
        double actual = (double) TestUtils.invokeMethod(mainClass, integrate, operator, a, b);

        String message = String.format(MESSAGE_TEMPLATE, operatorStr, a, b);
        assertEquals(message, expected, actual, 1e-5);
    }
}