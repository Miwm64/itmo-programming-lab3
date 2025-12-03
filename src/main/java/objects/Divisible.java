package objects;

import java.util.ArrayList;

public interface Divisible {
    WorldObjectDivisible divide(double percentageLeft);
    ArrayList<WorldObjectDivisible> divide(ArrayList<Double> percentageLeft);
}
