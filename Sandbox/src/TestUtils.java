import com.sun.istack.internal.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author meanmail
 */
class TestUtils {
    static Class<?> getUserClass(@NotNull String name) {
        Class<?> pairClass = null;
        try {
            pairClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            fail(String.format("Class %s did't found", name));
        }
        return pairClass;
    }

    static void runMain(@NotNull ClassLoader classLoader) {
        Class<?> mainClass = getUserClass("Main");

        Method main = getMethod(mainClass, "main", Modifier.PUBLIC | Modifier.STATIC, String[].class);

        invokeMethod(mainClass, main);
    }

    static Object invokeMethod(@NotNull Object object, @NotNull Method method, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            String argsAsString = Arrays.stream(args).map(Object::toString).collect(joining(", "));
            fail(String.format("Can't run %s.%s(%s)", object.getClass().getSimpleName(), method.getName(), argsAsString));
        }
        return null;
    }

    static Method getMethod(@NotNull Class<?> clazz, @NotNull String name, int modifiers, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            fail(String.format("%s.%s() did't found", clazz.getSimpleName(), name));
        }

        String message = String.format("%s.%s() should be %s", clazz.getSimpleName(), name, Modifier.toString(modifiers));
        assertEquals(message, Modifier.toString(modifiers), Modifier.toString(method.getModifiers()));
        return method;
    }

    static Constructor<?> getConstructor(@NotNull Class<?> clazz, int modifiers, Class<?>... parameterTypes) {
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            fail(String.format("%s() did't found", clazz.getSimpleName()));
        }

        String message = String.format("%s() should be %s", clazz.getSimpleName(), Modifier.toString(modifiers));
        assertEquals(message, Modifier.toString(modifiers), Modifier.toString(constructor.getModifiers()));
        return constructor;
    }
}
