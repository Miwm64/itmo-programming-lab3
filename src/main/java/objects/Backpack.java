package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Backpack extends WorldObject{
    protected ArrayList<WorldObject> items;

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
        return items;
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
        items.remove(object);
    }
}
