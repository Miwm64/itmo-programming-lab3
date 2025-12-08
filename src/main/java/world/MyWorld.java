package world;

import creatures.*;
import locations.*;
import objects.*;
import util.Pair;

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
                .currentLocation(currLocation)
                .build();

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
        aralVorkosigan.changeExhaustion(5);
        System.out.println(forest.getThickness());

        WaterLocation waterLocation = new WaterLocation("Stream", "");
        WaterSource waterSource = new WaterSource(0.9, 10000000);
        waterLocation.setWaterSource(waterSource);

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
                Sunlight sunlight = new Sunlight();
                sunlight.shineUpon(shineable);
            }
        }

        Cloud cloud = new Cloud("Cloud", "", 10);
        cordeliaNaismith.setFaceExpression(HumanFaceExpression.HAPPY);
        cordeliaNaismith.realise(new Fact("Beautiful gas creatures in clouds", cordeliaNaismith));
        cordeliaNaismith.setOnMove(false);

        aralVorkosigan.setFaceExpression(HumanFaceExpression.CALM);
        aralVorkosigan.setOnMove(false);
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

        Pair<Double, Buff> drinkResult = waterLocation.getWater(1);
        if (drinkResult.second != null){
            cordeliaNaismith.addBuff(drinkResult.second);
            aralVorkosigan.addBuff(drinkResult.second);
            dubauer.addBuff(drinkResult.second);
        }

        cordeliaNaismith.sit(1);
        aralVorkosigan.sit(1);
        dubauer.sit(1);

        Tree leaningTree = new Tree("Baobab", currentConditions.time(), "", 15.0);
        waterLocation.addObject(leaningTree);
        aralVorkosigan.interact(leaningTree);

        cordeliaNaismith.lookAt(aralVorkosigan, new Fact("Vorkosigan is tired", cordeliaNaismith));
        cordeliaNaismith.realise(new Fact("Vorkosigan is the Butcher of Komarr", aralVorkosigan),
                HumanFaceExpression.CONCERN);
        cordeliaNaismith.talk("I know who you are. Vorkosigan, the Butcher of Komarr.", "accusing");
        aralVorkosigan.openEyes();
        aralVorkosigan.setFaceExpression(HumanFaceExpression.SHOCKED);
        aralVorkosigan.lookAt(cordeliaNaismith,
                new Fact("Cordeila knows who I am", aralVorkosigan));

        aralVorkosigan.talk("What do you know about Komarr?",
                "tone added ignorant betan");

        cordeliaNaismith.talk("Just what everyone knows", "");
    }
}
