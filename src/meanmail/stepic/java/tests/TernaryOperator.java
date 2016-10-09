package meanmail.stepic.java.tests;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static meanmail.stepic.java.tests.Assertions.assertEquals;

/**
 * Created by Пользователь on 10.10.2016.
 *
 * Дан предикат condition и две функции ifTrue и ifFalse.
 * Напишите метод ternaryOperator, который из них построит новую функцию,
 * возвращающую значение функции ifTrue, если предикат выполнен,
 * и значение ifFalse иначе.
 */
public class TernaryOperator {

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        Predicate<Object> condition = Objects::isNull;

        Function<Object, Integer> ifTrue = obj -> 0;

        Function<CharSequence, Integer> ifFalse = CharSequence::length;

        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        assertEquals(safeStringLength.apply(null), 0, "Test #1");
        assertEquals(safeStringLength.apply("test"), 4, "Test #2");

    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        // Измените код в этом методе так, чтобы он работал правильно

        return t -> ifTrue.apply(t);

    }
}
