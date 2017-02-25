// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author meanmail
 */
public class MainTest {
    private static final int[][][] samples = new int[][][]{
            {{0, 2, 4}, {1, 3, 5}, {0, 1, 2, 3, 4, 5}},
            {{}, {1, 3, 5}, {1, 3, 5}},
            {{}, {}, {}},
            {{1, 1, 1}, {1, 1, 1}, {1, 1, 1, 1, 1, 1}},
            {{1, 2, 3}, {1, 2, 3}, {1, 1, 2, 2, 3, 3}},
            {{6}, {1, 3, 5}, {1, 3, 5, 6}},
            {{6}, {5, 5, 5, 5}, {5, 5, 5, 5, 6}},
            {{1, 2, 3, 5}, {6}, {1, 2, 3, 5, 6}},
            {{1, 2, 3, 5}, {}, {1, 2, 3, 5}},
            {{4, 4}, {1, 2, 3}, {1, 2, 3, 4, 4}},
            {{1, 2, 3}, {4, 4}, {1, 2, 3, 4, 4}}
    };

    private static final String MESSAGE_TEMPLATE = "Main.mergeArrays(%s, %s)\nExpected: %s\nActual: %s\n";
    private static Method mergeArrays;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        mergeArrays = TestUtils.getMethod(mainClass,
                "mergeArrays",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                int[].class,
                int[].class,
                int[].class);
    }

    @Test(timeout = 6000)
    public void mergeArraysSample1() throws Throwable {
        for (int[][] sample : samples) {
            int[] array = (int[]) TestUtils.invokeMethod(mainClass, mergeArrays, sample[0], sample[1]);

            String message = String.format(MESSAGE_TEMPLATE,
                    Arrays.toString(sample[0]),
                    Arrays.toString(sample[1]),
                    Arrays.toString(sample[2]),
                    Arrays.toString(array));
            assertArrayEquals(message, sample[2], array);
        }
    }

}