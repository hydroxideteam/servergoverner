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

public class Bans {
	public static YamlConfiguration load() {
		File f = new File(Main.plugin.getDataFolder()+"/bans.yml");
		
		YamlConfiguration bans = YamlConfiguration.loadConfiguration(f);
		return bans;
	}
	
	
	public static void save(YamlConfiguration bans) {
		File f = new File(Main.plugin.getDataFolder()+"/bans.yml");
		
		try {
			bans.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static boolean isPlayerBanned(UUID id) {
		
		YamlConfiguration banlist = load();
		
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
				
				YamlConfiguration list = load();
				
				list.set(victim.getUniqueId().toString()+".reason", reason);
				list.set(victim.getUniqueId().toString()+".time", minutes);
				list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
				
				save(list);
				Bukkit.broadcastMessage(Main.SGprefix+victim.getDisplayName()+" has been banned for "+minutes+" minute(s) for "+reason);	
				victim.kickPlayer(ChatColor.RED+"You have been banned for "+minutes+" minute(s) for "+reason);	
			}
		} else {
			banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
		}
		
	}
	
	public static void banPlayer(String name, String reason, CommandSender banner) {
		Player victim = Bukkit.getServer().getPlayer(name);
		if (victim != null) {
			if (isPlayerBanned(victim)) {
				banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player "+victim.getDisplayName()+"is already banned.");
			} else {
				// there are a lot of gay people in Cowboy Bebop
				YamlConfiguration list = load();
				
				list.set(victim.getUniqueId().toString()+".reason", reason);
				list.set(victim.getUniqueId().toString()+".time", -1);
				list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
				
				
				save(list);
				Bukkit.broadcastMessage(Main.SGprefix+victim.getDisplayName()+" has been permanently banned for "+reason);	
				victim.kickPlayer(ChatColor.RED+"You have been permanently banned for "+reason);
			}
		} else {
			banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
		}
		
	}
	
	
	public static void unbanPlayer(String name, CommandSender banner) {
		
		OfflinePlayer victim = Bukkit.getOfflinePlayer(name);
		
		if (victim != null) {
			if (isPlayerBanned(victim.getUniqueId())) {
				YamlConfiguration list = load();
				
				list.set(victim.getUniqueId().toString(), null);
				
				save(list);
				Bukkit.broadcastMessage(Main.SGprefix+victim.getName()+" has been unbanned");	
				
			} else {
				// there are a lot of gay people in Cowboy Bebop
			banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player "+victim.getName()+" not found in ban records.");
			}
		} else {
			banner.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
		}
	}
}
