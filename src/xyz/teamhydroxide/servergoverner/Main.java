package xyz.teamhydroxide.servergoverner;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.servergoverner.commands.GCCommands;
import xyz.teamhydroxide.servergoverner.commands.PlusCommands;
import xyz.teamhydroxide.servergoverner.events.CustomEventMessages;
import xyz.teamhydroxide.servergoverner.events.PlayerEvents;
import xyz.teamhydroxide.utils.StringManipulation;
import xyz.teamhydroxide.utils.YamlData;

public class Main extends JavaPlugin {

	public static Main plugin;

	public static String SGprefix = ChatColor.DARK_AQUA+"[SG] "+ChatColor.WHITE;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Logging on...");

		// Event Handlers
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		getServer().getPluginManager().registerEvents(new CustomEventMessages(), this);

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

		saveDefaultConfig();
		
		
		
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.plugin, new Runnable() {
			public void run() {
				YamlConfiguration list = YamlData.load("bans");

				for (String key : list.getKeys(false)) {
					if (StringManipulation.isInt(list.getString(key+".time"))) {
						list.set(key+".time", list.getInt(key+".time")-1);

						if (list.getInt(key+".time") == 0) {

							list.set(key, null);
							Bukkit.getServer().broadcastMessage(Main.SGprefix+list.get(key+".name")+" has been unbanned.");
						}
					}
				}

				YamlData.save("bans", list);
			}
		}, 20, 20);
	}


	@Override
	public void onDisable() {
		getLogger().info("Logging off...");
	}

}