import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author meanmail
 */
public class MainTest {
    @Test(timeout = 8000)
    public void sample1() throws Throwable {
        String input = "Мама мыла-мыла-мыла раму!";
        String expected = "мыла\nмама\nраму";
        test(input, expected);
    }

    @Test(timeout = 8000)
    public void sample2() throws Throwable {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed sodales consectetur purus at faucibus. " +
                "Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. " +
                "Morbi lacinia velit blandit tincidunt efficitur. " +
                "Vestibulum eget metus imperdiet sapien laoreet faucibus. " +
                "Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        String expected = "consectetur\n" +
                "faucibus\n" +
                "ipsum\n" +
                "lorem\n" +
                "adipiscing\n" +
                "amet\n" +
                "dolor\n" +
                "eget\n" +
                "elit\n" +
                "mi";
        test(input, expected);
    }

    @Test(timeout = 8000)
    public void sample3() throws Throwable {
        String input = "Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.\n" +
                "\n" +
                "Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр. Например, в строке \"Мама мыла раму 33 раза!\" ровно пять слов: \"Мама\", \"мыла\", \"раму\", \"33\" и \"раза\".\n" +
                "\n" +
                "Подсчет слов должен выполняться без учета регистра, т.е. \"МАМА\", \"мама\" и \"Мама\" — это одно и то же слово. Выводите слова в нижнем регистре.\n" +
                "\n" +
                "Если в тексте меньше 10 уникальных слов, то выводите сколько есть.\n" +
                "\n" +
                "Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте, то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.\n" +
                "\n" +
                "Задача имеет красивое решение через стримы без циклов и условных операторов. Попробуйте придумать его.";
        String expected = "в\n" +
                "и\n" +
                "мама\n" +
                "слов\n" +
                "слова\n" +
                "то\n" +
                "10\n" +
                "33\n" +
                "без\n" +
                "выводите";
        test(input, expected);
    }

    private void test(String input, String expected) throws Throwable {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes())) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                TestUtils.runMain(in, new PrintStream(out));

                assertEquals(input, expected, out.toString().trim());
            }
        }
    }
}