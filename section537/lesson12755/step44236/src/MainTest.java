// Don't edit this file

import org.junit.Test;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 5000)
    public void mainTest() throws Throwable {
        TestUtils.assertOutputEquals("It's alive! It's alive!");
    }
}