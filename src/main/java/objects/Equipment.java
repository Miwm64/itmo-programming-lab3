package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Equipment extends WorldObject{
    protected EquipmentType type;
    protected int durability;

    // Constructors
    public Equipment(EquipmentType type, int durability){
        this("", LocalDateTime.now(), "", type, durability);
    }

    public Equipment(String title, LocalDateTime creationTime, String description,
                         EquipmentType type, int durability){
        super(title, creationTime, description);
        this.type = type;
        this.durability = durability;
    }

    public boolean useWith(WorldObject object) {
        System.out.printf("%s equipment tried to interact with %s\n", type.toString(), object.title);
        return true;
    }

    // Getters
    public EquipmentType getType() {
        return type;
    }

    public int getDurability() {
        return durability;
    }

    // Setter
    public void setDurability(int durability) {
        this.durability = durability;
    }

    // Java object methods
    @Override
    public Equipment clone() {
        Equipment clone = (Equipment) super.clone();
        clone.type = type;
        clone.durability = durability;
        return clone;
    }

    @Override
    public String toString() {
        return String.format("type: Equipment\ntitle: %s\ncreationTime: %s\ndescription: %s\nType: %s\nDurability:%d",
                title, creationTime, description, type.toString(), durability);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o) && Objects.equals(type, ((Equipment) o).type) &&
                Objects.equals(durability, ((Equipment) o).durability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), title, creationTime, description, type, durability);
    }

}
