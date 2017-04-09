package xyz.teamhydroxide.servergoverner.persistance;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.servergoverner.Main;

public class YamlData {
	public static YamlConfiguration load(String filename) {
		File f = new File(Main.plugin.getDataFolder()+"/"+filename+".yml");
		
		YamlConfiguration bans = YamlConfiguration.loadConfiguration(f);
		return bans;
	}
	
	
	public static void save(String filename, YamlConfiguration saveList) {
		File f = new File(Main.plugin.getDataFolder()+"/"+filename+".yml");
		
		try {
			saveList.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static boolean isPlayerBanned(UUID id) {
		
		YamlConfiguration banlist = load("bans");
		
		if (banlist.contains(id.toString())) {
			return true;
		}
		return false;
	}
	
	public static boolean isPlayerBanned(Player player) {
		
		UUID id = player.getUniqueId();
		
		return isPlayerBanned(id);
	}
	
	public static boolean isPlayerBanned(String name) {
		Player player = Bukkit.getPlayer(name);
		
		if (player != null) {
			return isPlayerBanned(player);
		}
		
		return false;
	}
	
	public static void banPlayer(String name, String reason, int minutes, CommandSender banner) {
		
			
		Player victim = Bukkit.getServer().getPlayer(name);
		
		if (victim != null) {
			if (isPlayerBanned(victim)) {
				banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player "+victim.getDisplayName()+"is already banned.");
			} else {
				
				YamlConfiguration list = load("bans");
				
				list.set(victim.getUniqueId().toString()+".reason", reason);
				list.set(victim.getUniqueId().toString()+".time", minutes);
				list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
				
				save("bans", list);
				Bukkit.broadcastMessage(Main.SGprefix+victim.getDisplayName()+" has been banned for "+minutes+" minute(s) for "+reason);	
				victim.kickPlayer(ChatColor.RED+"You have been banned for "+minutes+" minute(s) for "+reason);	
			}
		} else {
			banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
		}
		
	}
	
	public static void banPlayer(String name, String reason, CommandSender banner) {
		
		
	}
	
	
	public static void unbanPlayer(String name, CommandSender banner) {
		
		
	}
}
