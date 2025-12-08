package creatures;

public class Buff {
    public final BuffType type;
    public final double strength;

    public Buff(BuffType type, double strength){
        this.type = type;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return String.format("type: Buff\ntype: %s\n strength: %.2f", type, strength);
    }
}
