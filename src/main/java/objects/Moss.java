package objects;

import java.time.LocalDateTime;
import java.util.Objects;

public class Moss extends WorldObject implements Shineable {
    // Constructors
    public Moss() {
        this("", LocalDateTime.now(), "");
    }

    public Moss(String title, LocalDateTime creationTime, String description) {
        super(title, creationTime, description);
    }

    @Override
    public void shine() {
        System.out.printf("Moss %s shines\n", title);
    }

    // Java object methods
    @Override
    public Moss clone() {
        return (Moss) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Moss\ntitle: %s\ncreationTime: %s\ndescription: %s",
                title, creationTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), title, creationTime, description);
    }
}