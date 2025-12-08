package objects;

import java.time.LocalDateTime;

public class Canopy extends WorldObject implements Shineable {
    // Constructors
    public Canopy() {
        this("", LocalDateTime.now(), "");
    }

    public Canopy(String title, LocalDateTime creationTime, String description) {
        super(title, creationTime, description);
    }

    @Override
    public void shine() {
        System.out.printf("Canopy %s shines\n", title);
    }

    // Java object methods
    @Override
    public Canopy clone() {
        return (Canopy) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Canopy\ntitle: %s\ncreationTime: %s\ndescription: %s",
                title, creationTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Canopy)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}