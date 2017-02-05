// Don't edit this file

import com.sun.istack.internal.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

/**
 * @author meanmail
 */
public class TestUtils {
    public static Class<?> getUserClass(@NotNull String name) {
        Class<?> pairClass = null;
        try {
            pairClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            fail(String.format("Class %s did't found", name));
        }
        return pairClass;
    }

    public static void runMain(InputStream in, PrintStream out) throws Throwable {
        if (in != null) {
            System.setIn(in);
        }
        if (out != null) {
            System.setOut(out);
        }

        Class<?> mainClass = getUserClass("Main");

        Method main = getMethod(mainClass, "main",
                new int[]{Modifier.PUBLIC | Modifier.STATIC | Modifier.TRANSIENT, Modifier.PUBLIC | Modifier.STATIC},
                Void.TYPE,
                String[].class);

        invokeMethod(mainClass, main, (Object) new String[0]);
    }

    public static Object invokeMethod(@NotNull Object object, @NotNull Method method, Object... args) throws Throwable {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            String argsAsString = Arrays.stream(args).map(Object::toString).collect(joining(", "));
            fail(String.format("Can't run %s.%s(%s)", object.getClass().getSimpleName(), method.getName(), argsAsString));
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
        return null;
    }

    public static Method getMethod(@NotNull Class<?> clazz, @NotNull String name, int[] modifiers, Class<?> returnType, Class<?>... parameterTypes) {
        String methodName = String.format("%s.%s", clazz.getSimpleName(), name);
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
            assertEquals(String.format("Method %s return type", methodName), returnType, method.getReturnType());
        } catch (NoSuchMethodException e) {
            String argsAsString = Arrays.stream(parameterTypes)
                    .map(Class::getSimpleName)
                    .collect(joining(", "));
            fail(String.format("%s(%s) did't found", methodName, argsAsString));
        }

        checkModifiers(clazz, modifiers, method.getModifiers(), "Method");
        return method;
    }

    private static String modifierToString(int modifiers) {
        if (modifiers == 0) {
            return "package-private";
        }
        return Modifier.toString(modifiers);
    }


    public static Constructor<?> getConstructor(@NotNull Class<?> clazz, int[] modifiers, Class<?>... parameterTypes) {
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            String parameters = Arrays.stream(parameterTypes)
                    .map(Class::getSimpleName)
                    .collect(joining(", "));
            fail(String.format("Constructor %s(%s) did't found", clazz.getSimpleName(), parameters));
        }

        checkModifiers(clazz, modifiers, constructor.getModifiers(), "Constructor");
        return constructor;
    }

    private static void checkModifiers(@NotNull Class<?> clazz, int[] expectedModifiers, Integer modifiers, String objectName) {
        Set<Integer> modifierList = Arrays.stream(expectedModifiers)
                .mapToObj(Integer::new)
                .collect(toSet());

        String modifiersList = modifierList.stream()
                .map(TestUtils::modifierToString)
                .collect(Collectors.joining("|"));

        String message = String.format("%s %s() should be %s", objectName, clazz.getSimpleName(), modifiersList);
        assertTrue(message, modifierList.contains(modifiers));
    }

    public static Object newInstance(Constructor<?> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(String.format("%s() failed new instance", constructor.getName()));
        }
        return null;
    }

    public static Class<?> getInnerClass(Class<?> outerClass, String innerClassName) {
        List<Class<?>> classes = Arrays.stream(outerClass.getDeclaredClasses())
                .filter(clazz -> clazz.getSimpleName().equals(innerClassName))
                .collect(Collectors.toList());

        if (classes.size() == 0) {
            fail(String.format("%s.%s did't found", outerClass.getSimpleName(), innerClassName));
        }
        return classes.get(0);
    }

    public static void assertOutputEquals(String expected) throws Throwable {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            runMain(null, new PrintStream(outStream));
            assertEquals(expected, outStream.toString());
        }
    }
}
