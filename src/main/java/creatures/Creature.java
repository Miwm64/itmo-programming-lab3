package creatures;

import locations.Location;
import objects.WorldObject;
import world.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Creature extends WorldObject {
    protected final CreatureType type;
    protected ArrayList<CreatureProperty> properties;
    protected ArrayList<Buff> buffs;
    protected Planet birthPlanet;
    protected Location currentLocation;
    protected boolean isAlive;

    // Constructor for builder
    private Creature(Builder builder) {
        super(builder.title, builder.creationTime, builder.description);
        this.birthPlanet = builder.birthPlanet;
        this.buffs = builder.buffs;
        this.currentLocation = builder.currentLocation;
        this.isAlive = builder.isAlive;
        this.properties = builder.properties;
        this.type = builder.type;
    }

    public Creature(String title, LocalDateTime creationTime, String description, CreatureType type, ArrayList<CreatureProperty> properties, boolean isAlive, Location currentLocation, ArrayList<Buff> buffs, Planet birthPlanet) {
        super(title, creationTime, description);
        this.type = type;
        this.properties = properties;
        this.isAlive = isAlive;
        this.currentLocation = currentLocation;
        this.buffs = buffs;
        this.birthPlanet = birthPlanet;
    }

    // Builder class
    public static class Builder {
        private String title;
        private LocalDateTime creationTime;
        private String description = "";
        private Planet birthPlanet;
        private ArrayList<Buff> buffs = new ArrayList<>();
        private Location currentLocation = null;
        private boolean isAlive = true;
        private ArrayList<CreatureProperty> properties = new ArrayList<>();
        private CreatureType type;

        public Builder(String title, LocalDateTime creationTime, CreatureType type) {
            this.title = Objects.requireNonNull(title);
            this.creationTime = Objects.requireNonNull(creationTime);
            this.type = type;
        }

        public Builder birthPlanet(Planet planet){
            this.birthPlanet = planet;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder buffs(ArrayList<Buff> buffs) {
            this.buffs = buffs;
            return this;
        }

        public Builder addBuff(Buff buff) {
            this.buffs.add(buff);
            return this;
        }

        public Builder currentLocation(Location currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public Builder isAlive(boolean isAlive) {
            this.isAlive = isAlive;
            return this;
        }

        public Builder properties(ArrayList<CreatureProperty> properties) {
            this.properties = properties;
            return this;
        }

        public Builder addProperty(CreatureProperty property) {
            this.properties.add(property);
            return this;
        }

        public Creature build() {
            if (title == null || title.isEmpty()) {
                throw new IllegalStateException("Title is required");
            }
            if (creationTime == null) {
                throw new IllegalStateException("Creation time is required");
            }
            if (type == null) {
                throw new IllegalStateException("Creature type is required");
            }

            return new Creature(this);
        }
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

    public void setBirthPlanet(Planet birthPlanet) {
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
    public Planet getBirthPlanet() {
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
                Objects.equals(birthPlanet, creature.birthPlanet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), birthPlanet, type);
    }
}
