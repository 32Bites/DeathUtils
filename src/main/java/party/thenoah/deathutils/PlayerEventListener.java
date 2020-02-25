package party.thenoah.deathutils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            DeathLocation deathLocation = new DeathLocation(event.getEntity().getLocation().getWorld().getName(),
                    event.getEntity().getLocation().getBlockX(),
                    event.getEntity().getLocation().getBlockY(),
                    event.getEntity().getLocation().getBlockZ());

            if (doesPlayerExist(player.getName())) {
                int index = getPlayerIndex(player.getName());
                Config.players.get(index).setDeathLocation(deathLocation);
            } else {
                PlayerInfo playerInfo = new PlayerInfo(player.getName());
                playerInfo.setDeathLocation(deathLocation);
                Config.players.add(playerInfo);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        int index = getPlayerIndex(event.getPlayer().getName());
        PlayerInfo playerInfo = Config.players.get(index);
        long x = playerInfo.getDeathLocation().x;
        long y = playerInfo.getDeathLocation().y;
        long z = playerInfo.getDeathLocation().z;

        String message = String.format("%sYou died at: %sX:%d Y:%d Z:%d%s", ChatColor.RED, ChatColor.GREEN, x, y, z, ChatColor.RESET);
        event.getPlayer().sendMessage(message);
        if (event.getPlayer().hasPermission("deathutils.deathpoint")) {
            message = String.format("Use %s/deathpoint%s to teleport you where you died.", ChatColor.GREEN, ChatColor.RESET);
            event.getPlayer().sendMessage(message);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int index = getPlayerIndex(player.getName());
        Config.players.remove(index);
    }

    public static boolean doesPlayerExist(String name) {
        for (PlayerInfo playerInfo : Config.players) {
            if (playerInfo.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int getPlayerIndex(String name) {
        for (int i = 0; i <= Config.players.size(); i++) {
            if (Config.players.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
