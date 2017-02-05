// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author meanmail
 */
public class MainTest {
    private static Method pseudoRandomStream;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        pseudoRandomStream = TestUtils.getMethod(mainClass,
                "pseudoRandomStream",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                IntStream.class,
                Integer.TYPE);
    }

    @Test(timeout = 8000)
    public void pseudoRandomStream() throws Throwable {
        IntStream stream = (IntStream) TestUtils.invokeMethod(mainClass, pseudoRandomStream, 13);

        assertNotNull("Main.pseudoRandomStream(13) return null", stream);

        List<Integer> actual = new ArrayList<>();
        stream.limit(10).forEach(actual::add);

        Integer[] expected = new Integer[]{13, 16, 25, 62, 384, 745, 502, 200, 0, 0};

        assertArrayEquals("Main.pseudoRandomStream(13).limit(10).toArray()",
                expected,
                actual.toArray(new Integer[10]));
    }

}