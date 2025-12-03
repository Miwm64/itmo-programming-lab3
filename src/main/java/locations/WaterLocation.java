package locations;

import objects.WorldObject;
import util.Pair;

import java.util.ArrayList;

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
}
