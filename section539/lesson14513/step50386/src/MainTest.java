// Don't edit this file
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {

    @Test
    public void checkLabels() throws Exception {
        Map<String, Class<?>> classes = new HashMap<>();

        Arrays.asList(Main.class.getDeclaredClasses()).forEach(clazz -> classes.put(clazz.getSimpleName(), clazz));

        Class<?> keywordAnalyzerClass = classes.get("KeywordAnalyzer");
        Class<?> spamAnalyzerClass = classes.get("SpamAnalyzer");
        Class<?> negativeTextAnalyzerClass = classes.get("NegativeTextAnalyzer");
        Class<?> tooLongTextAnalyzerClass = classes.get("TooLongTextAnalyzer");

        assertNotNull("Don't found class KeywordAnalyzer", keywordAnalyzerClass);
        assertNotNull("Don't found class SpamAnalyzer", spamAnalyzerClass);
        assertNotNull("Don't found class NegativeTextAnalyzer", negativeTextAnalyzerClass);
        assertNotNull("Don't found class TooLongTextAnalyzer", tooLongTextAnalyzerClass);

        assertTrue("KeywordAnalyzer is not implements TextAnalyzer", TextAnalyzer.class.isAssignableFrom(keywordAnalyzerClass));
        assertTrue("SpamAnalyzerClass is not extends KeywordAnalyzer", keywordAnalyzerClass.isAssignableFrom(spamAnalyzerClass));
        assertTrue("NegativeTextAnalyzer is not extends KeywordAnalyzer", keywordAnalyzerClass.isAssignableFrom(negativeTextAnalyzerClass));

        assertTrue("KeywordAnalyzer is not abstract", Modifier.isAbstract(keywordAnalyzerClass.getModifiers()));

        Method getKeywords = getMethod(keywordAnalyzerClass, "getKeywords");

        assertNotNull("KeywordAnalyzer is contains method getKeywords()", getKeywords);
        int modifiers = getKeywords.getModifiers();
        assertTrue("KeywordAnalyzer.getKeywords should be abstract", Modifier.isAbstract(modifiers));
        assertTrue("KeywordAnalyzer.getKeywords should be protected", Modifier.isProtected(modifiers));

        Method getLabel = getMethod(keywordAnalyzerClass, "getLabel");
        assertNotNull("KeywordAnalyzer is contain method getLabel()", getLabel);
        modifiers = getLabel.getModifiers();
        assertTrue("KeywordAnalyzer.getLabel should be abstract", Modifier.isAbstract(modifiers));
        assertTrue("KeywordAnalyzer.getLabel should be protected", Modifier.isProtected(modifiers));

        Constructor<?> spamAnalyzerConstructor = getConstructor(spamAnalyzerClass, Main.class, String[].class);
        assertNotNull("SpamAnalyzerClass don't contain constructor with String[]", spamAnalyzerConstructor);

        Constructor<?> negativeTextAnalyzerConstructor = getConstructor(negativeTextAnalyzerClass, Main.class);
        assertNotNull("NegativeTextAnalyzer don't contain default constructor", negativeTextAnalyzerConstructor);

        Constructor<?> tooLongTextAnalyzerConstructor = getConstructor(tooLongTextAnalyzerClass, Main.class, int.class);
        assertNotNull("TooLongTextAnalyzer don't contain constructor with int", tooLongTextAnalyzerConstructor);

        String[] keywords = new String[]{
                "spam",
                "same spam!"
        };

        Main main = new Main();

        TextAnalyzer[] analyzers = new TextAnalyzer[]{
                (TextAnalyzer) spamAnalyzerConstructor.newInstance(main, keywords),
                (TextAnalyzer) negativeTextAnalyzerConstructor.newInstance(main),
                (TextAnalyzer) tooLongTextAnalyzerConstructor.newInstance(main, 10),
        };

        assertEquals(Label.OK, main.checkLabels(analyzers, "Good text"));
        assertEquals(Label.NEGATIVE_TEXT, main.checkLabels(analyzers, "Sorry :("));
        assertEquals(Label.NEGATIVE_TEXT, main.checkLabels(analyzers, "Bye =(!"));
        assertEquals(Label.NEGATIVE_TEXT, main.checkLabels(analyzers, "What?:|"));
        assertEquals(Label.TOO_LONG, main.checkLabels(analyzers, "I'm very long string!"));
        assertEquals(Label.SPAM, main.checkLabels(analyzers, "Hi. I'm spam"));
        assertEquals(Label.SPAM, main.checkLabels(analyzers, "Hi. I'm too spam!"));
    }

    private Method getMethod(Class<?> keywordAnalyzerClass, String getKeywords2) {
        Method getKeywords = null;
        try {
            getKeywords = keywordAnalyzerClass.getDeclaredMethod(getKeywords2);
        } catch (NoSuchMethodException ignored) {
        }
        return getKeywords;
    }

    private Constructor<?> getConstructor(Class<?> analyzerClass, Class<?>... parameterClass) {
        Constructor<?> spamAnalyzerConstructor = null;
        try {
            spamAnalyzerConstructor = analyzerClass.getDeclaredConstructor(parameterClass);
        } catch (NoSuchMethodException ignored) {
        }
        return spamAnalyzerConstructor;
    }

}