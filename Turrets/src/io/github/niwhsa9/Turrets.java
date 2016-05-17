package io.github.niwhsa9;
import org.bukkit.plugin.java.JavaPlugin;

public class Turrets extends JavaPlugin{
	TurretCommands tc;
	TurretHandlers th;
	@Override
	public void onEnable() {
		System.out.println("Enabling Turrets!");
		Importer im = new Importer();
		tc = new TurretCommands();
		tc.turrets = im.importTurrets(); //exp
		System.out.println("x:" +tc.turrets.get(0).x + " y:" + tc.turrets.get(0).y + " z:" + tc.turrets.get(0).z); //exp
		th = new TurretHandlers(this, tc);  
		th.turrets = tc.turrets; //exp
		getCommand("turret").setExecutor(tc);
	
	

	}

	@Override
	public void onDisable() {
		System.out.println("Disabling Turrets! Exporting data!");
		Exporter ex = new Exporter(tc, th); 
		System.out.println("Turrets Disabled! Data export complete. (Read above for status)");
	}
}
/* TODO 
 * Uses flame particle beam effect 
 * Checks teams and doesn't fire if member is on same team as it (export teams to file)
 * Target redstone lamp and type /turret create
 * Exports turrets to file so that data saves.
 * Turn on redstone lamp when turret activates
 * add redstone torch above block
 */
