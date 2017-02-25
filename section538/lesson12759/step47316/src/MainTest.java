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
    private static Method flipBit;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        flipBit = TestUtils.getMethod(mainClass,
                "flipBit",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Integer.TYPE, Integer.TYPE, Integer.TYPE);
    }

    @Test(timeout = 8000)
    public void flipBitAllBitsInZero() throws Throwable {
        int value = 0;
        for (int i = 1; i <= 32; i++) {
            value = (int) TestUtils.invokeMethod(mainClass, flipBit, value, i);
        }

        String message = "\nint value = 0;\nfor (int i = 1; i <= 32; i++) {\n\tvalue = Main.flipBit(value, i);\n}";
        assertEquals(message, -1, value);
    }

    @Test(timeout = 8000)
    public void flipBitAllBitsInAllOne() throws Throwable {
        int value = -1;
        for (int i = 1; i <= 32; i++) {
            value = (int) TestUtils.invokeMethod(mainClass, flipBit, value, i);
        }

        String message = "\nint value = -1;\nfor (int i = 1; i <= 32; i++) {\n\tvalue = Main.flipBit(value, i);\n}";
        assertEquals(message, 0, value);
    }
}