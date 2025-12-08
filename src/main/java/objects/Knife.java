package objects;

import creatures.Buff;
import creatures.BuffType;
import creatures.Creature;
import locations.Forest;
import locations.Location;
import locations.LocationType;

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

    public boolean useWith(WorldObject object) {
        if (durability == 0){
            return false;
        }
        durability--;
        if (object instanceof Creature creature){
            creature.addBuff(new Buff(BuffType.WOUND, 20));
        }
        return true;
    }


    public boolean cutThrough(Location location) {
        if (durability == 0 || location.getType() == LocationType.CLOUD) {
            return false;
        }
        if (location instanceof Forest forest){
            forest.setThickness(forest.getThickness()*0.75);
        }
        return true;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knife)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
