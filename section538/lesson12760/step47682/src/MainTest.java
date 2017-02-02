// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test
    public void charExpressionSample1() throws Exception {
        assertEquals('|', Main.charExpression(32));
    }

    @Test
    public void charExpressionSample2() throws Exception {
        assertEquals('y', Main.charExpression(29));
    }

    @Test
    public void charExpressionSample3() throws Exception {
        assertEquals('\\', Main.charExpression(0));
    }

    @Test
    public void charExpressionSample4() throws Exception {
        assertEquals('H', Main.charExpression(-20));
    }
}