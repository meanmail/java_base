/**
 * Created by Пользователь on 30.09.2016.
 */
public class Assertions {

    public static void assertEquals(int value, int expected, String msg) {

        if (value != expected)
            System.out.printf("%s: Error! Expected %d, got %d.\n", msg, expected, value);
        else
            System.out.printf("%s: Ok\n", msg);
    }
}
