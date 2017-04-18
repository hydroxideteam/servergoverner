package xyz.teamhydroxide.servergoverner.statusmanagers;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class GodManager {
	
	static ArrayList<String> goddedList = new ArrayList<String>();
	
	public static boolean isPlayerGodded(Player player) {
		
		
		return goddedList.contains(player.getDisplayName());
	}
	
	public static void godPlayer(Player player) {
		goddedList.add(player.getDisplayName());
	}
	
	public static void unGodPlayer(Player player) {
		goddedList.remove(player.getDisplayName());
	}
	
}
