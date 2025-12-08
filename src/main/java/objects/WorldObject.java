package objects;

import creatures.Creature;

import java.time.LocalDateTime;
import java.util.Objects;


public class WorldObject implements Cloneable {
    protected String title;
    protected final LocalDateTime creationTime;
    protected String description;


    // Constructors
    public WorldObject(){
        this("", LocalDateTime.now(), "");
    }

    public WorldObject(String title, LocalDateTime creationTime, String description){
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.creationTime = Objects.requireNonNull(creationTime, "Creation time cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
    }

    public void interact(WorldObject object){
        System.out.printf("%s interacted with %s%n", this.getTitle(), object.getTitle());
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public String getDescription() {
        return description;
    }

    // Setters
    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "Description cannot be null");
    }

    // Java object methods
    @Override
    public WorldObject clone() {
        try {
            if (this instanceof Creature){
                throw new CloneNotSupportedException("Creatures have unique identities and cannot be cloned");
            }
            return (WorldObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone must not fail, yet it failed",e);
        }
    }

    @Override
    public String toString() {
        return String.format("type: WorldObject\ntitle: %s\ncreationTime: %s\ndescription: %s",
                title, creationTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldObject)) return false;

        WorldObject that = (WorldObject) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creationTime);
    }
}
