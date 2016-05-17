package io.github.niwhsa9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class TurretHandlers implements Listener, ActionListener {
	Timer timer;
	ArrayList<Turret> turrets  = new ArrayList<Turret>();
	TurretCommands tc;

	public TurretHandlers(Plugin p, TurretCommands tc) {
		this.tc = tc;
		turrets = tc.getTurrets();
		initTimer();
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
	}

	public void initTimer() {
		timer = new Timer(10, this);
		timer.start();
	}

	public void updateTurrets() {
		for (int i = 0; i < turrets.size(); i++) {
			turrets.get(i).fire();
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		try {
		Block b = e.getBlock();
		for (int i = 0; i < turrets.size(); i++) { 
			if (b.getLocation().getBlockX() == turrets.get(i).x && b.getLocation().getBlockY() == turrets.get(i).y
					&& b.getLocation().getBlockZ() == turrets.get(i).z) {
				turrets.remove(i);
				tc.removeTurret(i);
			}
		}
		} catch(Exception ex) {
			//:P hurray for lazy!
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (turrets != null && tc.getTurrets() != null) {
			for (int i = turrets.size(); i < tc.getTurrets().size(); i++) {
				turrets.add(tc.getTurrets().get(i));
			}
			updateTurrets();
		}
	}

}
