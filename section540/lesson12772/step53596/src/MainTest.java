// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
public class MainTest {
    private static Method sqrt;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        sqrt = TestUtils.getMethod(mainClass,
                "sqrt",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Double.TYPE,
                Double.TYPE);
    }

    @Test(timeout = 8000)
    public void sqrtException() throws Exception {
        try {
            Main.sqrt(-100);
        } catch (IllegalArgumentException e) {
            String message = "Main.sqrt(-100);\nIllegalArgumentException.getMessage()";
            assertEquals(message, "Expected non-negative number, got -100.0", e.getMessage());
            return;
        }

        fail("Main.sqrt(-100). Expected IllegalArgumentException");
    }

    @Test(timeout = 8000)
    public void sqrtNotException() throws Throwable {
        double value = (double) TestUtils.invokeMethod(mainClass, sqrt, 100);

        assertEquals(10.0, value, 0);
    }

}