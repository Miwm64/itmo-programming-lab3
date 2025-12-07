package creatures;

import exceptions.BackpackAlreadyExistsException;
import locations.Location;
import objects.Backpack;
import objects.WorldObject;
import world.Faction;
import world.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

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
                System.out.print("and got wounded");
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

    public void lookAt(WorldObject object){
        System.out.printf("%s looks at %s\n", title, object.getTitle());
        if (object instanceof Human human){
            human.setFaceExpression(HumanFaceExpression.ANGRY);
        }
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
    public HashSet<Human> getKnownHumans() {
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
        System.out.printf("%s looks %s\n", title, faceExpression.toString());
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

    public void wearBackpack(Backpack backpack) throws BackpackAlreadyExistsException {
        this.backpack = Objects.requireNonNull(backpack);
    }

    public Backpack takeOffBackpack(){
        Backpack backpack = this.backpack;
        this.backpack = null;
        return backpack;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public void setOnMove(boolean onMove) {
        isOnMove = onMove;
    }

    public void setKnownHumans(ArrayList<Human> knownHumans) {
        this.knownHumans = new HashSet<>(Objects.requireNonNull(knownHumans));
    }

    public void setName(Name name) {
        this.name = Objects.requireNonNull(name);
    }

    // Java object methods
    // Java object methods
    @Override
    public String toString() {
        return super.toString() + String.format(
                "type: Human\n" +
                        "name: %s\n" +
                        "faceExpression: %s\n" +
                        "exhaustion: %.2f\n" +
                        "knownHumans: %d humans\n" +
                        "equipments: %s\n" +
                        "faction: %s\n" +
                        "backpack: %s\n" +
                        "isEyesOpened: %b\n" +
                        "isOnMove: %b\n" +
                        "knownFacts: %d facts\n",
                name,
                faceExpression,
                exhaustion,
                knownHumans != null ? knownHumans.size() : 0,
                equipments != null ? equipments.toString() : "null",
                faction != null ? faction.toString() : "null",
                backpack != null ? backpack.toString() : "null",
                isEyesOpened,
                isOnMove,
                knownFacts != null ? knownFacts.size() : 0
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        return Double.compare(human.exhaustion, exhaustion) == 0 &&
                isEyesOpened == human.isEyesOpened &&
                isOnMove == human.isOnMove &&
                Objects.equals(knownHumans, human.knownHumans) &&
                Objects.equals(equipments, human.equipments) &&
                Objects.equals(faction, human.faction) &&
                Objects.equals(name, human.name) &&
                Objects.equals(backpack, human.backpack) &&
                Objects.equals(faceExpression, human.faceExpression) &&
                Objects.equals(knownFacts, human.knownFacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                knownHumans,
                equipments,
                faction,
                name,
                backpack,
                exhaustion,
                isEyesOpened,
                isOnMove,
                faceExpression,
                knownFacts
        );
    }
}
