package xyz.teamhydroxide.servergoverner.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.Main;
import xyz.teamhydroxide.servergoverner.persistance.YamlData;
import xyz.teamhydroxide.utils.StringManipulation;

public class GovernerConsole {
	public static void get(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean canUse = false;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("servergoverner.console")) {
				canUse = true;
			}
		} else {
			canUse = true;
		}
		
		if (canUse) {
			if (args.length == 0) {
				sender.sendMessage(Main.SGprefix+"ServerGoverner Bukkit Plugin v1.0");
				sender.sendMessage(Main.SGprefix+"Server management & moderation tool.");
			} else {
				
				if (args[0].equalsIgnoreCase("kick")) {
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Remove users from server.");
						sender.sendMessage(ChatColor.GRAY+"Usage: /sg kick <username> [reason]");
					
					} else {
						Player kicked = Bukkit.getServer().getPlayer(args[1]);
						
						if (kicked != null) {
							if (args.length >= 3) {
								kicked.kickPlayer(ChatColor.RED+StringManipulation.buildFromArray(args, 2));
							} else {
								kicked.kickPlayer(ChatColor.RED+"You have been kicked from this server!\nYou may rejoin.");
							}
							
						} else {
							sender.sendMessage(Main.SGprefix+ChatColor.RED+"Error: player not found.");
						}
					}
				}
				
				if (args[0].equalsIgnoreCase("ban")) {
					
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Banning of users.");
						sender.sendMessage(ChatColor.GRAY+"Usage: /sg ban <username> <timeformat> <reason> OR /sg ban <username> <reason>");
						sender.sendMessage(ChatColor.GRAY+"For info on time formatting, see: /sg info time");
					} else if ( args.length >= 3 && StringManipulation.isInt(args[2]) == false) {
						YamlData.banPlayer(args[1], StringManipulation.buildFromArray(args, 2), sender);
						
					} else if (args.length >= 4) {
						YamlData.banPlayer(args[1], StringManipulation.buildFromArray(args, 3), Integer.parseInt(args[2]), sender);
					}
					
				}
				
				if (args[0].equalsIgnoreCase("unban")) {
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Un-Banning of users.");
						sender.sendMessage(ChatColor.GRAY+" Usage: /sg unban <username>");
					} else {
						YamlData.unbanPlayer(args[1], sender);
					}
				}
				
				if (args[0].equalsIgnoreCase("jail")) {
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Jailing of users.");
						sender.sendMessage(ChatColor.GRAY+"Usage: /sg jail <username> [timeformat]");
						sender.sendMessage(ChatColor.GRAY+"For info on time formatting, see: /sg info time");
						sender.sendMessage(ChatColor.GRAY+"Server Admins, there's stuff in the config.yml about jailing you should read.");
					} else {
						
					}
				}
				
				if (args[0].equalsIgnoreCase("mute")) {
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Muting of users.");
						sender.sendMessage(ChatColor.GRAY+"Usage: /sg mute <username> [timeformat]");
						sender.sendMessage(ChatColor.GRAY+"For info on time formatting, see: /sg info time");
					} else {
						
					}
				}
				
				if (args[0].equalsIgnoreCase("god")) {
					if (args.length == 1) {
						sender.sendMessage(Main.SGprefix+"Makes yourself or another user invincible.");
						sender.sendMessage(ChatColor.GRAY+"Usage: /sg god [username]");
					} else {
						
						
						
					}
				}
			}
		}
	}
}
