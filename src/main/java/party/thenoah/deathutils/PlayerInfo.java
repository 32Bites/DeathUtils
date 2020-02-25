package party.thenoah.deathutils;

public class PlayerInfo {
    public String name;
    public DeathLocation deathLocation;

    PlayerInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DeathLocation getDeathLocation() {
        return deathLocation;
    }

    public void setDeathLocation(DeathLocation deathLocation) {
        this.deathLocation = deathLocation;
    }
}
