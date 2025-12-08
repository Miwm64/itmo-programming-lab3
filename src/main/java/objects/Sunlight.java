package objects;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sunlight extends WorldObject {
    // Constructors
    public Sunlight(){
        this("", LocalDateTime.now(), "");
    }

    public Sunlight(String title, LocalDateTime creationTime, String description) {
        super(title, creationTime, description);
    }

    public void shineUpon(Shineable object) {
        Objects.requireNonNull(object);
        object.shine();
    }

    // Java object methods
    @Override
    public Sunlight clone() {
        return (Sunlight) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Sunlight\ntitle: %s\ncreationTime: %s\ndescription: %s",
                title, creationTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sunlight)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
