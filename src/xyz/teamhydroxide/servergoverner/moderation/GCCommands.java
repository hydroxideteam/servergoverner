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

public class GCCommands implements CommandExecutor {


	private void kick(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.SGprefix+"Remove users from server.");
			sender.sendMessage(ChatColor.GRAY+"Usage: /sg kick <username> [reason]");
		} else {
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
		}
	}

	private void ban(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.SGprefix+"Banning of users.");
			sender.sendMessage(ChatColor.GRAY+"Usage: /sg ban <username> <timeformat> <reason> OR /sg ban <username> <reason>");
			sender.sendMessage(ChatColor.GRAY+"For info on time formatting, see: /sg info time");
		} else if ( args.length >= 2 && StringManipulation.isInt(args[1]) == false) {
			YamlData.banPlayer(args[0], StringManipulation.buildFromArray(args, 1), sender);

		} else if (args.length >= 3) {
			YamlData.banPlayer(args[0], StringManipulation.buildFromArray(args, 2), Integer.parseInt(args[1]), sender);
		}
	}

	private void unban(CommandSender sender, String[] args) {
		if (args.length == 0) {


		} else {
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
		}
	}

	private void god(CommandSender sender, String[] args) {
		sender.sendMessage(Main.SGprefix+"Godmode, makes players invincible & flying, but they cannot attack. (UNIMPLEMENTED)");
	}

	private void jail(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.SGprefix+"Jailing of users. (UNIMPLEMENTED)");
			sender.sendMessage(ChatColor.GRAY+"Usage: /sg jail <username> [timeformat]");
			sender.sendMessage(ChatColor.GRAY+"For info on time formatting, see: /sg info time");
			sender.sendMessage(ChatColor.GRAY+"Server Admins, there's stuff in the config.yml about jailing you should read.");
		} else {

		}
	}

	private void mute(CommandSender sender, String[] args) {
		sender.sendMessage(Main.SGprefix+"Muting players so they cannot chat. UNIMPLEMENTED)");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if (cmd.getName().equalsIgnoreCase("sg") && sender.hasPermission("servergoverner")) {
			sender.sendMessage(Main.SGprefix+"ServerGoverner Bukkit Plugin v1.0");
			sender.sendMessage(Main.SGprefix+"Server management & moderation tool.");
		}

		if (cmd.getName().equalsIgnoreCase("kick")) {
			kick(sender, args);
		}

		if (cmd.getName().equalsIgnoreCase("ban")) {

			ban(sender, args);
		}

		if (cmd.getName().equalsIgnoreCase("unban")) {

			unban(sender, args);

		}

		if (cmd.getName().equalsIgnoreCase("jail")) {
			jail(sender, args);
		}

		if (cmd.getName().equalsIgnoreCase("mute")) {
			mute(sender, args);
		}

		if (cmd.getName().equalsIgnoreCase("god")) {
			god(sender, args);
		}
		return true;
	}
}
