package model;

import java.util.Date;
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
	private String nom;
	private int nbJoursPrevUtilisation;
	private Date dateAchat;
	private Agent agent;
	private List<Logiciel> logiciels;
	private double prix;
	
	public Equipement(){}
	
	public Equipement(String nom, int numeroPoste, Agent agent, List<Logiciel> logiciels, double prix) {
		this.nom = nom;
		this.numeroEquipement = numeroPoste;
		this.agent = agent;
		this.logiciels = logiciels;
		this.prix = prix;
	}
	
	public String getNom() {
		return this.nom;
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

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	
}
