package objects;

import java.time.LocalDateTime;
import java.util.Objects;

public class Rill extends WorldObject implements Shineable {
    // Constructors
    public Rill() {
        this("", LocalDateTime.now(), "");
    }

    public Rill(String title, LocalDateTime creationTime, String description) {
        super(title, creationTime, description);
    }

    @Override
    public void shine() {
        System.out.printf("Rill %s shines\n", title);
    }

    // Java object methods
    @Override
    public Rill clone() {
        return (Rill) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Rill\ntitle: %s\ncreationTime: %s\ndescription: %s",
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