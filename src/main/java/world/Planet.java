package world;

import objects.WorldObject;

import java.util.Objects;

public class Planet {
    protected WormholeStatus wormholeStatus;
    protected PlanetType planetType;
    protected Faction faction;
    protected String title;

    public Planet(String title, WormholeStatus wormholeStatus, PlanetType planetType,
                  Faction faction) {
        this.wormholeStatus = Objects.requireNonNull(wormholeStatus);
        this.planetType = Objects.requireNonNull(planetType);
        this.faction = Objects.requireNonNull(faction);
        this.title = Objects.requireNonNull(title);
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    public void setWormholeStatus(WormholeStatus wormholeStatus) {
        this.wormholeStatus = wormholeStatus;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public WormholeStatus getWormholeStatus() {
        return wormholeStatus;
    }

    public Faction getFaction() {
        return faction;
    }

    // Java object methods
    @Override
    public String toString() {
        return String.format("type: Planet\ntitle: %s\nplanetType: %s\nfaction: %swormholeStatus: %s",
                title, planetType, faction, wormholeStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet p = (Planet) o;
        return Objects.equals(title, p.title) && Objects.equals(planetType,
                p.planetType) &&  Objects.equals(faction, p.faction)
                &&  Objects.equals(wormholeStatus, p.wormholeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), title, planetType, faction, wormholeStatus);
    }
}
