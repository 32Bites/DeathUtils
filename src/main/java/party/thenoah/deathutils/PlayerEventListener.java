package party.thenoah.deathutils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
            if (doesPlayerExist(player.getName())) {
                int index = getPlayerIndex(player.getName());
                DeathUtils.players.get(index).setDeathLocation(player.getLocation());
            } else {
                PlayerInfo playerInfo = new PlayerInfo(player.getName());
                playerInfo.setDeathLocation(player.getLocation());
                DeathUtils.players.add(playerInfo);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        int index = getPlayerIndex(event.getPlayer().getName());
        PlayerInfo playerInfo = DeathUtils.players.get(index);
        Location deathLocation = playerInfo.getDeathLocation();

        String message = String.format("%sYou died at: %sX:%d Y:%d Z:%d%s",
                ChatColor.RED,
                ChatColor.GREEN,
                deathLocation.getBlockX(),
                deathLocation.getBlockY(),
                deathLocation.getBlockZ(),
                ChatColor.RESET);
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
        if (DeathUtils.players.size() > 0) {
            DeathUtils.players.remove(index);
        }
    }

    public static boolean doesPlayerExist(String name) {
        for (PlayerInfo playerInfo : DeathUtils.players) {
            if (playerInfo.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int getPlayerIndex(String name) {
        for (int i = 0; i <= DeathUtils.players.size(); i++) {
            if (DeathUtils.players.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
