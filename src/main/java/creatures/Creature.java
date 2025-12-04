package creatures;

import locations.Location;
import objects.WorldObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Creature extends WorldObject {
    protected final CreatureType type;
    protected ArrayList<CreatureProperty> properties;
    protected ArrayList<Buff> buffs;
    protected String birthPlanet; // TODO object planet
    protected Location currentLocation;
    protected boolean isAlive;

    public Creature(String title, LocalDateTime creationTime, String description, String birthPlanet,
                    ArrayList<Buff> buffs, Location currentLocation, boolean isAlive,
                    ArrayList<CreatureProperty> properties, CreatureType type) {
        super(title, creationTime, description);
        this.birthPlanet = birthPlanet;
        this.buffs = buffs;
        this.currentLocation = currentLocation;
        this.isAlive = isAlive;
        this.properties = properties;
        this.type = type;
    }

    @Override
    public void interact(WorldObject object) {
        if (object instanceof Creature creature){
            System.out.println(this.title + "feasted on " + creature.getTitle());
            creature.addBuff(new Buff(BuffType.WOUND, 0.3));
        }
        else {
            System.out.println(this.title + "tried to bite " + object.getTitle());
            this.addBuff(new Buff(BuffType.POISON, 10));
        }
    }

    // Setters
    public void addBuff(Buff buff){
        buffs.add(Objects.requireNonNull(buff));
    }

    public void removeBuff(Buff buff){
        buffs.remove(Objects.requireNonNull(buff));
    }

    public void setBirthPlanet(String birthPlanet) {
        this.birthPlanet = Objects.requireNonNull(birthPlanet);
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = Objects.requireNonNull(buffs);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = Objects.requireNonNull(currentLocation);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setProperties(ArrayList<CreatureProperty> properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    // Getters
    public String getBirthPlanet() {
        return birthPlanet;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public ArrayList<CreatureProperty> getProperties() {
        return properties;
    }

    public CreatureType getType() {
        return type;
    }


    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Creature\ntitle: %s\ncreationTime: %s\ndescription: %s\nType: %s\nProperties: %s\nBuffs: %s\nBirth Planet: %s\nCurrent Location: %s\nAlive: %b",
                title, creationTime, description, type, properties, buffs,
                birthPlanet, currentLocation, isAlive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Creature creature = (Creature) o;
        return super.equals(o) && type == creature.type &&
                isAlive == creature.isAlive &&
                Objects.equals(properties, creature.properties) &&
                Objects.equals(buffs, creature.buffs) &&
                Objects.equals(birthPlanet, creature.birthPlanet) &&
                Objects.equals(currentLocation, creature.currentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), title, creationTime, description,
                birthPlanet, buffs, currentLocation, isAlive, type, properties);
    }
}
