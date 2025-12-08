package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorldObjectDivisible extends WorldObject implements Divisible {
    protected double quantity;

    public WorldObjectDivisible() {
        this("", LocalDateTime.now(), "", 0);
    }

    public WorldObjectDivisible(String title, LocalDateTime creationTime,
                                String description, double quantity) {
        super(title, creationTime, description);
        this.quantity = quantity;
    }

    @Override
    public WorldObjectDivisible divide(double percentageLeft) {
        return divide(new ArrayList<>(List.of(100 - percentageLeft))).getFirst();

    }

    @Override
    public ArrayList<WorldObjectDivisible> divide(ArrayList<Double> percentageLeft) {
        double summ = 0;
        ArrayList<WorldObjectDivisible> res = new ArrayList<>();
        for (var p : percentageLeft){
            summ += p;
            WorldObjectDivisible obj = this.clone();
            obj.setQuantity(quantity*(p/100d));
            res.addLast(obj);
        }
        if (summ > 100){
            throw new IllegalArgumentException(
                    "Division input incorrect: sum of percentages exceeds 100%");
        }
        setQuantity(quantity*((100-summ)/100));
        return res;
    }

    // Getter
    public double getQuantity() {
        return quantity;
    }

    // Setter
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Java object methods
    @Override
    public WorldObjectDivisible clone() {
        WorldObjectDivisible clone = (WorldObjectDivisible) super.clone();
        clone.quantity = quantity;
        return clone;
    }

    @Override
    public String toString() {
        return String.format("""
                type: WorldObjectDivisible
                title: %s
                creationTime: %s
                description: %s
                quantity: %s""", title, creationTime, description, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldObjectDivisible)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
