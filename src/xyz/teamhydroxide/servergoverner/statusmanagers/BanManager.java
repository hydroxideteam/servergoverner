package xyz.teamhydroxide.servergoverner.statusmanagers;

import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.utils.YamlData;

public class BanManager {
	
	public static boolean isBanned(UUID id) {
		YamlConfiguration banlist = YamlData.load("bans");
		if (banlist.contains(id.toString())) {
			return true;
		}
		return false;
	}
	
	public static boolean isBanned(Player player) {
		return isBanned(player.getUniqueId());
	}
	
	public static int getSecondsBanned(Player player) {
		return 0;
	}
	
	public static void banPlayer(Player victim, String timeGiven, String reason) {
		YamlConfiguration list = YamlData.load("bans");

		
		list.set(victim.getUniqueId().toString()+".reason", reason);
		list.set(victim.getUniqueId().toString()+".time", timeGiven);
		list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
		
		
		YamlData.save("bans", list);
	}
}
