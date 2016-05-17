package io.github.niwhsa9;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TurretCommands implements CommandExecutor {
	ArrayList<Turret> turrets = new ArrayList<Turret>();
	
	public ArrayList<Turret> getTurrets() {
		return turrets;
	}
	
	public void removeTurret(int i) {
		turrets.remove(i);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		try {
			if (label.equals("turret")) {
				if(args == null) sender.sendMessage(ChatColor.BLUE+"Turrets plugin working!");
				if (args[0].equals("create")) {
					sender.sendMessage(ChatColor.GOLD+"Creating Turret!");
					Player p = (Player) sender;
					Block turretCore = p.getTargetBlock((Set<Material>) null, 100);
					if(!turretCore.getType().equals(Material.REDSTONE_LAMP_OFF)) {
						sender.sendMessage(ChatColor.DARK_RED+"Block needs to be redstone lamp (off)");
						return false;
					}
					turrets.add(new Turret(turretCore.getLocation().getBlockX(), turretCore.getLocation().getBlockY(), turretCore.getLocation().getBlockZ()));
					System.out.println("Turret created at: " + turretCore.getLocation().getBlockX()+", " + turretCore.getLocation().getBlockY()+", " + turretCore.getLocation().getBlockZ());
					sender.sendMessage(ChatColor.GOLD+"Turret Created!");
					turretCore.setType(Material.IRON_BLOCK); //should be material lamp on
					turretCore.getState().update(true);
					return true;
				}
			}
			return false;

		} catch (Exception e)

		{
			sender.sendMessage(ChatColor.DARK_RED + "Error, command used incorrectly: " + e.getMessage());

		}
		return false;
	}

}
