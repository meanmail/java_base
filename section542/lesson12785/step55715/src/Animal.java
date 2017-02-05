// Don't edit this file

import java.io.Serializable;
import java.util.Objects;

/**
 * @author meanmail
 */
class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}
