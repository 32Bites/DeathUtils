package party.thenoah.deathutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class DeathUtils extends JavaPlugin {

    public static List<PlayerInfo> players = new ArrayList<PlayerInfo>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
        this.getCommand("deathpoint").setExecutor(new CommandListener());

        // This may seem unnecessary, but just in case.
        for (Player player : getServer().getOnlinePlayers()) {
            PlayerInfo playerInfo = new PlayerInfo(player.getName());
            Location bedSpawn = player.getBedSpawnLocation();
            if (bedSpawn == null) {
                playerInfo.setDeathLocation(player.getWorld().getSpawnLocation());
            } else {
                playerInfo.setDeathLocation(bedSpawn);
            }
            players.add(playerInfo);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
