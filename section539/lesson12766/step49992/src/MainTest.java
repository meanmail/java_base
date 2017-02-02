// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000L)
    public void moveRobotSample1() throws Exception {
        Robot robot = new Robot();
        Main.moveRobot(robot, 3, 0);

        assertEquals(3, robot.getX());
        assertEquals(0, robot.getY());
    }

    @Test(timeout = 8000L)
    public void moveRobotSample2() throws Exception {
        Robot robot = new Robot();
        Main.moveRobot(robot, -130, 200);

        assertEquals(-130, robot.getX());
        assertEquals(200, robot.getY());
    }
}