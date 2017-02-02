// Don't edit this file
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000L)
    public void isPalindromeSample1() throws Exception {
        String text = "Madam, I'm Adam!";
        assertTrue("Sample input: " + text, Main.isPalindrome(text));
    }

    @Test(timeout = 8000L)
    public void isPalindromeSample2() throws Exception {
        String text = "Text about a bird";
        assertFalse("Sample input: " + text, Main.isPalindrome(text));
    }

    @Test(timeout = 8000L)
    public void isPalindromeSample3() throws Exception {
        Random random = new Random();
        String text = "|2 @\"$U~ ~/~\\~p* p#e!!!r[ ]c{a}:s'e<.> _Es^a$c Re+p''p///u2-";
        assertTrue("Sample input: " + text, Main.isPalindrome(text));
    }
}