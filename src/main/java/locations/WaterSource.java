package locations;

import creatures.Buff;
import util.Pair;

public class WaterSource {
    private double isSafeToDrink;
    private double waterLeftAmount;

    public WaterSource(double isSafeToDrink, double waterLeftAmount){
        this.isSafeToDrink= isSafeToDrink;
        this.waterLeftAmount = waterLeftAmount;
    }

    public util.Pair<Double, Buff> getWater(double requestedAmount) {
        double givenAmount = Math.min(waterLeftAmount, requestedAmount);
        waterLeftAmount -= givenAmount;
        // TODO add buff
        return new Pair<>(givenAmount, null);
    }

    // Getters
    public double getWaterLeftAmount() {
        return waterLeftAmount;
    }

    public double getIsSafeToDrink() {
        return isSafeToDrink;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: WaterSource\nsafety: %f\nwaterLeftAmount: %f",
                isSafeToDrink, waterLeftAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaterSource that = (WaterSource) o;
        return Double.compare(that.isSafeToDrink, isSafeToDrink) == 0 &&
                Double.compare(that.waterLeftAmount, waterLeftAmount) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(isSafeToDrink, waterLeftAmount);
    }


}
