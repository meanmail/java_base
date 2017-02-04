import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * @author meanmail
 */
public class MainTest {

    @Test
    public void testClassStructure() {
        Class<?> pairClass = TestUtils.getUserClass("Pair");

        assertEquals("Class Pair should be package-private", 0, pairClass.getModifiers());

        Method getFirst = TestUtils.getMethod(pairClass, "getFirst", Modifier.PUBLIC);
        Method getSecond = TestUtils.getMethod(pairClass, "getSecond", Modifier.PUBLIC);
        TestUtils.getMethod(pairClass, "equals", Modifier.PUBLIC, Object.class);
        TestUtils.getMethod(pairClass, "hashCode", Modifier.PUBLIC);
        Method of = TestUtils.getMethod(pairClass, "of", Modifier.PUBLIC | Modifier.STATIC, Object.class, Object.class);
        TestUtils.getConstructor(pairClass, Modifier.PRIVATE);

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