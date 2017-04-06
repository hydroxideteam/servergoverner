package xyz.teamhydroxide.servergoverner.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.servergoverner.Main;

public class PlayerAddons {
	
	
	public static void quit(Player player) {
		player.kickPlayer("You have left the server.");
	}
	
	public static void home(Player player) {
		Location bedLoc = player.getBedSpawnLocation();
		
		if (bedLoc != null) {
			player.teleport(bedLoc);
			player.playSound(bedLoc, Sound.ENTITY_ENDERMEN_TELEPORT, 4, 1);
			player.sendMessage(ChatColor.DARK_GRAY+"You have been teleported.");
		} else {
			player.playSound(bedLoc, Sound.ENTITY_ENDERMEN_HURT, 4, 1);
			player.sendMessage(ChatColor.DARK_GRAY+"Your bed has been missing or obstructed");
		}
	}
	
	public static void data(Player player) {
		String name = player.getDisplayName();
		Location position = player.getLocation();
		Boolean isflying = player.isFlying();
		double health = player.getHealth();
		GameMode gamemode = player.getGameMode();
		//Variables for information about the player, Probably the worst way to do it
		
		player.sendMessage(ChatColor.RED+"Position: "+position.getBlockX()+", "+position.getBlockY()+", "+position.getBlockZ());
		player.sendMessage(ChatColor.RED+"Health: "+health);
	}
	
	public static void localchat(Player player, String message) {
		double distance = Main.plugin.getConfig().getDouble("localchatdistance");
		for (Player other : player.getWorld().getPlayers()) {
			if (other.getLocation().distance(player.getLocation()) <= distance) {
				other.sendMessage(ChatColor.YELLOW+"Local "+ChatColor.WHITE+"<"+player.getDisplayName()+"> "+message);
			}
		}
	}
}

