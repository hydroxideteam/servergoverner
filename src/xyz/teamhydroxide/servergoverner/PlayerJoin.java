package xyz.teamhydroxide.servergoverner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import xyz.teamhydroxide.servergoverner.persistance.Bans;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if (Bans.isPlayerBanned(player)) {
			player.kickPlayer(ChatColor.RED+"You are banned from this server!");
		}
	}
}
