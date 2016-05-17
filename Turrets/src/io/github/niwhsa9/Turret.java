package io.github.niwhsa9;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;
import com.darkblade12.particleeffect.ParticleEffect;

public class Turret implements Listener{
	double x;
	double y;
	double z;
	double dps=3.5;
	double range = 20;
	String team="none";
	Location l;
	public Turret(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		World w = Bukkit.getServer().getWorld("world");
		l = new Location(w, x,y+0.5f,z); //change y+0.5f to y
	
	}
	
	public void updateLoc() {
		l = new Location(Bukkit.getServer().getWorld("world"), x,y+0.5f,z);
	}

	public void fire() {
		for (Player nearPlayer : Bukkit.getOnlinePlayers()) { //Bukkit.getOnlinePlayers(); change to entities
		  if (nearPlayer.getLocation().distance(l) <= range) { /*"so do not repeatedly call this method 
		  	  														to get the location's magnitude." Well sh*t -Ash. */
			  nearPlayer.damage(dps);
			  nearPlayer.getWorld().playEffect(nearPlayer.getLocation(), Effect.ENDER_SIGNAL, 2003); //2003
			  //nearPlayer.getWorld().playEffect(nearPlayer.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
			  nearPlayer.getWorld().playSound(nearPlayer.getLocation(),   Sound.ARROW_HIT, 7, 1);
			  
		
			  Location tl = l;
			  Location pl = nearPlayer.getLocation();
			  pl.add(0.0, 1.0, 0.0);
			//  tl.add(0.0, 0.5, 0.0);
			  Vector v = pl.toVector().subtract(tl.toVector());
		
			  ParticleEffect.FLAME.display(v, 0.2f, tl, 200);
			
			  
		  }
		}
	}
	
}
