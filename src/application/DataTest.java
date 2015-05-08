package application;

import java.util.ArrayList;
import java.util.List;

import model.Agent;
import model.Equipement;
import model.Logiciel;

public class DataTest {
	
	public List<Equipement> getEquipements () {
		List<Equipement> equipements = new ArrayList<>();
		Agent agent1 = new Agent("test", "test", "01/01/2000", "0000", "0000");
		List<Logiciel> logiciels1 = new ArrayList<>();
		Equipement equipement1 = new Equipement("test", 1, agent1, logiciels1, 200);
		equipements.add(equipement1);
		Agent agent2 = new Agent("toto", "toto", "01/01/2000", "0001", "0001");
		List<Logiciel> logiciels2 = new ArrayList<>();
		Equipement equipement2 = new Equipement("equip", 2, agent2, logiciels2, 200);
		equipements.add(equipement2);
		return equipements;
	}

}
