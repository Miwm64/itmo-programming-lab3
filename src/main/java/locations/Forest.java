package locations;

import objects.Rill;
import objects.WorldObject;
import java.util.ArrayList;
import java.util.Objects;

public class Forest extends Location {
    protected double thickness;
    protected double height;

    // Constructors
    public Forest(String name, String description, double thickness, double height){
        this(name, description, thickness, height, new ArrayList<>());
    }

    public Forest(String name, String description, double thickness, double height,
                  ArrayList<WorldObject> objects){
        super(name, LocationType.FOREST, description, objects);
        this.thickness = thickness;
        this.height = height;
    }

    public void cut() {
        thickness *= 0.9d;
    }

    // Getters
    public double getThickness() {
        return thickness;
    }

    public double getHeight() {
        return height;
    }

    // Setters
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Forest\nname: %s\ntype: %s\ndescription: %s\nobjects: %s\nThickness: %f\nHeight: %f",
                name, type, description, objects, thickness, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Forest)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}