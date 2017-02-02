// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void moveRobotNormal() throws Exception {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();

        Main.moveRobot(robotConnectionManager, 100, 500);

        assertEquals(1, robotConnectionManager.callCount());
    }

    @Test
    public void moveRobotCloseException() throws Exception {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();

        Main.moveRobot(robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected one time getConnection, but was %d", callCount), 1, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test
    public void moveRobotTry2() throws Exception {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();
        robotConnectionManager.setFailedConnectionCount(1);

        Main.moveRobot(robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected two time getConnection, but was %d", callCount), 2, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test
    public void moveRobotTry3() throws Exception {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedConnectionCount(2);

        Main.moveRobot(robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected three time getConnection(), but was %d", callCount), 3, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }

    @Test(expected = RobotConnectionException.class)
    public void moveRobotTry4() throws Exception {
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setFailedOnClose();
        robotConnectionManager.setFailedConnectionCount(3);

        Main.moveRobot(robotConnectionManager, 100, 500);

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected three time getConnection(), but was %d", callCount), 3, callCount);
        assertEquals(String.format("Expected zero time close(), but was %d", closeCount), 0, closeCount);

        fail("Expected RobotConnectionException");
    }


    @Test
    public void moveRobotException() throws Exception {
        RuntimeException expectedException = new RuntimeException();
        RobotConnectionManagerImpl robotConnectionManager = new RobotConnectionManagerImpl();
        robotConnectionManager.setMoveRobotToException(expectedException);

        try {
            Main.moveRobot(robotConnectionManager, 100, 500);
        } catch (Exception e) {
            String message = String.format("Expected RuntimeException, but %s", e.getClass().getSimpleName());
            assertEquals(message, expectedException, e);
        }

        int callCount = robotConnectionManager.callCount();
        int closeCount = robotConnectionManager.closeCount();
        assertEquals(String.format("Expected one time getConnection(), but was %d", callCount), 1, callCount);
        assertEquals(String.format("Expected one time close(), but was %d", closeCount), 1, closeCount);
    }
}