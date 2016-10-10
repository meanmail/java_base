package meanmail.stepic.java.tests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static meanmail.stepic.java.tests.Assertions.assertEquals;

/**
 * Напишите метод, находящий в стриме минимальный и максимальный элементы
 * в соответствии порядком, заданным Comparator'ом.
 * <p>
 * Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:
 * minMaxConsumer.accept(min, max);
 * <p>
 * Если стрим не содержит элементов, то вызовите
 * minMaxConsumer.accept(null, null);
 */
public class FindMinMax {

    // Это поле для тестирования не удаляйте и не используйте его
    private static boolean tested;

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        list.add(-100500);
        list.add(30);
        list.add(100500);

        BiConsumer<Integer, Integer> tester1 = (min, max) -> {
            assertEquals(min, -100500, "Test #1.1");
            assertEquals(max, 100500, "Test #1.2");

            tested();
        };

        findMinMax(list.stream(), Integer::compare, tester1);


        BiConsumer<Integer, Integer> tester2 = (min, max) -> {
            assertEquals(min, 100500, "Test #2.1");
            assertEquals(max, -100500, "Test #2.2");

            tested();
        };

        findMinMax(list.stream(), (x, y) -> Integer.compare(y, x), tester2);

        list = new ArrayList<>();

        BiConsumer<Integer, Integer> tester3 = (min, max) -> {
            assertEquals(min, null, "Test #3.1");
            assertEquals(max, null, "Test #3.2");

            tested();
        };

        findMinMax(list.stream(), Integer::compare, tester3);

        list = new ArrayList<>();

        list.add(9000);

        BiConsumer<Integer, Integer> tester4 = (min, max) -> {
            assertEquals(min, 9000, "Test #4.1");
            assertEquals(max, 9000, "Test #4.2");

            tested();
        };

        findMinMax(list.stream(), Integer::compare, tester4);

        assertEquals(tested, true, "Test: Call minMaxConsumer at least once?");
    }

    // Этот метод для тестирования, не используйте его
    private static void tested() {
        tested = true;
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        // Измените код в этом методе так, чтобы он работал правильно

        minMaxConsumer.accept(null, null);
    }
}