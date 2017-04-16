package xyz.teamhydroxide.utils;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.teamhydroxide.servergoverner.Main;

public class YamlData {
	public static YamlConfiguration load(String filename) {
		File f = new File(Main.plugin.getDataFolder()+"/"+filename+".yml");
		
		YamlConfiguration bans = YamlConfiguration.loadConfiguration(f);
		return bans;
	}
	

	public static void save(String filename, YamlConfiguration saveList) {
		File f = new File(Main.plugin.getDataFolder()+"/"+filename+".yml");
		
		try {
			saveList.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
