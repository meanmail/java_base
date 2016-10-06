package meanmail.stepic.java.tests;

import java.io.*;
import java.util.Objects;

import static meanmail.stepic.java.tests.Assertions.assertEquals;

/**
 * Created by meanmail on 06.10.2016.
 */

class Animal implements Serializable {

    public static Animal[] deserializeAnimalArray(byte[] data){

        // Paste your a code here

        return new Animal[]{};
    }

    private final String name;

    public Animal(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        Animal[] cats = new Animal[] {new Animal("Cat")};

        /* tests phase #0: stream is correct*/

        Animal[] animals = test(1, cats);

        assertEquals(animals, cats, "Test #1");


        /* tests phase #1: stream incorrect */

        Class<?> exceptionClass = null;

        try {

            test(1, new Object[]{new Integer(100)});

        } catch (Throwable e) {

            exceptionClass = e.getClass();
        }

        assertEquals(exceptionClass, IllegalArgumentException.class, "Test #2");


        exceptionClass = null;

        try {

            test(-10, cats);

        } catch (Throwable e) {

            exceptionClass = e.getClass();
        }

        assertEquals(exceptionClass, IllegalArgumentException.class, "Test #3");


    }

    private static Animal[] test(int count, Object[] animals) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        ObjectOutputStream out = new ObjectOutputStream(byteStream);

        out.writeInt(count);

        for (Object animal : animals) {

            out.writeObject(animal);
        }

        out.flush();

        return deserializeAnimalArray(byteStream.toByteArray());
    }

}
