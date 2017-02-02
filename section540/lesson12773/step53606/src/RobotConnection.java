// Don't edit this file
/**
 * @author meanmail
 */
public interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);

    @Override
    void close();
}
