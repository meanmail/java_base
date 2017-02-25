// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
public class MainTest {
    private static Method moveRobot;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        moveRobot = TestUtils.getMethod(mainClass,
                "moveRobot",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Void.TYPE,
                RobotConnectionManager.class,
                Integer.TYPE, Integer.TYPE);
    }

    @Test(timeout = 8000)
    public void moveRobotNormal() throws Throwable {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();

        TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);

        assertEquals(1, robotConnectionManager.callCount());
    }

    @Test(timeout = 8000)
    public void moveRobotCloseException() throws Throwable {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();

        TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected one time getConnection, but was %d", callCount), 1, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test(timeout = 8000)
    public void moveRobotTry2() throws Throwable {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();
        robotConnectionManager.setFailedConnectionCount(1);

        TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected two time getConnection, but was %d", callCount), 2, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test(timeout = 8000)
    public void moveRobotTry3() throws Throwable {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedConnectionCount(2);

        TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected three time getConnection(), but was %d", callCount), 3, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test(expected = RobotConnectionException.class, timeout = 8000)
    public void moveRobotTry4() throws Throwable {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();
        robotConnectionManager.setFailedConnectionCount(3);

        TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected three time getConnection(), but was %d", callCount), 3, callCount);
        assertEquals(String.format("Expected zero time close(), but was %d", closeCount), 0, closeCount);

        fail("Expected RobotConnectionException");
    }


    @Test(timeout = 8000)
    public void moveRobotException() throws Exception {
        RuntimeException expectedException = new RuntimeException();
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setMoveRobotToException(expectedException);

        try {
            TestUtils.invokeMethod(mainClass, moveRobot, robotConnectionManager, 100, 500);
        } catch (Throwable e) {
            String message = String.format("Expected RuntimeException, but %s", e.getClass().getSimpleName());
            assertEquals(message, expectedException, e);
        }

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected one time getConnection(), but was %d", callCount), 1, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }
}