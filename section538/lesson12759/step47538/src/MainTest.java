// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void doubleExpressionSamples() throws Exception {
        assertTrue(Main.doubleExpression(0.1, 0.2, 0.3));
    }

    @Test
    public void doubleExpressionFalse1() throws Exception {
        assertFalse(Main.doubleExpression(5.1006, 4.2, 9.3));
    }

    @Test
    public void doubleExpressionFalse2() throws Exception {
        assertFalse(Main.doubleExpression(90500, -100500, -9000.00025));
    }

    @Test
    public void doubleExpressionTrue1() throws Exception {
        assertTrue(Main.doubleExpression(5, 0.0001, 5));
    }

    @Test
    public void doubleExpressionTrue2() throws Exception {
        assertTrue(Main.doubleExpression(-5, 0.0001, -5));
    }
}