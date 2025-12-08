package locations;

import objects.WorldObject;
import java.util.ArrayList;

public class Mountain extends Location {
    protected double height;

    // Constructors
    public Mountain(String name, String description, double height){
        this(name, description, height, new ArrayList<>());
    }

    public Mountain(String name, String description, double height,
                    ArrayList<WorldObject> objects){
        super(name, LocationType.MOUNTAIN, description, objects);
        this.height = height;
    }

    // Getters
    public double getHeight() {
        return height;
    }

    // Setters
    public void setHeight(double height) {
        this.height = height;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Mountain\nname: %s\ntype: %s\ndescription: %s\nobjects: %s\nHeight: %f",
                name, type, description, objects, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mountain)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}