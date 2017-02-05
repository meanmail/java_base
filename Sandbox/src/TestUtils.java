import com.sun.istack.internal.NotNull;

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

    static void runMain() throws Throwable {
        Class<?> mainClass = getUserClass("Main");

        Method main = getMethod(mainClass, "main",
                new int[]{Modifier.PUBLIC | Modifier.STATIC | Modifier.TRANSIENT},
                String[].class);

        invokeMethod(mainClass, main, (Object) new String[0]);
    }

    static Object invokeMethod(@NotNull Object object, @NotNull Method method, Object... args) throws Throwable {
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

    static Method getMethod(@NotNull Class<?> clazz, @NotNull String name, int[] modifiers, Class<?> returnType, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
            assertEquals(returnType, method.getReturnType());
        } catch (NoSuchMethodException e) {
            String argsAsString = Arrays.stream(parameterTypes)
                    .map(Class::getSimpleName)
                    .collect(joining(", "));
            fail(String.format("%s.%s(%s) did't found", clazz.getSimpleName(), name, argsAsString));
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


    static Constructor<?> getConstructor(@NotNull Class<?> clazz, int[] modifiers, Class<?>... parameterTypes) {
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

    static Object newInstance(Constructor<?> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(String.format("%s() failed new instance", constructor.getName()));
        }
        return null;
    }


    static Class<?> getInnerClass(Class<?> outerClass, String innerClassName) {
        List<Class<?>> classes = Arrays.stream(outerClass.getDeclaredClasses())
                .filter(clazz -> clazz.getSimpleName().equals(innerClassName))
                .collect(Collectors.toList());

        if (classes.size() == 0) {
            fail(String.format("%s.%s did't found", outerClass.getSimpleName(), innerClassName));
        }
        return classes.get(0);
    }
}
