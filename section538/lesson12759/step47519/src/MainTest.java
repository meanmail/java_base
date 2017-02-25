// Don't edit this file

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test(timeout = 2000)
    public void booleanExpression() throws Throwable {
        Class<?> mainClass = TestUtils.getUserClass("Main");
        for (int i = 0; i < 16; i++) {
            boolean a = (i % 2) == 1;
            boolean b = ((i >> 1) % 2) == 1;
            boolean c = ((i >> 2) % 2) == 1;
            boolean d = ((i >> 3) % 2) == 1;

            Class<Boolean> booleanClass = Boolean.TYPE;
            Method booleanExpression = TestUtils.getMethod(mainClass,
                    "booleanExpression",
                    new int[]{Modifier.PUBLIC | Modifier.STATIC},
                    booleanClass,
                    booleanClass, booleanClass, booleanClass, booleanClass);

            boolean actualValue = (boolean) TestUtils.invokeMethod(mainClass, booleanExpression, a, b, c, d);
            boolean expectedValue = Integer.bitCount(i) == 2;

            String message = String.format("Main.booleanExpression(%b, %b, %b, %b)", a, b, c, d);
            assertEquals(message, expectedValue, actualValue);
        }
    }
}