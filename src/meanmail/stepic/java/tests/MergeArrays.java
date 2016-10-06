package meanmail.stepic.java.tests;

import static meanmail.stepic.java.tests.Assertions.*;

/**
 * Created by meanmail on 06.10.2016.
 */
public class MergeArrays {

    public static void main(String[] args){

        assertEquals(mergeArrays(new int[]{0, 2, 4}, new int[]{1, 3, 5}), new int[]{0, 1, 2, 3, 4, 5}, "Test #1");
        assertEquals(mergeArrays(new int[]{}, new int[]{1, 3, 5}), new int[]{1, 3, 5}, "Test #2");
        assertEquals(mergeArrays(new int[]{}, new int[]{}), new int[]{}, "Test #3");
        assertEquals(mergeArrays(new int[]{1, 1, 1}, new int[]{1, 1, 1}), new int[]{1, 1, 1, 1, 1, 1}, "Test #4");
        assertEquals(mergeArrays(new int[]{1, 2, 3}, new int[]{1, 2, 3}), new int[]{1, 1, 2, 2, 3, 3}, "Test #5");
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {

        // Paste your a code here

        return a1;
    }
}
