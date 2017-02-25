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
    private static final int[][] leaps = new int[][]{
            {1, 0},
            {4, 1},
            {100, 24},
            {2017, 489},
            {2187, 530},
            {400, 97}
    };
    private static Method leapYearCount;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        leapYearCount = TestUtils.getMethod(mainClass,
                "leapYearCount",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Integer.TYPE, Integer.TYPE);
    }

    @Test(timeout = 2000)
    public void leapYearCount() throws Throwable {
        for (int[] leap : leaps) {
            String message = String.format("Main.leapYearCount(%s)", leap[0]);
            assertEquals(message, leap[1], TestUtils.invokeMethod(mainClass, leapYearCount, leap[0]));
        }
    }

}