package objects;

import java.time.LocalDateTime;

public class ShineableTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        Rill rill = new Rill("Small Stream", now, "A tiny water stream");
        Moss moss = new Moss("Forest Moss", now, "Green moss on rocks");
        Canopy canopy = new Canopy("Tree Canopy", now, "Leafy overhead cover");
        Stone stone = new Stone("Granite", now, "Hard rock");

        rill.shine();
        moss.shine();
        canopy.shine();
        stone.shine();

        Rill rillClone = rill.clone();
        System.out.println(rillClone.getClass().getSimpleName());

        Moss moss1 = new Moss("Moss", now, "Green");
        Moss moss2 = new Moss("Moss", now, "Green");
        System.out.println(moss1.equals(moss2));
        System.out.println(moss1.hashCode() == moss2.hashCode());

        System.out.println(moss1.equals(rill));
    }
}
