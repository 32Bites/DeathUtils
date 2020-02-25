package party.thenoah.deathutils;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
        this.getCommand("deathpoint").setExecutor(new CommandListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
