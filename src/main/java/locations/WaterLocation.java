package locations;

import creatures.Buff;
import objects.WorldObject;
import util.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class WaterLocation extends Location{
    protected WaterSource waterSource;

    // Constructors
    // you MUST set water source via setWaterSource()
    public WaterLocation(String name, String description){
        this(name, description, new ArrayList<>());
    }

    public WaterLocation(String name, String description, ArrayList<WorldObject> objects){
        super(name, LocationType.WATER, description, objects);
    }

    // Getters
    public WaterSource getWaterSource(){
        return waterSource;
    }

    public util.Pair<Double, Buff> getWater(double requestedAmound) {
        return waterSource.getWater(requestedAmound);
    }

    // Setters
    public void setWaterSource(WaterSource source){
        waterSource = source;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: WaterLocation\nname: %s\ntype: %s\ndescription: %s\nobjects: %s\nWaterSource: %s",
                name, type, description, objects, waterSource);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaterLocation that = (WaterLocation) o;
        return super.equals(o) && Objects.equals(waterSource, that.waterSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), waterSource);
    }
}
