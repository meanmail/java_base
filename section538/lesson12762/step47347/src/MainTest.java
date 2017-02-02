// Don't edit this file
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author meanmail
 */
@RunWith(Theories.class)
public class MainTest {

    @DataPoints("samples")
    public static final int[][][] samples = new int[][][]{
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

    @Theory
    public void mergeArraysSample1(@FromDataPoints("samples") int[][] sample) throws Exception {
        int[] array = Main.mergeArrays(sample[0], sample[1]);

        assertArrayEquals(sample[2], array);
    }

}