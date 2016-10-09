package meanmail.stepic.java.tests;

import java.util.HashSet;
import java.util.Set;

import static meanmail.stepic.java.tests.Assertions.*;

/**
 * Created by meanmail on 10.10.2016.
 * Реализуйте метод, вычисляющий симметрическую разность двух множеств.
 * Метод должен возвращать результат в виде нового множества.
 * Изменять переданные в него множества не допускается.
 *
 * Пример:
 *
 * Симметрическая разность множеств {1, 2, 3} и {0, 1, 2} равна {0, 3}.

 */
public class SymmetricDifference {

    // Наборы для тестов
    private static Set<Integer> set1 = new HashSet<>();
    private static Set<Integer> set2 = new HashSet<>();
    private static Set<Integer> set3 = new HashSet<>();
    private static Set<Integer> set4 = new HashSet<>();

    // Инициализация наборов для тестов
    static {
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(0);
        set2.add(1);
        set2.add(2);

        set3.add(0);
        set3.add(3);

        set4.add(10);
        set4.add(20);
        set4.add(30);
    }

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        HashSet<Integer> set1_ = new HashSet<>(set1);
        HashSet<Integer> set2_ = new HashSet<>(set2);

        assertEquals(symmetricDifference(set1_, set2_), set3, "Test #1.1");
        assertEquals(set1_, set1, "Test #1.2");
        assertEquals(set2_, set2, "Test #1.3");

        HashSet<Integer> set4_1 = new HashSet<>(set4);
        HashSet<Integer> set4_2 = new HashSet<>(set4);

        assertEquals(symmetricDifference(set4_1, set4_2), new HashSet<>(), "Test #2.1");
        assertEquals(set4_1, set4, "Test #2.2");
        assertEquals(set4_2, set4, "Test #2.3");

        HashSet<Object> set5_1 = new HashSet<>();
        HashSet<Object> set5_2 = new HashSet<>();

        assertEquals(symmetricDifference(set5_1, set5_2), new HashSet<>(), "Test #3.1");
        assertEquals(set5_1, new HashSet<>(), "Test #3.2");
        assertEquals(set5_2, new HashSet<>(), "Test #3.3");
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {

        // Здесь должен быть ваш код

        return (Set<T>) set1;
    }
}
