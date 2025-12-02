package objects;

import java.time.LocalDateTime;
import java.util.Objects;

public class Tree extends WorldObject{
    private double height; // height in meters

    // Constructors
    public Tree(){
        this("", LocalDateTime.now(), "", 0);
    }

    public Tree(String title, LocalDateTime time, String description, double height) {
        super(title, time, description);
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
    public Tree clone() {
        return (Tree) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Tree\ntitle: %s\ncreationTime: %s\ndescription: %s\nheight: %.2f",
                title, creationTime, description, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;

        Tree that = (Tree) o;
        return height == that.height;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }
}
