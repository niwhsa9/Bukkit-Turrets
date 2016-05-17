package io.github.niwhsa9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Importer {
	File importFile;
	BufferedReader br;

	ArrayList<Turret> importTurrets() {
		try {
			int lineNum = 0;
			importFile = new File("turret-saves.txt");
			br = new BufferedReader(new FileReader(importFile));
			ArrayList<Turret> tr = new ArrayList<Turret>();
			String line;
			int stage = 0;
			Turret tur = new Turret(0, 0 ,0);
			while ((line = br.readLine()) != null) {
				lineNum++;
				line = line.replaceAll("\\s", "");
				if (line.equals("turret")) {
					tur = new Turret(0,0,0);
					stage = 0;
					continue;
				} else if(stage == 1) {
					line = line.replaceAll("[a-zA-Z]:", "");
					tur.x = Double.parseDouble(line);
				} else if(stage==2) {
					line = line.replaceAll("[a-zA-Z]:", "");
					tur.y = Double.parseDouble(line);
				} else if(stage == 3) {
					line = line.replaceAll("[a-zA-Z]:", "");
					tur.z = Double.parseDouble(line);
					tur.updateLoc();
					tr.add(tur);
				
				
				}
	
				stage++;
			}
			if (line == null && lineNum == 0)
				throw new Exception();

			System.out.println("Turrets imported!");
			return tr;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Coudln't import turrets!");
			return new ArrayList<Turret>();
		}
	}
}
