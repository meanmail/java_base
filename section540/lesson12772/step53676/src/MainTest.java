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
    private static Method getCallerClassAndMethodName;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        getCallerClassAndMethodName = TestUtils.getMethod(mainClass,
                "getCallerClassAndMethodName",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                String.class);
    }

    @Test(timeout = 8000)
    public void getCallerClassAndMethodNameNotNull1() throws Throwable {
        String classMethod = (String) TestUtils.invokeMethod(mainClass, getCallerClassAndMethodName);
        assertEquals("sun.reflect.NativeMethodAccessorImpl#invoke", classMethod);
    }
}