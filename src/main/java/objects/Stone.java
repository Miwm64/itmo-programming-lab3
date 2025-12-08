package objects;

import java.time.LocalDateTime;

public class Stone extends WorldObject implements Shineable{
    // Constructors
    public Stone(){
        this("", LocalDateTime.now(), "");
    }

    public Stone(String title, LocalDateTime creationTime, String description){
        super(title, creationTime, description);
    }

    @Override
    public void shine(){
        System.out.printf("Stone %s shines\n", title);
    }

    // Java object methods
    @Override
    public Stone clone() {
        return (Stone) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Stone\ntitle: %s\ncreationTime: %s\ndescription: %s",
                title, creationTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stone)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
