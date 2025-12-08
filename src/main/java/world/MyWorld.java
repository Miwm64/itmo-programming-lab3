package world;

import creatures.*;
import locations.*;
import objects.*;

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
        Knife vorKnife = new Knife(100);
        Human aralVorkosigan = new Human.Builder("Vorkosigan",
                new Name("Aral", "Xav", "Vorkosigan", nicks),
                LocalDateTime.of(2497, 7, 18, 0, 0))
                .faction(Faction.BARRAYARAN_IMPERIUM)
                .birthPlanet(barrayar)
                .currentLocation(currLocation)
                .addEquipment(vorKnife).build();

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
                currentConditions.time(), "from camp", 100);

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

        Forest forest = (Forest) aralVorkosigan.getCurrentLocation();
        System.out.println(forest.getHeight());
        forest.setHeight(forest.getHeight()+100);
        System.out.println(forest.getHeight());

        System.out.println(forest.getThickness());
        vorKnife.cutThrough(forest);
        System.out.println(forest.getThickness());

        WaterLocation waterLocation = new WaterLocation("Stream", "");
        HumanEngagementSimulator.lead(aralVorkosigan, new ArrayList<>(Arrays.asList(cordeliaNaismith, dubauer)),
                waterLocation);
        waterLocation.addObject(new Stone());
        waterLocation.addObject(new Stone());
        waterLocation.addObject(new Stone());
        waterLocation.addObject(new Moss());
        waterLocation.addObject(new Rill());
        waterLocation.addObject(new Stone());

        for (WorldObject obj : waterLocation.getObjects()){
            if (obj instanceof Shineable shineable){
                shineable.shine();
            }
        }

        Cloud cloud = new Cloud("Cloud", "", 10);
        for (int i = 0; i < 10; ++i){
            cloud.addObject(new GasFilledCreature.Builder("" + i, currentConditions.time()).build());
        }
        ArrayList<WorldObject> creatures = cloud.getObjects();

        for (var c : creatures){
            if (c instanceof GasFilledCreature g){
                g.interact(cordeliaNaismith);
                g.interact(aralVorkosigan);
            }
        }
        System.out.println(cordeliaNaismith.getBuffs());
        System.out.println(aralVorkosigan.getBuffs());
    }
}
