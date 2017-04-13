package xyz.teamhydroxide.servergoverner.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class Lists {
	public static HashMap<Player, Integer> muted = new HashMap();
	public static ArrayList<Player> godded = new ArrayList<Player>();
	
	
	public static boolean isMuted(Player player) {
		if (muted.containsKey(player)) {
			return true;
		}
		return false;
	}
	
	public static boolean isGodded(Player player) {
		if (godded.contains(player)) {
			return true;
		}
		return false;
	}
	
	public static void setGodded(Player player, boolean idx) {
		if (idx == true) {
			godded.add(player);
		} else {
			godded.remove(player);
		}
	}
	
	public static void addToMuted(Player player, int time) {
		muted.put(player, time);
	}
	
	public static int getMutedTime(Player player) {
		return muted.get(player);
	}
}
