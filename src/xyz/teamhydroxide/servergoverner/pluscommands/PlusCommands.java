package xyz.teamhydroxide.servergoverner.pluscommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.teamhydroxide.servergoverner.Main;

public class PlusCommands implements CommandExecutor {
	
	public PlusCommands() {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if (cmd.getName().equalsIgnoreCase("lag")) {
			
			
			sender.sendMessage(Main.SGprefix+"Memory Usage (In Megabytes):"+(Runtime.getRuntime().freeMemory()/1000000)+"mb / "+(Runtime.getRuntime().totalMemory()/1000000)+"mb");
			return true;
		}
		return false;
	}

}
