package objects;

import objects.Moss;
import objects.Stone;
import objects.Sunlight;

import java.time.LocalDateTime;

public class SunlightTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        Sunlight sunlight = new Sunlight("Morning Sun", now, "Bright morning light");
        Stone stone = new Stone("Diamond", now, "Sparkly gem");
        Moss moss = new Moss("Forest Moss", now, "Green moss");

        System.out.println("Testing Sunlight interactions:");
        sunlight.shineUpon(stone);  // "Stone Diamond shines"
        sunlight.shineUpon(moss);   // "Moss Forest Moss shines"

        // Test cloning
        Sunlight cloned = sunlight.clone();
        System.out.println("Clone type: " + cloned.getClass().getSimpleName());  // Sunlight
        System.out.println("Clone equals original: " + sunlight.equals(cloned));  // true

        // Test equals/hashCode
        Sunlight sameSunlight = new Sunlight("Morning Sun", now, "Bright morning light");
        System.out.println("Equals: " + sunlight.equals(sameSunlight));  // true
        System.out.println("Same hash: " +
                (sunlight.hashCode() == sameSunlight.hashCode()));  // true
    }
}