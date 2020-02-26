package party.thenoah.deathutils;

import org.bukkit.Location;

public class PlayerInfo {
    public String name;
    public Location deathLocation;

    PlayerInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getDeathLocation() {
        return deathLocation;
    }

    public void setDeathLocation(Location deathLocation) {
        this.deathLocation = deathLocation;
    }
}
