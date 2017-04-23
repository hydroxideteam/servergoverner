package xyz.teamhydroxide.servergoverner.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.servergoverner.Main;
import xyz.teamhydroxide.utils.StringManipulation;

public class PlusCommands implements CommandExecutor {
	
	public PlusCommands() {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		// Player-Only commands
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("fly")) {
				player.setFlying(!player.isFlying());
				return true;
			}
			// leaving server via command
			if (cmd.getName().equalsIgnoreCase("quit")) {
				player.kickPlayer("You have left the server.");
				return true;
			}
			
			// teleporting to bed
			if (cmd.getName().equalsIgnoreCase("home")) {
				Location bedLoc = player.getBedSpawnLocation();
				
				if (bedLoc != null) {
					player.teleport(bedLoc);
					player.playSound(bedLoc, Sound.ENTITY_ENDERMEN_TELEPORT, 4, 1);
					player.sendMessage(ChatColor.DARK_GRAY+"You have been teleported.");
				} else {
					player.playSound(bedLoc, Sound.ENTITY_ENDERMEN_HURT, 4, 1);
					player.sendMessage(ChatColor.DARK_GRAY+"Your bed has been missing or obstructed");
				}
				return true;
			}
			
			// dump data about player
			if (cmd.getName().equalsIgnoreCase("data")) {
				Location position = player.getLocation();
				double health = player.getHealth();
				
				player.sendMessage(ChatColor.RED+"Position: "+position.getBlockX()+", "+position.getBlockY()+", "+position.getBlockZ());
				player.sendMessage(ChatColor.RED+"Health: "+health);
				return true;
			}
			
			// local chat
			if (cmd.getName().equalsIgnoreCase("local")) {
				String message = StringManipulation.buildFromArray(args, 0);
				double distance = Main.plugin.getConfig().getDouble("localchatdistance");
				for (Player other : player.getWorld().getPlayers()) {
					if (other.getLocation().distance(player.getLocation()) <= distance) {
						other.sendMessage(ChatColor.YELLOW+"Local "+ChatColor.WHITE+"<"+player.getDisplayName()+"> "+message);
					}
				}
				return true;
			}
			
		}
		
		
		// Either-Way commands
		
		// Server resource data
		if (cmd.getName().equalsIgnoreCase("lag")) {
			sender.sendMessage(Main.SGprefix+"Memory: using "+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1000000+"mb out of "+(Runtime.getRuntime().totalMemory()/1000000)+"mb, "+(Runtime.getRuntime().freeMemory()/1000000)+"mb free.");
			return true;
		}
		return false;
	}

}
