// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
public class MainTest {
    private static Method findMinMax;
    private static Class<?> mainClass;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");

        findMinMax = TestUtils.getMethod(mainClass,
                "findMinMax",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Void.TYPE,
                Stream.class,
                Comparator.class,
                BiConsumer.class);
    }

    @Test(timeout = 8000)
    public void findMinMax() throws Throwable {
        Stream stream = Arrays.stream(new Integer[]{10, 20, 1, 5, 8, 94, 1, -52, 0});
        Comparator<Integer> comparator = Integer::compare;
        final boolean[] failFlag = {true};
        BiConsumer<Integer, Integer> biConsumer = (min, max) -> {
            assertEquals(new Integer(-52), min);
            assertEquals(new Integer(94), max);
            failFlag[0] = false;
        };

        TestUtils.invokeMethod(mainClass, findMinMax, stream, comparator, biConsumer);

        if (failFlag[0]) {
            fail("biConsumer didn't accept");
        }
    }

}