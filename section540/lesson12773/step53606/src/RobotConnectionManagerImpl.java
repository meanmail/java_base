// Don't edit this file
/**
 * @author meanmail
 */
public class RobotConnectionManagerImpl implements RobotConnectionManager {
    private int callCount;
    private boolean failedOnClose;
    private int failedConnectionCount;
    private int closeCount;
    private RuntimeException moveRobotToException;

    @Override
    public RobotConnection getConnection() {
        callCount++;
        if (callCount <= failedConnectionCount) {
            throw new RobotConnectionException("Connection failed");
        }
        return new RobotConnection() {
            @Override
            public void moveRobotTo(int x, int y) {
                if (moveRobotToException != null) {
                    throw moveRobotToException;
                }
            }

            @Override
            public void close() {
                closeCount++;
                if (failedOnClose) {
                    throw new RobotConnectionException("Closing failed");
                }
            }
        };
    }

    int callCount() {
        return callCount;
    }

    void setFailedOnClose() {
        this.failedOnClose = true;
    }

    void setFailedConnectionCount(int failedConnectionCount) {
        this.failedConnectionCount = failedConnectionCount;
    }

    int closeCount() {
        return closeCount;
    }

    void setMoveRobotToException(RuntimeException moveRobotToException) {
        this.moveRobotToException = moveRobotToException;
    }
}
