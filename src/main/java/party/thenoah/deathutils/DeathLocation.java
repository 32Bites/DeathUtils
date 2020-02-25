package party.thenoah.deathutils;

public class DeathLocation {
    public String world;
    public long x,y,z;

    DeathLocation(String world, long x, long y, long z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
