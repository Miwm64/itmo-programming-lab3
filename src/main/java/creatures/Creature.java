package creatures;

import locations.Location;
import objects.WorldObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Creature extends WorldObject {
    private final CreatureType type;
    private ArrayList<CreatureProperty> properties;
    private ArrayList<Buff> buffs;
    private String birthPlanet; // TODO object planet
    private Location currentLocation;
    private boolean isAlive;

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
        if (object instanceof Creature){
            Creature creature = (Creature) object;
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
        buffs.add(buff);
    }

    public void setBirthPlanet(String birthPlanet) {
        this.birthPlanet = birthPlanet;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = buffs;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setProperties(ArrayList<CreatureProperty> properties) {
        this.properties = properties;
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

}
