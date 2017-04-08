package xyz.teamhydroxide.servergoverner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.friends.FriendCommand;
import xyz.teamhydroxide.servergoverner.friends.FriendEvents;
import xyz.teamhydroxide.servergoverner.moderation.GovernerConsole;
import xyz.teamhydroxide.servergoverner.pluscommands.PlusCommands;
import xyz.teamhydroxide.utils.StringManipulation;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	
	public static String SGprefix = ChatColor.DARK_AQUA+"[SG] "+ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Logging on...");
		
		// Event Handlers
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new FriendEvents(), this);
		
		
		// Command Executors
		getCommand("friend").setExecutor(new FriendCommand());
		
		PlusCommands plusCommands = new PlusCommands();
		getCommand("lag").setExecutor(plusCommands);
		getCommand("quit").setExecutor(plusCommands);
		getCommand("home").setExecutor(plusCommands);
		getCommand("local").setExecutor(plusCommands);
		getCommand("data").setExecutor(plusCommands);
		
		Timer.minute();
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Logging off...");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		if (cmd.getName().equalsIgnoreCase("sg")) {
			GovernerConsole.get(sender, cmd, label, args);
		}
		return true;
	}
}