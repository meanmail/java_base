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
    private static final String MESSAGE_TEMPLATE = "Main.isPalindrome(\"%s\")";
    private static Method isPalindrome;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        isPalindrome = TestUtils.getMethod(mainClass,
                "isPalindrome",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Boolean.TYPE,
                String.class);
    }

    @Test(timeout = 8000L)
    public void isPalindromeSample1() throws Throwable {
        String text = "Madam, I'm Adam!";
        String message = String.format(MESSAGE_TEMPLATE, text);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, isPalindrome, text));
    }

    @Test(timeout = 8000L)
    public void isPalindromeSample2() throws Throwable {
        String text = "Text about a bird";
        String message = String.format(MESSAGE_TEMPLATE, text);
        assertEquals(message, false, TestUtils.invokeMethod(mainClass, isPalindrome, text));
    }

    @Test(timeout = 8000L)
    public void isPalindromeSample3() throws Throwable {
        String text = "|2 @\"$U~ ~/~\\~p* p#e!!!r[ ]c{a}:s'e<.> _Es^a$c Re+p''p///u2-";
        String message = String.format(MESSAGE_TEMPLATE, text);
        assertEquals(message, true, TestUtils.invokeMethod(mainClass, isPalindrome, text));
    }
}