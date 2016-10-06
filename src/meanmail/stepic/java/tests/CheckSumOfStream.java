package meanmail.stepic.java.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static meanmail.stepic.java.tests.Assertions.*;

/**
 * Created by meanmail on 30.09.2016.
 * https://vk.com/meanmail
 */
public class CheckSumOfStream {

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        
        // Paste your a code here
        
        return -1;
    }

    private static byte[] testSet1 = new byte[]{10, 20, 30, 50, 1, 125};
    private static byte[] testSet2 = new byte[]{111, -45, 0, -2, 58, -1};
    private static byte[] testSet3 = new byte[]{0, 0, 0, 0, 0, 0};
    private static byte[] testSet4 = new byte[]{-1, -1, -1, -1, -1, -1};
    private static byte[] testSet5 = new byte[]{0x33, 0x45, 0x01};
    private static byte[] testSet6 = new byte[]{};
    private static byte[] testSet7 = new byte[]{-1};

    public static void main(String[] args) throws IOException {

        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet1)), 71, "Test #1");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet2)), 931, "Test #2");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet3)), 0, "Test #3");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet4)), 5397, "Test #4");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet5)), 71, "Test #5");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet6)), 0, "Test #6");
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet7)), 255, "Test #7");
    }
}
