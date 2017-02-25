// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author meanmail
 */
public class MainTest {

    private static ByteArrayOutputStream outStream;
    private static byte[][][] testSet = new byte[][][]{{{65, 13, 10, 10, 13}, {65, 10, 10, 13}}, {{}, {}}, {{13, 10}, {10}},
            {{10, 13}, {10, 13}}, {{13, 13, 13, 13, 13}, {13, 13, 13, 13, 13}}, {{10, 10}, {10, 10}}, {{13, 13, 10}, {13, 10}},
            {{13, 65}, {13, 65}}, {{65, 65, 65}, {65, 65, 65}}, {{13, 13, 13, 10}, {13, 13, 10}}, {{55, 55}, {55, 55}}};

    @BeforeClass
    public static void beforeClass() throws IOException {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @Test(timeout = 8000)
    public void main() throws Throwable {
        for (int i = 0; i < testSet.length; i++) {
            outStream.reset();
            TestUtils.runMain(new ByteArrayInputStream(testSet[i][0]), new PrintStream(outStream));

            byte[] expected = testSet[i][1];
            byte[] actual = outStream.toByteArray();
            String message = String.format("Test#%d: Expected %s but was %s", i, Arrays.toString(expected), Arrays.toString(actual));
            assertArrayEquals(message, expected, actual);
        }
    }
}