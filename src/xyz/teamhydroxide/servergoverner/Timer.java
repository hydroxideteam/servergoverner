package xyz.teamhydroxide.servergoverner;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import xyz.teamhydroxide.servergoverner.persistance.YamlData;

public class Timer {
	@SuppressWarnings("deprecation")
	public static void minute() {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.plugin, new Runnable() {
			
			public void run() {
				YamlConfiguration list = YamlData.load();
				
				for (String key : list.getKeys(false)) {
					list.set(key+".time", list.getInt(key+".time")-1);

					if (list.getInt(key+".time") == 0) {
						
						list.set(key, null);
						Bukkit.getServer().broadcastMessage(Main.SGprefix+list.get(key+".name")+" has been unbanned.");
					}
				}
				
				YamlData.save(list);
				//for ()
			}
		}, 1200, 1200);
	}
}
