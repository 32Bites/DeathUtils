package party.thenoah.deathutils;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("deathpoint")) {
                Player player = (Player) sender;
                int index = PlayerEventListener.getPlayerIndex(player.getName());
                PlayerInfo playerInfo = Config.players.get(index);
                long x = playerInfo.getDeathLocation().x;
                long y = playerInfo.getDeathLocation().y;
                long z = playerInfo.getDeathLocation().z;

                Location loc = new Location(getServer().getWorld(playerInfo.getDeathLocation().world), x, y, z);
                player.teleport(loc);
            }
        }
        return false;
    }
}
