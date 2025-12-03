package locations;

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
}
