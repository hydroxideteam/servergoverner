package xyz.teamhydroxide.servergoverner.moderation;

import org.bukkit.ChatColor;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import xyz.teamhydroxide.servergoverner.Main;
import xyz.teamhydroxide.servergoverner.persistance.Lists;
import xyz.teamhydroxide.utils.YamlData;

public class PlayerEvents implements Listener {
	
	public PlayerEvents() {
		
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if (Lists.isPlayerBanned(player)) {
			player.kickPlayer(ChatColor.RED+"You are banned from this server!");
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (Lists.isMuted(player)) {
			player.sendMessage(ChatColor.RED+"ERROR: you are muted for "+Lists.getMutedTime(player)+" seconds.");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if (Lists.isGodded(p)) {
				e.setCancelled(true);
			}
		}
		
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			
			if (Lists.isGodded(p)) {
				if (e.getEntity() instanceof Player && Main.plugin.getConfig().getBoolean("canGoddedAttackPlayers") == false) {
					p.sendMessage("ERROR: you cannot attack whilst godded.");
					e.setCancelled(true);
				} else if(e.getEntity() instanceof Monster && Main.plugin.getConfig().getBoolean("canGoddedAttackMonsters") == false) {
					p.sendMessage("ERROR: you cannot attack whilst godded.");
					e.setCancelled(true);
				}
				
			}
		}
	}
}
