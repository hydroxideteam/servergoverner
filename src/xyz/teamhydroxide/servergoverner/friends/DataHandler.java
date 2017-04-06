package xyz.teamhydroxide.servergoverner.friends;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import xyz.teamhydroxide.servergoverner.Main;

public class DataHandler {
	public static YamlConfiguration load() {
		File f = new File(Main.plugin.getDataFolder()+"/friendlist.yml");
		
		YamlConfiguration list = YamlConfiguration.loadConfiguration(f);
		return list;
	}
	
	
	public static void save(YamlConfiguration list) {
		File f = new File(Main.plugin.getDataFolder()+"/friendlist.yml");
		
		try {
			list.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
