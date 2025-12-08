package creatures;

import locations.Location;
import objects.WorldObject;
import world.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class GasFilledCreature extends Creature{
    // Constructors
    public GasFilledCreature(String title, LocalDateTime creationTime, String description, Planet birthPlanet, ArrayList<Buff> buffs, Location currentLocation, boolean isAlive) {
        super(title, creationTime, description, CreatureType.INSECT, new ArrayList<>(),
                isAlive, currentLocation, new ArrayList<>(), birthPlanet);
        this.properties.add(CreatureProperty.RADIAL_SYMMETRY);
    }

    // Constructor for builder
    private GasFilledCreature(GasFilledCreature.Builder builder) {
        super(builder.title, builder.creationTime, builder.description, builder.type, builder.properties,
                builder.isAlive, builder.currentLocation, builder.buffs, builder.birthPlanet);
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

        public GasFilledCreature.Builder birthPlanet(Planet planet){
            this.birthPlanet = planet;
            return this;
        }

        public GasFilledCreature.Builder description(String description) {
            this.description = description;
            return this;
        }

        public GasFilledCreature.Builder buffs(ArrayList<Buff> buffs) {
            this.buffs = buffs;
            return this;
        }

        public GasFilledCreature.Builder addBuff(Buff buff) {
            this.buffs.add(buff);
            return this;
        }

        public GasFilledCreature.Builder currentLocation(Location currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public GasFilledCreature.Builder isAlive(boolean isAlive) {
            this.isAlive = isAlive;
            return this;
        }

        public GasFilledCreature.Builder properties(ArrayList<CreatureProperty> properties) {
            this.properties = properties;
            return this;
        }

        public GasFilledCreature.Builder addProperty(CreatureProperty property) {
            this.properties.add(property);
            return this;
        }

        public GasFilledCreature build() {
            if (title == null || title.isEmpty()) {
                throw new IllegalStateException("Title is required");
            }
            if (creationTime == null) {
                throw new IllegalStateException("Creation time is required");
            }
            if (type == null) {
                throw new IllegalStateException("Creature type is required");
            }

            return new GasFilledCreature(this);
        }
    }

    @Override
    public void interact(WorldObject object){
        Objects.requireNonNull(object);
        if (object instanceof Creature creature){
            creature.addBuff(new Buff(BuffType.SLEEP, 30));

        }
        else{
            super.interact(object);
        }
    }
}
