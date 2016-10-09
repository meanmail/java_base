package meanmail.stepic.java.tests;

import java.util.stream.IntStream;

import static meanmail.stepic.java.tests.Assertions.assertEquals;
import static meanmail.stepic.java.tests.Assertions.assertNotEquals;

/**
 * Created by meanmail on 10.10.2016.
 *
 * Напишите метод, возвращающий стрим псевдослучайных целых чисел. Алгоритм генерации чисел следующий:
 * 1) Берется какое-то начальное неотрицательное число (оно будет передаваться в ваш метод проверяющей системой).
 * 2) Первый элемент последовательности устанавливается равным этому числу.
 * 3) Следующие элементы вычисляются по рекуррентной формуле Rn+1 = mid(Rn^2),
 * где mid — это функция, выделяющая второй, третий и четвертый разряд переданного числа. Например, mid(123456)=345.
 */
public class PseudoRandomStream {

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        String expected = "13 16 25 62 384 745 502 200 0 0 0 0 0 0 0 ";
        StringBuffer value = new StringBuffer();

        IntStream stream = pseudoRandomStream(13);

        assertNotEquals(stream, null, "Test #1 [stream = pseudoRandomStream(13)]");

        if (stream != null) {
            stream = stream.limit(15);
            assertNotEquals(stream, null, "Test #2 [stream.limit(15)]");
        }

        if (stream != null) {
            stream.forEach(v -> value.append(v).append(" "));
            assertEquals(value.toString(), expected, "Test #3");
        }
    }

    public static IntStream pseudoRandomStream(int seed) {

        // Измените код в этом методе так, чтобы он работал правильно

        return null;
    }

}
