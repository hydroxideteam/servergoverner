package xyz.teamhydroxide.servergoverner.moderation;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.Main;
import xyz.teamhydroxide.servergoverner.persistance.YamlData;
import xyz.teamhydroxide.utils.StringManipulation;
import xyz.teamhydroxide.utils.TimeParser;

public class GCCommands implements CommandExecutor {


	private boolean kick(CommandSender sender, String[] args) {
		if (args.length > 0) {
			Player kicked = Bukkit.getServer().getPlayer(args[0]);

			if (kicked != null) {
				if (args.length >= 2) {
					kicked.kickPlayer(ChatColor.RED+StringManipulation.buildFromArray(args, 1));
				} else {
					kicked.kickPlayer(ChatColor.RED+"You have been kicked from this server!\nYou may rejoin.");
				}

			} else {
				sender.sendMessage(Main.SGprefix+ChatColor.RED+"Error: player not found.");
			}
			return true;
		}
		return false;
	}

	private boolean ban(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			Player victim = Bukkit.getServer().getPlayer(args[0]);
			String reason = StringManipulation.buildFromArray(args, 2);
			String timeStr = args[1];
			int timeInSeconds = (int)(TimeParser.parseString(args[1])/1000);
			
			if (victim != null) {
				
				YamlConfiguration list = YamlData.load("bans");
				
				if (list.contains(victim.getUniqueId().toString())) {
					sender.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player "+victim.getDisplayName()+"is already banned.");
				} else {
					// there are a lot of gay people in Cowboy Bebop
					if (args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("inf")) {
						list.set(victim.getUniqueId().toString()+".time", "infinity");
						list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
						list.set(victim.getUniqueId().toString()+".reason", reason);
						timeStr = "eternity";
					} else {
					
						list.set(victim.getUniqueId().toString()+".reason", reason);
						list.set(victim.getUniqueId().toString()+".time", timeInSeconds);
						list.set(victim.getUniqueId().toString()+".name", victim.getDisplayName());
					}
					
					
					YamlData.save("bans", list);
					Bukkit.broadcastMessage(Main.SGprefix+victim.getDisplayName()+" has been banned for "+timeStr+" for "+reason);	
					victim.kickPlayer(ChatColor.RED+"You have been banned for "+timeStr+" for "+reason);
				}
			} else {
				sender.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
			}
			return true;
		}
		return false;
	}
	
	private boolean chatClear(CommandSender sender, String[] args) {
		for (int i = 1; i < 100; i++) {
			Bukkit.getServer().broadcastMessage("   ");
		}
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA+"Chat has been cleared.");
		return true;
	}

	private boolean unban(CommandSender sender, String[] args) {
		if (args.length > 0) {

			@SuppressWarnings("deprecation")
			OfflinePlayer victim = Bukkit.getOfflinePlayer(args[0]);

			if (victim != null) {

				YamlConfiguration list = YamlData.load("bans");

				if (list.contains(victim.getUniqueId().toString())) {
					list.set(victim.getUniqueId().toString(), null);
					YamlData.save("bans", list);
					Bukkit.broadcastMessage(Main.SGprefix+victim.getName()+" has been unbanned");	

				} else {
					// there are a lot of gay people in Cowboy Bebop
					sender.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player "+victim.getName()+" not found in ban records.");
				}
			} else {
				sender.sendMessage(Main.SGprefix+ChatColor.RED+"ERROR: player not found.");
			}
			return true;
		}
		return false;
	}

	private boolean god(CommandSender sender, String[] args) {
		sender.sendMessage(Main.SGprefix+"Godmode, makes players invincible & flying, but they cannot attack. (UNIMPLEMENTED)");
		return true;
	}

	private boolean jail(CommandSender sender, String[] args) {
		return true;
	}

	private boolean mute(CommandSender sender, String[] args) {
		sender.sendMessage(Main.SGprefix+"Muting players so they cannot chat. UNIMPLEMENTED)");
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if (cmd.getName().equalsIgnoreCase("sg")) {
			sender.sendMessage(Main.SGprefix+"ServerGoverner Bukkit Plugin v1.0");
			sender.sendMessage(ChatColor.GRAY+"Server management & moderation tool.");
			
			if (args.length != 0 && args[0].equalsIgnoreCase("info")) {
				if (args[1].equalsIgnoreCase("time")) {
					sender.sendMessage(ChatColor.GRAY+"TimeFormats are specified as a number, and then a unit of time:");
					sender.sendMessage(ChatColor.GRAY+"1h30m, 12h, 15m, 2d5h. These are all acceptable TimeFormats");
					sender.sendMessage(ChatColor.GRAY+"-1 can be given to achieve forever.");
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("kick"))
			return kick(sender, args);

		if (cmd.getName().equalsIgnoreCase("ban"))
			return ban(sender, args);

		if (cmd.getName().equalsIgnoreCase("unban"))
			return unban(sender, args);

		if (cmd.getName().equalsIgnoreCase("jail"))
			return jail(sender, args);

		if (cmd.getName().equalsIgnoreCase("mute"))
			return mute(sender, args);

		if (cmd.getName().equalsIgnoreCase("god"))
			return god(sender, args);
		
		if (cmd.getName().equalsIgnoreCase("chatclear"))
			return chatClear(sender, args);
		
		return false;
	}
}
