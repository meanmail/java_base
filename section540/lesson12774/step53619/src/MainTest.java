// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.*;

import static org.junit.Assert.*;

/**
 * @author meanmail
 */
public class MainTest {
    private static final java.lang.String CLASS_A = "org.stepic.java.logging.ClassA";
    private static final String CLASS_B = "org.stepic.java.logging.ClassB";
    private static final String ORG_STEPIC_JAVA = "org.stepic.java";

    private static Method configureLogging;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        configureLogging = TestUtils.getMethod(mainClass,
                "configureLogging",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Void.TYPE);
    }

    @Test(timeout = 8000)
    public void configureLoggingClassA() throws Throwable {
        TestUtils.invokeMethod(mainClass, configureLogging);

        Level actual = Logger.getLogger(CLASS_A).getLevel();
        assertEquals(CLASS_A, Level.ALL, actual);
    }

    @Test(timeout = 8000)
    public void configureLoggingClassB() throws Throwable {
        TestUtils.invokeMethod(mainClass, configureLogging);

        Level actual = Logger.getLogger(CLASS_B).getLevel();
        assertEquals(CLASS_B, Level.WARNING, actual);
    }

    @Test(timeout = 8000)
    public void configureLoggingPackage() throws Throwable {
        TestUtils.invokeMethod(mainClass, configureLogging);

        Level actual = Logger.getLogger(ORG_STEPIC_JAVA).getLevel();
        assertEquals(ORG_STEPIC_JAVA, Level.ALL, actual);
    }

    @Test(timeout = 8000)
    public void configureLoggingHandler() throws Throwable {
        TestUtils.invokeMethod(mainClass, configureLogging);

        Logger logger = Logger.getLogger(ORG_STEPIC_JAVA);

        String message = "Messages \"org.stepic.java\" don't should sending in parent handlers";
        assertFalse(message, logger.getUseParentHandlers());

        if (logger.getHandlers().length == 0) {
            fail("Don't found Handler");
        }

        Handler consoleHandler = null;
        for (Handler handler : logger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                consoleHandler = handler;
            }
        }
        assertNotNull("Don't found Console Handler", consoleHandler);
        Formatter formatter = consoleHandler.getFormatter();
        assertNotNull("Don't fount Formatter", formatter);
        assertEquals("Formatter should be XML formatter", XMLFormatter.class, formatter.getClass());
    }
}