package objects;

public abstract class EquipmentFactory {
    public abstract Equipment create(String title, String description, int durability);
    public abstract Equipment create(int durability);  // Default version
}