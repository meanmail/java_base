// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author meanmail
 */
public class MainTest {
    private static Constructor<?> constructor;
    private static Class<?> mainClass;
    private static Method length;
    private static Method charAt;
    private static Method subSequence;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        Class<?> asciiCharSequenceClass = TestUtils.getInnerClass(mainClass, "AsciiCharSequence");

        constructor = TestUtils.getConstructor(asciiCharSequenceClass,
                new int[]{Modifier.PUBLIC, 0},
                mainClass,
                byte[].class);

        length = TestUtils.getMethod(asciiCharSequenceClass,
                "length",
                new int[]{Modifier.PUBLIC},
                Integer.TYPE);

        charAt = TestUtils.getMethod(asciiCharSequenceClass,
                "charAt",
                new int[]{Modifier.PUBLIC},
                Character.TYPE,
                Integer.TYPE);

        subSequence = TestUtils.getMethod(asciiCharSequenceClass,
                "subSequence",
                new int[]{Modifier.PUBLIC},
                CharSequence.class,
                Integer.TYPE,
                Integer.TYPE);
    }

    @Test(timeout = 8000)
    public void sample1() throws Throwable {
        Object mainInstance = mainClass.newInstance();
        String string = "I'm string";
        Object asciiCharSequence = TestUtils.newInstance(constructor, mainInstance, string.getBytes());

        assertTrue("Main.AsciiCharSequence should implement java.lang.CharSequence", asciiCharSequence instanceof CharSequence);

        int actualLength = (int) TestUtils.invokeMethod(asciiCharSequence, length);
        assertEquals("AsciiCharSequence.length()", string.length(), actualLength);
        assertEquals(string, asciiCharSequence.toString());

        for (int i = 0; i < string.length(); i++) {
            char actualChar = (char) TestUtils.invokeMethod(asciiCharSequence, charAt, i);
            assertEquals(String.format("AsciiCharSequence.charAt(%d)", i), string.charAt(i), actualChar);
        }

        Object substring = TestUtils.invokeMethod(asciiCharSequence, subSequence, 4, 8);
        assertNotNull("AsciiCharSequence.substring(4, 8) return null", substring);
        assertEquals("AsciiCharSequence.substring(4, 8)", string.substring(4, 8), substring.toString());
    }
}