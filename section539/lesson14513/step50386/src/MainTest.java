// Don't edit this file

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000)
    public void checkLabels() throws Throwable {
        Class<?> mainClass = TestUtils.getUserClass("Main");

        Class<?> keywordAnalyzerClass = TestUtils.getInnerClass(mainClass, "KeywordAnalyzer");
        Class<?> spamAnalyzerClass = TestUtils.getInnerClass(mainClass, "SpamAnalyzer");
        Class<?> negativeTextAnalyzerClass = TestUtils.getInnerClass(mainClass, "NegativeTextAnalyzer");
        Class<?> tooLongTextAnalyzerClass = TestUtils.getInnerClass(mainClass, "TooLongTextAnalyzer");

        assertTrue("KeywordAnalyzer is not implements TextAnalyzer", TextAnalyzer.class.isAssignableFrom(keywordAnalyzerClass));
        assertTrue("SpamAnalyzerClass is not extends KeywordAnalyzer", keywordAnalyzerClass.isAssignableFrom(spamAnalyzerClass));
        assertTrue("NegativeTextAnalyzer is not extends KeywordAnalyzer", keywordAnalyzerClass.isAssignableFrom(negativeTextAnalyzerClass));

        assertTrue("KeywordAnalyzer is not abstract", Modifier.isAbstract(keywordAnalyzerClass.getModifiers()));

        TestUtils.getMethod(keywordAnalyzerClass,
                "getKeywords",
                new int[]{Modifier.PROTECTED | Modifier.ABSTRACT},
                String[].class);

        TestUtils.getMethod(keywordAnalyzerClass,
                "getLabel",
                new int[]{Modifier.PROTECTED | Modifier.ABSTRACT},
                Label.class);

        Constructor<?> spamAnalyzerConstructor = TestUtils.getConstructor(spamAnalyzerClass,
                new int[]{Modifier.PUBLIC, 0},
                Main.class,
                String[].class);

        Constructor<?> negativeTextAnalyzerConstructor = TestUtils.getConstructor(negativeTextAnalyzerClass,
                new int[]{Modifier.PUBLIC, 0},
                Main.class);

        Constructor<?> tooLongTextAnalyzerConstructor = TestUtils.getConstructor(tooLongTextAnalyzerClass,
                new int[]{Modifier.PUBLIC, 0},
                Main.class,
                int.class);

        String[] keywords = new String[]{
                "spam",
                "same spam!"
        };

        Object main = mainClass.newInstance();

        TextAnalyzer[] analyzers = new TextAnalyzer[]{
                (TextAnalyzer) spamAnalyzerConstructor.newInstance(main, keywords),
                (TextAnalyzer) negativeTextAnalyzerConstructor.newInstance(main),
                (TextAnalyzer) tooLongTextAnalyzerConstructor.newInstance(main, 10),
        };

        Method checkLabels = TestUtils.getMethod(mainClass,
                "checkLabels",
                new int[]{Modifier.PUBLIC, 0},
                Label.class,
                TextAnalyzer[].class, String.class);

        assertEquals(Label.OK, TestUtils.invokeMethod(main, checkLabels, analyzers, "Good text"));
        assertEquals(Label.NEGATIVE_TEXT, TestUtils.invokeMethod(main, checkLabels, analyzers, "Sorry :("));
        assertEquals(Label.NEGATIVE_TEXT, TestUtils.invokeMethod(main, checkLabels, analyzers, "Bye =(!"));
        assertEquals(Label.NEGATIVE_TEXT, TestUtils.invokeMethod(main, checkLabels, analyzers, "What?:|"));
        assertEquals(Label.TOO_LONG, TestUtils.invokeMethod(main, checkLabels, analyzers, "I'm very long string!"));
        assertEquals(Label.SPAM, TestUtils.invokeMethod(main, checkLabels, analyzers, "Hi. I'm spam"));
        assertEquals(Label.SPAM, TestUtils.invokeMethod(main, checkLabels, analyzers, "Hi. I'm too spam!"));
    }
}