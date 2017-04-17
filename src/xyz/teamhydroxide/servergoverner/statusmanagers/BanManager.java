package xyz.teamhydroxide.servergoverner.statusmanagers;

import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.utils.YamlData;

public class BanManager {
	
	public static boolean isPlayerBanned(UUID id) {
		YamlConfiguration banlist = YamlData.load("bans");
		if (banlist.contains(id.toString())) {
			return true;
		}
		return false;
	}
	
	public static boolean isPlayerBanned(Player player) {
		return isPlayerBanned(player.getUniqueId());
	}
	
	public static int getPlayerBanTime(Player player) {
		YamlConfiguration banlist = YamlData.load("bans");
		
		return banlist.getInt(player.getUniqueId().toString()+".time");
	}
	
	public static String getPlayerBanReason(Player player) {
		YamlConfiguration banlist = YamlData.load("bans");
		
		return banlist.getString(player.getUniqueId().toString()+".reason");
	}
	
	public static void banPlayer(Player victim, String timeGiven, String reason) {
		YamlConfiguration list = YamlData.load("bans");

		
		list.set(victim.getUniqueId().toString()+".reason", reason);
		list.set(victim.getUniqueId().toString()+".time", timeGiven);
		list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
		
		
		YamlData.save("bans", list);
	}
}
