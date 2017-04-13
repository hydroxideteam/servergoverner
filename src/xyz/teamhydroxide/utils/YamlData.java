package xyz.teamhydroxide.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
