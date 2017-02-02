// Don't edit this file
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000L)
    public void sample1() {
        Main.ComplexNumber a = new Main.ComplexNumber(1, 1);
        Main.ComplexNumber b = new Main.ComplexNumber(1, 1);

        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(a, b);
    }

    @Test(timeout = 8000L)
    public void sample2() {
        Main.ComplexNumber a = new Main.ComplexNumber(1, 1);
        Main.ComplexNumber b = new Main.ComplexNumber(1, 2);

        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }

    @Test(timeout = 8000L)
    public void sample3() {
        Main.ComplexNumber a = new Main.ComplexNumber(1, 1);
        Main.ComplexNumber b = new Main.ComplexNumber(42, 1);

        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }

    @Test(timeout = 8000L)
    public void sample4() {
        Main.ComplexNumber a = new Main.ComplexNumber(10.25, 1.69);
        Main.ComplexNumber b = new Main.ComplexNumber(10.25, 1.69);

        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(a, b);
    }
}