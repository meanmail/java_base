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
    private static final String MESSAGE_TEMPLATE = "robot.moveRobot(%d, %d);\nrobot.get%s()";
    private static Method moveRobot;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        moveRobot = TestUtils.getMethod(mainClass,
                "moveRobot",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Void.TYPE,
                Robot.class, Integer.TYPE, Integer.TYPE);
    }

    @Test(timeout = 8000L)
    public void moveRobotSample1() throws Throwable {
        beforeClass();

        Robot robot = new Robot();
        TestUtils.invokeMethod(mainClass, moveRobot, robot, 3, 0);

        int x = robot.getX();
        int y = robot.getY();

        assertEquals(String.format(MESSAGE_TEMPLATE, 3, 0, "X"), 3, x);
        assertEquals(String.format(MESSAGE_TEMPLATE, 3, 0, "Y"), 0, y);
    }

    @Test(timeout = 8000L)
    public void moveRobotSample2() throws Throwable {
        beforeClass();

        Robot robot = new Robot();
        TestUtils.invokeMethod(mainClass, moveRobot, robot, -130, 200);

        int x = robot.getX();
        int y = robot.getY();

        assertEquals(String.format(MESSAGE_TEMPLATE, -130, 200, "X"), -130, x);
        assertEquals(String.format(MESSAGE_TEMPLATE, -130, 200, "Y"), 200, y);
    }
}