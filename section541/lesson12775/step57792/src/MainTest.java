// Don't edit this file

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * @author meanmail
 */
public class MainTest {

    @Test(timeout = 8000)
    public void testClassStructure() throws Throwable {
        Class<?> pairClass = TestUtils.getUserClass("Pair");

        assertEquals("Class Pair should be package-private", 0, pairClass.getModifiers());

        Method getFirst = TestUtils.getMethod(pairClass,
                "getFirst",
                new int[]{Modifier.PUBLIC},
                Object.class);

        Method getSecond = TestUtils.getMethod(pairClass,
                "getSecond",
                new int[]{Modifier.PUBLIC},
                Object.class);

        TestUtils.getMethod(pairClass,
                "equals",
                new int[]{Modifier.PUBLIC},
                Boolean.TYPE, Object.class);

        TestUtils.getMethod(pairClass,
                "hashCode",
                new int[]{Modifier.PUBLIC},
                Integer.TYPE);

        Method of = TestUtils.getMethod(pairClass,
                "of",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                pairClass,
                Object.class,
                Object.class);

        TestUtils.getConstructor(pairClass, new int[]{Modifier.PRIVATE, 0}, Object.class, Object.class);

        Object pair = TestUtils.invokeMethod(pairClass, of, 1, "hello");

        assertNotNull("Pair.of(1, \"hello\") return null", pair);

        Object result = TestUtils.invokeMethod(pair, getFirst);

        String message = String.format("getFirst(): expected 1, but was return %s", result);
        assertEquals(message, 1, result);

        result = TestUtils.invokeMethod(pair, getSecond);

        message = String.format("getSecond(): expected \"hello\", but was return %s", result);
        assertEquals(message, "hello", result);

        Object pair2 = TestUtils.invokeMethod(pairClass, of, 1, "hello");
        assertNotNull("Pair.of(1, \"hello\") return null", pair);
        assertTrue("pair.equals(pair2) should be true", pair.equals(pair2));
        assertTrue("pair.hashCode() == pair2.hashCode()", pair.hashCode() == pair2.hashCode());
    }
}