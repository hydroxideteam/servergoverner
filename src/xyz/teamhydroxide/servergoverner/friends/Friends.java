package xyz.teamhydroxide.servergoverner.friends;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import xyz.teamhydroxide.servergoverner.Main;

public class Friends {
	

	
	// checks if player has 'friend' on their friendlist
	public static boolean isPlayerFriended(Player player, Player friend) {
		YamlConfiguration list = DataHandler.load();
		List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
		
		if (friendlist.contains(friend.getUniqueId().toString())) {
			return true;
		}
		return false;
	}
	
	
	// check if both players are on the friend list
	public static boolean checkMutualFriends(Player player1, Player player2) {
		
		if (isPlayerFriended(player1, player2) && isPlayerFriended(player2, player1)) {
			return true;
		}
		return false;
	}
}
