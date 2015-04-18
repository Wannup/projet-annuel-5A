package model;

import java.util.List;


public class Equipement {

	private int numeroPoste;
	private Agent agent; // ou une liste d'agents ?
	private List<Logiciel> logiciels;
	private double prix;
	
	public Equipement(int numeroPoste, Agent agent, List<Logiciel> logiciels, double prix) {
		
		this.numeroPoste = numeroPoste;
		this.agent = agent;
		this.logiciels = logiciels;
		this.prix = prix;
	}

	public int getNumeroPoste() {
		return numeroPoste;
	}

	public void setNumeroPoste(int numeroPoste) {
		this.numeroPoste = numeroPoste;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Logiciel> getLogiciels() {
		return logiciels;
	}

	public void setLogiciels(List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
