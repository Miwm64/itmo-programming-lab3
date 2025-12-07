package creatures;

import locations.Location;
import objects.Backpack;
import objects.WorldObject;
import world.Faction;
import world.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Human extends Creature{
    protected HashSet<Human> knownHumans;
    protected ArrayList<String> equipments; // TODO Objects
    protected Faction faction;
    protected Name name;
    protected Backpack backpack;
    protected double exhaustion; // 0-100
    protected boolean isEyesOpened;
    protected boolean isOnMove;
    protected HumanFaceExpression faceExpression;
    protected HashSet<Fact> knownFacts;

    public Human(String title, LocalDateTime creationTime, String description, Planet birthPlanet,
                 ArrayList<Buff> buffs, Location currentLocation, boolean isAlive,
                 ArrayList<CreatureProperty> properties, CreatureType type, Name name,
                 HashSet<Human> knownHumans, HashSet<Fact> knownFacts, boolean isOnMove, boolean isEyesOpened,
                 Faction faction, HumanFaceExpression faceExpression, double exhaustion, ArrayList<String> equipments,
                 Backpack backpack) {
        super(title, creationTime, description, birthPlanet, buffs, currentLocation, isAlive, properties, type);
        this.name = name;
        this.knownHumans = knownHumans;
        this.knownFacts = knownFacts;
        this.isOnMove = isOnMove;
        this.isEyesOpened = isEyesOpened;
        this.faction = faction;
        this.faceExpression = faceExpression;
        this.exhaustion = exhaustion;
        this.equipments = equipments;
        this.backpack = backpack;
    }
    // TODO builder pattern

    public void stumble(boolean fall) {
        changeExhaustion(1);
        System.out.printf("%s has stumbled", title);
        if (fall){
            double n = Math.random() * 100;
            if (100 - exhaustion - 1 < n){
                System.out.printf("and got wounded");
                addBuff(new Buff(BuffType.WOUND, exhaustion/4));
            }
        }
        System.out.println();
    }

    public void sit(double hours){
        changeExhaustion(hours*10);
    }

    public void openEyes(){
        isEyesOpened = true;
    }

    public void closeEyes(){
        isEyesOpened = false;
    }

    @Override
    public void interact(WorldObject object) {
        super.interact(object); // TODO implement
    }

    public void talk(String text, String connotation){
        System.out.printf("%s: %s - %s\n", title, text, connotation);
    }

    public void realise(Fact fact){
        knownFacts.add(Objects.requireNonNull(fact));
        System.out.printf("%s realised: \n", title, fact);
    }

    public void realise(Fact fact, HumanFaceExpression expression){
        knownFacts.add(Objects.requireNonNull(fact));
        System.out.printf("%s realised: \n", title, fact);
        this.faceExpression = expression;
    }

    // Getters
    public Backpack getBackpack() {
        return backpack;
    }

    public ArrayList<String> getEquipments() {
        return equipments;
    }

    public double getExhaustion() {
        return exhaustion;
    }

    public HumanFaceExpression getFaceExpression() {
        return faceExpression;
    }
    public Name getName() {
        return name;
    }
    public ArrayList<Human> getKnownHumans() {
        return knownHumans;
    }

    public boolean isOnMove() {
        return isOnMove;
    }
    public Faction getFaction() {
        return faction;
    }
    public boolean isEyesOpened() {
        return isEyesOpened;
    }

    // Setters
    public void setFaceExpression(HumanFaceExpression faceExpression) {
        this.faceExpression = faceExpression;
    }

    public void setExhaustion(double exhaustion) {
        if (exhaustion > 100){
            this.exhaustion = 100;
        }
        else if (exhaustion < 0){
            this.exhaustion = 0;
        }
        else {
            this.exhaustion = exhaustion;
        }
    }

    public void changeExhaustion(double change){
        setExhaustion(this.exhaustion + change);
    }

    public void setEquipments(ArrayList<String> equipments) {
        this.equipments = new ArrayList<>(Objects.requireNonNull(equipments));
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = Objects.requireNonNull(backpack);
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public void setOnMove(boolean onMove) {
        isOnMove = onMove;
    }

    public void setKnownHumans(ArrayList<Human> knownHumans) {
        this.knownHumans = new ArrayList<>(Objects.requireNonNull(knownHumans));
    }

    public void setName(Name name) {
        this.name = Objects.requireNonNull(name);
    }
}
