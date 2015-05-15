package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equipement {

	@Id
	@GeneratedValue
	private int idEquipement;
	
	private int numeroEquipement;
	private String typeEquipement;
	private int nbJoursPrevUtilisation;
	private String dateAchat;
	private Agent agent;
	private List<Logiciel> logiciels;
	private double prix;
	
	public Equipement(){}
	
	public Equipement(String typeEquip, int numeroPoste,  Agent agent, double prix, int nbJourPrev, String dateAchatEquip) {
		this.typeEquipement = typeEquip;
		this.numeroEquipement = numeroPoste;
		this.agent = agent;
		this.prix = prix;
		this.nbJoursPrevUtilisation = nbJourPrev;
		this.dateAchat = dateAchatEquip;
	}
	
	public int getId () {
		return this.idEquipement;
	}
	
	public void setNom (String typeEquip) {
		this.typeEquipement = typeEquip;
	}
	
	public String getNom() {
		return this.typeEquipement;
	}
	
	public int getNumeroPoste() {
		return numeroEquipement;
	}

	public void setNumeroPoste(int numeroPoste) {
		this.numeroEquipement = numeroPoste;
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

	public int getNumeroEquipement() {
		return numeroEquipement;
	}

	public void setNumeroEquipement(int numeroEquipement) {
		this.numeroEquipement = numeroEquipement;
	}

	public int getNbJoursPrevUtilisation() {
		return nbJoursPrevUtilisation;
	}

	public void setNbJoursPrevUtilisation(int nbJoursPrevUtilisation) {
		this.nbJoursPrevUtilisation = nbJoursPrevUtilisation;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	
}
