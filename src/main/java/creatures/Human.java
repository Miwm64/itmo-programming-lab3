package creatures;

import exceptions.BackpackAlreadyExistsException;
import locations.Location;
import objects.Backpack;
import objects.Equipment;
import objects.WorldObject;
import world.Faction;
import world.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Human extends Creature{
    protected HashSet<Human> knownHumans;
    protected ArrayList<Equipment> equipments;
    protected Faction faction;
    protected Name name;
    protected Backpack backpack;
    protected double exhaustion; // 0-100
    protected boolean isEyesOpened;
    protected boolean isOnMove;
    protected HumanFaceExpression faceExpression;
    protected HashSet<Fact> knownFacts;

    // Constructor for builder
    private Human(Builder builder) {
        super(builder.title, builder.creationTime, builder.description, builder.type, builder.properties, builder.isAlive, builder.currentLocation, builder.buffs, builder.birthPlanet);
        this.name = builder.name;
        this.knownHumans = builder.knownHumans;
        this.knownFacts = builder.knownFacts;
        this.isOnMove = builder.isOnMove;
        this.isEyesOpened = builder.isEyesOpened;
        this.faction = builder.faction;
        this.faceExpression = builder.faceExpression;
        this.exhaustion = builder.exhaustion;
        this.equipments = builder.equipments;
        this.backpack = builder.backpack;
    }

    // Builder class
    public static class Builder {
        // Creature fields (copied)
        private String title;
        private LocalDateTime creationTime;
        private String description = "";
        private Planet birthPlanet;
        private ArrayList<Buff> buffs = new ArrayList<>();
        private Location currentLocation = null;
        private boolean isAlive = true;
        private ArrayList<CreatureProperty> properties = new ArrayList<>();
        private CreatureType type = CreatureType.HUMAN;

        // Human fields
        private Name name;
        private HashSet<Human> knownHumans = new HashSet<>();
        private HashSet<Fact> knownFacts = new HashSet<>();
        private boolean isOnMove = false;
        private boolean isEyesOpened = true;
        private Faction faction = null;
        private HumanFaceExpression faceExpression = HumanFaceExpression.CALM;
        private double exhaustion = 0.0;
        private ArrayList<Equipment> equipments = new ArrayList<>();
        private Backpack backpack = null;

        public Builder(String title, Name name, LocalDateTime creationTime) {
            this.title = Objects.requireNonNull(title);
            this.creationTime = Objects.requireNonNull(creationTime);
            this.name = Objects.requireNonNull(name);
        }

        // Creature field setters
        public Builder birthPlanet(Planet planet){
            this.birthPlanet = planet;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder buffs(ArrayList<Buff> buffs) {
            this.buffs = buffs;
            return this;
        }

        public Builder addBuff(Buff buff) {
            this.buffs.add(buff);
            return this;
        }

        public Builder currentLocation(Location currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public Builder isAlive(boolean isAlive) {
            this.isAlive = isAlive;
            return this;
        }

        public Builder properties(ArrayList<CreatureProperty> properties) {
            this.properties = properties;
            return this;
        }

        public Builder addProperty(CreatureProperty property) {
            this.properties.add(property);
            return this;
        }

        // Human field setters
        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder knownHumans(HashSet<Human> knownHumans) {
            this.knownHumans = knownHumans;
            return this;
        }

        public Builder addKnownHuman(Human human) {
            this.knownHumans.add(human);
            return this;
        }

        public Builder knownFacts(HashSet<Fact> knownFacts) {
            this.knownFacts = knownFacts;
            return this;
        }

        public Builder addKnownFact(Fact fact) {
            this.knownFacts.add(fact);
            return this;
        }

        public Builder isOnMove(boolean isOnMove) {
            this.isOnMove = isOnMove;
            return this;
        }

        public Builder isEyesOpened(boolean isEyesOpened) {
            this.isEyesOpened = isEyesOpened;
            return this;
        }

        public Builder faction(Faction faction) {
            this.faction = faction;
            return this;
        }

        public Builder faceExpression(HumanFaceExpression faceExpression) {
            this.faceExpression = faceExpression;
            return this;
        }

        public Builder exhaustion(double exhaustion) {
            if (exhaustion < 0 || exhaustion > 100) {
                throw new IllegalArgumentException("Exhaustion must be between 0 and 100");
            }
            this.exhaustion = exhaustion;
            return this;
        }

        public Builder equipments(ArrayList<Equipment> equipments) {
            this.equipments = equipments;
            return this;
        }

        public Builder addEquipment(Equipment equipment) {
            this.equipments.add(equipment);
            return this;
        }

        public Builder backpack(Backpack backpack) {
            this.backpack = backpack;
            return this;
        }

        public Human build() {
            // Validate required fields
            if (title == null || title.isEmpty()) {
                throw new IllegalStateException("Title is required");
            }
            if (creationTime == null) {
                throw new IllegalStateException("Creation time is required");
            }
            if (type == null) {
                throw new IllegalStateException("Creature type is required");
            }
            if (name == null) {
                throw new IllegalStateException("Name is required");
            }

            return new Human(this);
        }
    }

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

    public double recognise(Human human){
        if (!knownHumans.contains(human)){
            return 0;
        }
        return 1 - (buffs.contains(BuffType.MEMORY_DAMAGE) ? 0.5 : 0) - exhaustion / 1000;
    }

    @Override
    public void interact(WorldObject object) {
        super.interact(object); // TODO implement
    }

    public void talk(String text, String connotation){
        System.out.printf("%s: %s - %s with %s expression\n", title, text, connotation, faceExpression);
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

    public void move(Location location){
        this.currentLocation = location;
        isOnMove = true;
        changeExhaustion(5);
    }

    // Getters
    public Backpack getBackpack() {
        return backpack;
    }

    public ArrayList<Equipment> getEquipments() {
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

    public void setEquipments(ArrayList<Equipment> equipments) {
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

    public void addKnownHuman(Human human) {
        this.knownHumans.add(Objects.requireNonNull(human));
    }
    public void removeKnownHuman(Human human) {
        this.knownHumans.remove(Objects.requireNonNull(human));
    }

    public void setName(Name name) {
        this.name = Objects.requireNonNull(name);
    }

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

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode()
        );
    }
}
