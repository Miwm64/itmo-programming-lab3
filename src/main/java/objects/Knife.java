package objects;

import java.time.LocalDateTime;

public class Knife extends Equipment {
    // Constructors
    public Knife(int durability){
        this("Knife", LocalDateTime.now(), "", durability);
    }

    public Knife(String title, LocalDateTime creationTime, String description,
                     int durability){
        super(title, creationTime, description, EquipmentType.WEAPON, durability);
    }
/*
    public void useWith(WorldObject object) {
        // TODO implement
    }
*/
/*
    public void stab(WorldObject object) {
        // TODO implement
    }
*/
/*
    public void cutThrough(WorldObject object) {
        // TODO implement
    }
*/
    // Java object methods

    @Override
    public Knife clone() {
        return (Knife) super.clone();
    }

    @Override
    public String toString() {
        return String.format("type: Knife\ntitle: %s\ncreationTime: %s\ndescription: %s\nType: %s\nDurability:%d",
                title, creationTime, description, type.toString(), durability);
    }
}
