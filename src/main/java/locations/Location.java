package locations;

import objects.Rill;
import objects.WorldObject;

import java.util.ArrayList;
import java.util.Objects;

public class Location {
    protected final String name;
    protected final LocationType type;
    protected final String description;
    protected ArrayList<WorldObject> objects;

    // Constructors
    public Location(String name, LocationType type, String description){
        this(name, type, description, new ArrayList<>());
    }

    public Location(String name, LocationType type, String description, ArrayList<WorldObject> objects){
        this.name = name;
        this.type = type;
        this.description = description;
        this.objects = objects;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ArrayList<WorldObject> getObjects() {
        return new ArrayList<>(objects);
    }

    public LocationType getType() {
        return type;
    }

    // Setters
    public void addObject(WorldObject object){
        objects.add(Objects.requireNonNull(object));
    }

    public void setObjects(ArrayList<WorldObject> objects){
        this.objects = Objects.requireNonNull(objects);
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Location\nname: %s\ntype: %s\ndescription: %s\nobjects: %s",
                name, type, description, objects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return Objects.equals(name, location.name) &&
                type == location.type &&
                Objects.equals(description, location.description) &&
                Objects.equals(objects, location.objects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description, objects);
    }
}
