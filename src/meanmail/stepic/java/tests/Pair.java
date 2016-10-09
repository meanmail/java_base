package meanmail.stepic.java.tests;

import static meanmail.stepic.java.tests.Assertions.*;

/**
 * Created by meanmail on 10.10.2016.
 *
 * Реализуйте generic-класс Pair, похожий на Optional,
 * но содержащий пару элементов разных типов и не запрещающий
 * элементам принимать значение null.
 * Реализуйте методы getFirst(), getSecond(), equals() и hashCode(),
 * а также статический фабричный метод of().
 * Конструктор должен быть закрытым (private).
 *
 * Пожалуйста, не меняйте модификатор доступа класса Pair.
 * Для корректной проверки класс должен иметь пакетную видимость.
 */

class Pair<F, S> {

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        // Test phase #1

        Pair<Integer, String> pair = Pair.of(1, "hello");

        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");

        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        assertEquals(mustBeTrue, true, "Test #1");
        assertEquals(mustAlsoBeTrue, true, "Test #2");
        assertEquals(pair2.equals(pair), true, "Test #3"); // Проверка симметричности equals

        // Test phase #2

        Pair<String, String> pair3 = Pair.of("id0", "hello");

        String s1 = pair3.getFirst(); // 1
        String s2 = pair3.getSecond(); // "hello"

        Pair<String, String> pair4 = Pair.of("id1", "hi");

        assertEquals( pair3.equals(pair4), false, "Test #4");
        assertEquals( pair4.equals(pair3), false, "Test #5"); // Проверка симметричности equals
    }

    private static <F, S> Pair<F, S> of(F first, S second) {
        return null;
    }

    private S getSecond() {
        return null;
    }

    private F getFirst() {
        return null;
    }

}
