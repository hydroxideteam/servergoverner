package xyz.teamhydroxide.servergoverner.friends;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FriendEvents implements Listener {
	
	@EventHandler
	public void get(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player damager =  (Player) e.getDamager();
			Player victim = (Player) e.getEntity();
			
			if (Friends.isPlayerFriended(damager, victim)) {
				damager.sendMessage(ChatColor.RED+"You cannot attack friends!");
				e.setCancelled(true);
			}
		}
	}
}
