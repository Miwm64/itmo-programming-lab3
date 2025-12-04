package creatures;

import locations.Location;
import objects.WorldObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class GasFilledCreature extends Creature{
    // Constructors
    public GasFilledCreature(String title, LocalDateTime creationTime, String description, String birthPlanet, ArrayList<Buff> buffs, Location currentLocation, boolean isAlive) {
        super(title, creationTime, description, birthPlanet, buffs,
                currentLocation, isAlive, new ArrayList<>(), CreatureType.INSECT);
        this.properties.add(CreatureProperty.RADIAL_SYMMETRY);
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
