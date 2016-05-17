package io.github.niwhsa9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Exporter {
	TurretCommands tc;
	TurretHandlers th;
	final File exportFile = new File("turret-saves.txt");
	FileWriter fw;
	PrintStream ps;

	public Exporter(TurretCommands tc, TurretHandlers th) {
		// TODO Auto-generated constructor stub
		this.tc = tc;
		this.th = th;

		if (th.turrets.size() > 0)
			export();
		else
			System.out.println("No data to export");
	}

	public void export() {
		if (exportFile.exists())
			System.out.println("Export File found! Exporting");
		else {
			try {
				exportFile.createNewFile();
				System.out.println("Warning, no export file found. Creating new one.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			// fw = new FileWriter(exportFile);
			System.out.println("Exporting " + th.turrets.size() + " turrets");
			ps = new PrintStream(exportFile);
			for (int i = 0; i < th.turrets.size(); i++) {
				Turret c = th.turrets.get(i);
				ps.println("turret:");
				ps.println("     x:" + c.x);
				ps.println("     y:" + c.y);
				ps.println("     z:" + c.z);
				ps.println();
				//ps.flush();
			}
		
			ps.close();
			
			System.out.println("Export sucess!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Export failed!");
		}

	}

}
