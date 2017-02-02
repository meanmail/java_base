// Don't edit this file
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
@RunWith(Theories.class)
public class MainTest {
    @DataPoints("leaps")
    public static final int[][] leaps = new int[][]{
            {1, 0},
            {4, 1},
            {100, 24},
            {2017, 489},
            {2187, 530},
            {400, 97}
    };

    @Theory
    public void leapYearCount(@FromDataPoints("leaps") int[] leap) throws Exception {
        assertEquals(leap[1], Main.leapYearCount(leap[0]));
    }

}