package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Backpack extends WorldObject{
    protected ArrayList<WorldObject> items = new ArrayList<>();

    // Constructor
    public Backpack(){
        this("", LocalDateTime.now(), "");
    }

    public Backpack(String title, LocalDateTime time, String description) {
        super(title, time, description);
    }

    // Getters
    public WorldObject getItem(int index){
        return items.get(index);
    }

    public ArrayList<WorldObject> getItems(){
        return new ArrayList<>(items);
    }

    // It returns an item and remove it from array
    public WorldObject takeItem(int index){
        WorldObject item = items.get(index);
        items.remove(index);
        return item;
    }

    // Setters
    public void removeItem(int index){
        items.remove(index);
    }

    public void removeItem(WorldObject object){
        items.remove(Objects.requireNonNull(object));
    }

    public void addItem(WorldObject object){
        items.add(Objects.requireNonNull(object));
    }

    public void setItems(ArrayList<WorldObject> objects){
        items = Objects.requireNonNull(objects);
    }

    // Java object methods
    @Override
    public Backpack clone() {
        Backpack clone = (Backpack) super.clone();
        clone.items = new ArrayList<>(items);
        return clone;
    }

    @Override
    public String toString() {
        return String.format("type: Backpack\ntitle: %s\ncreationTime: %s\ndescription: %s\nItems: %s",
                title, creationTime, description, items.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o) && Objects.equals(items, ((Backpack) o).items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), title, creationTime, description, items);
    }
}
