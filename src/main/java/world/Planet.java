package world;

import java.util.Objects;

public class Planet {
    protected WormholeStatus wormholeStatus;
    protected PlanetType planetType;
    protected Faction faction;

    public Planet(WormholeStatus wormholeStatus, PlanetType planetType, Faction faction) {
        this.wormholeStatus = Objects.requireNonNull(wormholeStatus);
        this.planetType = Objects.requireNonNull(planetType);
        this.faction = Objects.requireNonNull(faction);
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

    public PlanetType getPlanetType() {
        return planetType;
    }

    public WormholeStatus getWormholeStatus() {
        return wormholeStatus;
    }

    public Faction getFaction() {
        return faction;
    }
}
