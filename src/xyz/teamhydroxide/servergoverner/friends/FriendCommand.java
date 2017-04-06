package xyz.teamhydroxide.servergoverner.friends;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.Main;

// this class recieves and handles '/friend' commands
public class FriendCommand implements CommandExecutor {
	
	public FriendCommand() {
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			sender.sendMessage(Main.SGprefix+"ServerGoverner's Friend system");
			sender.sendMessage(ChatColor.GRAY+"Usage: /friend add <username>");
			sender.sendMessage(ChatColor.GRAY+"Usage: /friend remove <username>");
			sender.sendMessage(ChatColor.GRAY+"Usage: /friend list");
			sender.sendMessage(ChatColor.GRAY+"Usage: /friend chat <message>");
			return true;
		} else if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			
			if (args[0].equalsIgnoreCase("add") && args.length > 2) {
				
				Player friend = Bukkit.getServer().getPlayer(args[1]);
				
				if (friend == null) {
					player.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: Player not found!");
				} else {
					YamlConfiguration list = DataHandler.load();
					
					List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
					
					if (friendlist.contains(friend.getUniqueId().toString())) {
						player.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: This player is already on your friend list.");
					} else {
						player.sendMessage(Main.SGprefix+"You have added "+friend.getDisplayName()+" to your friend list.");
						friend.sendMessage(Main.SGprefix+player.getDisplayName()+ChatColor.YELLOW+" has added you to their friend list.");
						friendlist.add(friend.getUniqueId().toString());
						
						list.set(player.getUniqueId()+".list", friendlist);
						DataHandler.save(list);
					}
					
				}
				
				return true;
			}
			
			if (args[0].equalsIgnoreCase("remove") && (args.length > 2) ) {
				Player friend = Bukkit.getServer().getPlayer(args[1]);
				if (friend == null) {
					player.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: Player not found!");
				} else {
					YamlConfiguration list = DataHandler.load();
					List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
					if (friendlist.contains(friend.getUniqueId())); {
							friendlist.remove(friend);
					}
					
				}
				return true;
			}
			
			// give the sender a list of their friends
			if (args[0].equalsIgnoreCase("list")) {
				// load configuration into a YamlConfiguration
				YamlConfiguration list = DataHandler.load();
				
				List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
				// get a list<string> from it
				for (String friendUUID : friendlist) {
					
					
					Player friend = Bukkit.getServer().getPlayer(UUID.fromString(friendUUID));
					player.sendMessage(ChatColor.GRAY+friend.getPlayerListName());
					
				}
				
				// loop it
				
			}
			sender.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: Invalid syntax!");
		}
		return true;
	}
	
}
