package locations;

import objects.WorldObject;
import java.util.ArrayList;
import java.util.Collections;

public class Cloud extends Location {
    protected double thickness;

    // Constructors
    public Cloud(String name, String description, double thickness){
        this(name, description, thickness, new ArrayList<>());
    }

    public Cloud(String name, String description, double thickness,
                 ArrayList<WorldObject> objects){
        super(name, LocationType.CLOUD, description, objects);
        this.thickness = thickness;
    }

    public void floatMethod() {
        Collections.shuffle(objects);
    }

    public void cut() {
        thickness *= 0.9d;
        floatMethod();
    }

    // Getters
    public double getThickness() {
        return thickness;
    }

    // Setters
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    // Java object methods

    @Override
    public String toString() {
        return String.format("type: Cloud\nname: %s\ntype: %s\ndescription: %s\nobjects: %s\nThickness: %f",
                name, type, description, objects, thickness);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cloud)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}