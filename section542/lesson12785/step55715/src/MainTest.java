// Don't edit this file

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author meanmail
 */
public class MainTest {
    private static Class<?> mainClass;
    private static Method deserializeAnimalArray;

    @BeforeClass
    public static void beforeClass() {
        mainClass = TestUtils.getUserClass("Main");
        deserializeAnimalArray = TestUtils.getMethod(mainClass,
                "deserializeAnimalArray",
                new int[]{Modifier.PUBLIC | Modifier.STATIC},
                Animal[].class,
                byte[].class);
    }

    private static byte[] serialize(int count, Object[] objects) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);

        out.writeInt(count);

        for (Object object : objects) {
            out.writeObject(object);
        }

        out.flush();
        return byteStream.toByteArray();
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalZero() throws Throwable {
        Animal[] animal = new Animal[0];

        byte[] data = serialize(0, animal);

        Animal[] animals = (Animal[]) TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);

        assertNotNull("Main.deserializeAnimalArray([zero animal]) return null", animals);
        assertEquals("Main.deserializeAnimalArray([zero animal]).length", 0, animals.length);
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalOne() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(1, animal);

        Animal[] animals = (Animal[]) TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);

        assertNotNull("Main.deserializeAnimalArray([one animal]) return null", animals);
        assertEquals("Main.deserializeAnimalArray([one animal]).length", 1, animals.length);
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalThee() throws Throwable {

        Animal[] animal = new Animal[]{
                new Animal("Cat"),
                new Animal("Dog"),
                new Animal("Mouse")
        };

        byte[] data = serialize(animal.length, animal);

        Animal[] animals = (Animal[]) TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);

        assertNotNull("Main.deserializeAnimalArray([three animal]) return null", animals);
        assertEquals("Main.deserializeAnimalArray([three animal]).length", animal.length, animals.length);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayNotAnimal() throws Throwable {
        Object[] animal = new Object[]{
                new Integer(100)
        };

        byte[] data = serialize(animal.length, animal);

        TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayWrongCount() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(2, animal);

        TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayIncorrectCount() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(-10, animal);

        TestUtils.invokeMethod(mainClass, deserializeAnimalArray, (Object) data);
    }

}