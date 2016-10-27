package meanmail.stepic.java.tests.module5.lesson2.step9;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static meanmail.stepic.java.tests.Assertions.assertEquals;

/**
 * Created by meanmail on 13.10.2016.
 * <p>
 * Этот класс переименуйте в Main
 */
public class Main {


    public static void main(String[] args) throws IOException {

        // Код для тестирования. Перед отправкой в степик, удалите или закомиентируйте код для тестирования
        test();
        if (!testing)
            return;
        // Конец кода который не нужно отправлять в стэпик

        // Здесь поместите ваш код, запустите и код будет протестирован

    }

    // Нижележащий код для тестирования, не изменяйте его

    private static boolean testing = false;
    private static ByteArrayOutputStream outStream;
    private static final PrintStream standartOut = System.out;

    private static byte[][][] testSet = new byte[][][]{{{65, 13, 10, 10, 13}, {65, 10, 10, 13}}, {{}, {}}, {{13, 10}, {10}},
            {{10, 13}, {10, 13}}, {{13, 13, 13, 13, 13},{13, 13, 13, 13, 13}}, {{10, 10}, {10, 10}}, {{13, 13, 10}, {13, 10}},
            {{13, 65}, {13, 65}}};

    private static void test() throws IOException {

        if (testing)
            return;

        testing = true;

        for (int i = 0; i < testSet.length; i++) {

            testStart(i);

            main(null);

            testEnd(i);
        }

        testing = false;
    }

    // Этот метод для тестирования.
    private static void testStart(int i) throws IOException {

        ByteArrayInputStream tsStream = new ByteArrayInputStream(testSet[i][0]);

        // Пересылаем тестовый набор в выходной поток
        System.setIn(tsStream);
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    private static void testEnd(int i) {

        System.setOut(standartOut);

        byte[] result = outStream.toByteArray();

        assertEquals(result, testSet[i][1], "Test #" + (i + 1));
    }

}
