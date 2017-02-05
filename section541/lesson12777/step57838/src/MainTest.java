// Don't edit this file

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author meanmail
 */
public class MainTest {
    private static final String MESSAGE_TEMPLATE = "Main.ternaryOperator().apply(\"%s\")";
    private static Method ternaryOperator;
    private static Class<?> mainClass;
    private Function safeStringLength;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        ternaryOperator = TestUtils.getMethod(mainClass,
                "ternaryOperator",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Function.class,
                Predicate.class, Function.class, Function.class);
    }

    @Before
    public void before() throws Throwable {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;

        safeStringLength = (Function) TestUtils.invokeMethod(mainClass, ternaryOperator, condition, ifTrue, ifFalse);

        assertNotNull("Main.ternaryOperator() return null", safeStringLength);
    }

    @Test(timeout = 8000)
    public void ternaryOperatorEmptyString() throws Throwable {
        String string = "";
        assertEquals(String.format(MESSAGE_TEMPLATE, string), 0, safeStringLength.apply(string));
    }

    @Test(timeout = 8000)
    public void ternaryOperatorNotEmptyString() throws Throwable {
        String string = "Hello!";
        assertEquals(String.format(MESSAGE_TEMPLATE, string), string.length(), safeStringLength.apply(string));
    }

    @Test(timeout = 8000)
    public void ternaryOperatorNullString() throws Throwable {
        String string = null;
        assertEquals(String.format(MESSAGE_TEMPLATE, "null"), 0, safeStringLength.apply(string));
    }
}