// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author meanmail
 */
public class MainTest {
    private static final String MESSAGE_TEMPLATE_HASHCODE = "\nComplexNumber a = new ComplexNumber(%f, %f);\n" +
            "ComplexNumber b = new ComplexNumber(%f, %f);\n" +
            "a.hashCode() == b.hashCode()";
    private static final String MESSAGE_TEMPLATE_EQUALS = "\nComplexNumber a = new ComplexNumber(%f, %f);\n" +
            "ComplexNumber b = new ComplexNumber(%f, %f);\n" +
            "a.equals(b)";
    private static Constructor<?> constructor;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        Class<?> complexNumberClass = TestUtils.getInnerClass(mainClass, "ComplexNumber");

        constructor = TestUtils.getConstructor(complexNumberClass,
                new int[]{Modifier.PUBLIC, 0},
                mainClass,
                Double.TYPE,
                Double.TYPE);
    }

    @Test(timeout = 8000L)
    public void sample1() throws IllegalAccessException, InstantiationException {
        Object mainInstance = mainClass.newInstance();

        Object a = TestUtils.newInstance(constructor, mainInstance, 1, 1);
        Object b = TestUtils.newInstance(constructor, mainInstance, 1, 1);

        String message = String.format(MESSAGE_TEMPLATE_HASHCODE, 1.0, 1.0, 1.0, 1.0);
        assertEquals(message, a.hashCode(), b.hashCode());
        message = String.format(MESSAGE_TEMPLATE_EQUALS, 1.0, 1.0, 1.0, 1.0);
        assertEquals(message, a, b);
    }

    @Test(timeout = 8000L)
    public void sample2() throws IllegalAccessException, InstantiationException {
        Object mainInstance = mainClass.newInstance();

        Object a = TestUtils.newInstance(constructor, mainInstance, 1, 1);
        Object b = TestUtils.newInstance(constructor, mainInstance, 1, 2);

        String message = String.format(MESSAGE_TEMPLATE_HASHCODE, 1.0, 1.0, 1.0, 2.0);
        assertNotEquals(message, a.hashCode(), b.hashCode());
        message = String.format(MESSAGE_TEMPLATE_EQUALS, 1.0, 1.0, 1.0, 2.0);
        assertNotEquals(message, a, b);
    }

    @Test(timeout = 8000L)
    public void sample3() throws IllegalAccessException, InstantiationException {
        Object mainInstance = mainClass.newInstance();

        Object a = TestUtils.newInstance(constructor, mainInstance, 1, 1);
        Object b = TestUtils.newInstance(constructor, mainInstance, 42, 1);

        String message = String.format(MESSAGE_TEMPLATE_HASHCODE, 1.0, 1.0, 42.0, 1.0);
        assertNotEquals(message, a.hashCode(), b.hashCode());
        message = String.format(MESSAGE_TEMPLATE_EQUALS, 1.0, 1.0, 42.0, 1.0);
        assertNotEquals(message, a, b);
    }

    @Test(timeout = 8000L)
    public void sample4() throws IllegalAccessException, InstantiationException {
        Object mainInstance = mainClass.newInstance();

        Object a = TestUtils.newInstance(constructor, mainInstance, 10.25, 1.69);
        Object b = TestUtils.newInstance(constructor, mainInstance, 10.25, 1.69);

        String message = String.format(MESSAGE_TEMPLATE_HASHCODE, 10.25, 1.69, 10.25, 1.69);
        assertEquals(message, a.hashCode(), b.hashCode());
        message = String.format(MESSAGE_TEMPLATE_EQUALS, 10.25, 1.69, 10.25, 1.69);
        assertEquals(message, a, b);
    }
}