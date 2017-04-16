package xyz.teamhydroxide.servergoverner.persistance;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import xyz.teamhydroxide.utils.YamlData;

public class CustomMessages implements Listener {


	public CustomMessages() {

	}
	
	

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		YamlConfiguration list = YamlData.load("customMsg");


		if(!player.hasPlayedBefore()){
			String joinMessage = list.getString("firstJoinMessage"); 
			if(joinMessage.contains("%player%")){
				joinMessage.replace("%player%", player.getDisplayName());
			}
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',joinMessage));


		} else {
			String joinMessage = list.getString("joinMessage"); 				
			if(joinMessage.contains("%player%")){
				joinMessage.replace("%player%", player.getDisplayName());
			}


			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',joinMessage));
		}





	}

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		YamlConfiguration list = YamlData.load("customMsg");
		Player player = event.getPlayer();
		String leaveMessage = list.getString("leaveMessage");

		if(leaveMessage.contains("%player%")){
			leaveMessage.replace("%player%", player.getDisplayName());

		}
		event.setQuitMessage(ChatColor.translateAlternateColorCodes('&',leaveMessage));
	}
}
