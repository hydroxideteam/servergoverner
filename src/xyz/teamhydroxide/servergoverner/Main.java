package xyz.teamhydroxide.servergoverner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.commands.GovernerConsole;
import xyz.teamhydroxide.servergoverner.commands.PlayerAddons;
import xyz.teamhydroxide.servergoverner.friends.FriendCommand;
import xyz.teamhydroxide.servergoverner.friends.FriendEvents;
import xyz.teamhydroxide.servergoverner.pluscommands.PlusCommands;
import xyz.teamhydroxide.utils.StringManipulation;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	
	public static String SGprefix = ChatColor.DARK_AQUA+"[SG] "+ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Logging on...");
		
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new FriendEvents(), this);
		
		getCommand("friend").setExecutor(new FriendCommand());
		getCommand("lag").setExecutor(new PlusCommands());
		
		Timer.minute();
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Logging off...");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("quit")) { PlayerAddons.quit(player); }
			if (cmd.getName().equalsIgnoreCase("home")) { PlayerAddons.home(player); }
			if (cmd.getName().equalsIgnoreCase("data")) { PlayerAddons.data(player); }
			if (cmd.getName().equalsIgnoreCase("local")) { PlayerAddons.localchat(player, StringManipulation.buildFromArray(args, 0)); }
			if (cmd.getName().equalsIgnoreCase("friend")) { }
		}
		
		if (cmd.getName().equalsIgnoreCase("sg")) {
			GovernerConsole.get(sender, cmd, label, args);
		}
		return true;
	}
}