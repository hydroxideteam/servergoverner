package xyz.teamhydroxide.servergoverner;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.moderation.GCCommands;
import xyz.teamhydroxide.servergoverner.moderation.PlayerEvents;
import xyz.teamhydroxide.servergoverner.pluscommands.PlusCommands;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	
	public static String SGprefix = ChatColor.DARK_AQUA+"[SG] "+ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Logging on...");
		
		// Event Handlers
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);

		getCommand("sg").setExecutor(new GCCommands());
		
		PlusCommands plusCommands = new PlusCommands();
		GCCommands gcCommands = new GCCommands();
		
		getCommand("lag").setExecutor(plusCommands);
		getCommand("quit").setExecutor(plusCommands);
		getCommand("home").setExecutor(plusCommands);
		getCommand("local").setExecutor(plusCommands);
		getCommand("data").setExecutor(plusCommands);
		
		getCommand("sg").setExecutor(gcCommands);
		getCommand("chatclear").setExecutor(gcCommands);
		getCommand("ban").setExecutor(gcCommands);
		getCommand("kick").setExecutor(gcCommands);
		getCommand("unban").setExecutor(gcCommands);
		getCommand("god").setExecutor(gcCommands);
		getCommand("mute").setExecutor(gcCommands);
		getCommand("jail").setExecutor(gcCommands);
		
		Timer.minute();
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Logging off...");
	}
}