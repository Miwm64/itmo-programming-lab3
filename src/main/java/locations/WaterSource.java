package locations;

import creatures.Buff;
import creatures.BuffType;
import util.Pair;

import java.util.Objects;

public class WaterSource {
    private final double isSafeToDrink; // 1 - safe, 0 - not safe
    private double waterLeftAmount;

    public WaterSource(double isSafeToDrink, double waterLeftAmount){
        this.isSafeToDrink= isSafeToDrink;
        this.waterLeftAmount = waterLeftAmount;
    }

    public Pair<Double, Buff> getWater(double requestedAmount) {
        double givenAmount = Math.min(waterLeftAmount, requestedAmount);
        waterLeftAmount -= givenAmount;
        Buff buff = null;
        if (isSafeToDrink < Math.random()){
            buff = new Buff(BuffType.POISON, 1-isSafeToDrink*100);
        }
        return new Pair<>(givenAmount, buff);
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
        return Objects.hash(isSafeToDrink, waterLeftAmount);
    }
}
