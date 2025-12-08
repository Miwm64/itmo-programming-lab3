package world;

import creatures.*;
import locations.Forest;
import locations.Location;
import locations.Mountain;
import objects.Backpack;
import objects.WorldObjectDivisible;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyWorld extends World {
    protected Conditions currentConditions = new Conditions(Weather.CLOUDY, LocalDateTime.of(2539, 4, 2, 13, 42), Effect.FOG);

    @Override
    public void simulateScenario() {
        Planet barrayar = new Planet("Barrayar", WormholeStatus.ACTIVE_SINGLE, PlanetType.NATURAL, Faction.BARRAYARAN_IMPERIUM);
        Planet betaColony = new Planet("Beta Colony", WormholeStatus.ACTIVE_MULTIPLE,
                PlanetType.METAL_JUNGLES, Faction.BARRAYARAN_IMPERIUM);
        Location currLocation = new Mountain("Unknown", "", 200);

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Butcher of Komarr");
        Human aralVorkosigan = new Human.Builder("Vorkosigan",
                new Name("Aral", "Xav", "Vorkosigan", nicks),
                LocalDateTime.of(2497, 7, 18, 0, 0))
                .faction(Faction.BARRAYARAN_IMPERIUM)
                .birthPlanet(barrayar)
                .currentLocation(currLocation).build();

        Human cordeliaNaismith = new Human.Builder("Cordelia",
                new Name("Cordelia", "", "Naismith", new ArrayList<>()),
                LocalDateTime.of(2500, 5, 3, 0, 0))
                .faction(Faction.BETA_COLONY)
                .birthPlanet(betaColony)
                .currentLocation(currLocation).build();

        Human dubauer = new Human.Builder("Ensign Dubauer",
                new Name("", "", "Dubauer"),
                LocalDateTime.of(2504, 2, 25, 0, 0))
                .addBuff(new Buff(BuffType.MEMORY_DAMAGE, 75))
                .knownHumans(new java.util.HashSet<>(Arrays.asList(cordeliaNaismith))).build();

        WorldObjectDivisible meagerSpoils = new WorldObjectDivisible("Meager spoils",
                LocalDateTime.now(), "from camp", 100);

        WorldObjectDivisible meagerSpoils2 = meagerSpoils.divide(50);
        aralVorkosigan.wearBackpack(new Backpack());
        aralVorkosigan.getBackpack().addItem(meagerSpoils);
        cordeliaNaismith.wearBackpack(new Backpack());
        cordeliaNaismith.getBackpack().addItem(meagerSpoils2);


        HumanEngagementSimulator.lead(aralVorkosigan, new ArrayList<>(Arrays.asList(cordeliaNaismith, dubauer)),
                new Forest("Unknown", "", 50, 19));
        HumanEngagementSimulator.hold(cordeliaNaismith, dubauer);
        HumanEngagementSimulator.avoid(dubauer, aralVorkosigan);
        System.out.println(dubauer.recognise(cordeliaNaismith));
    }
}
