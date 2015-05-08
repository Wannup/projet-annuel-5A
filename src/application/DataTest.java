package application;

import java.util.ArrayList;
import java.util.List;

import model.Agent;
import model.Equipement;
import model.Logiciel;

public class DataTest {
	
	public List<Equipement> getEquipements () {
		List<Equipement> equipements = new ArrayList<>();
		Agent agent1 = new Agent("test", "test", "0000", "", "");
		List<Logiciel> logiciels1 = new ArrayList<>();
		Equipement equipement1 = new Equipement(1, agent1, logiciels1, 200);
		equipements.add(equipement1);
		return equipements;
	}

}
