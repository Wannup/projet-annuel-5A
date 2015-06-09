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
	private String dateGarantie;
	private String marque;
	private String modele;
	private String calife;
	private String info;
	private Agent agent;
	private List<Logiciel> logiciels;
	private double prix;
	
	public Equipement(){}
	
	public Equipement(String typeEquip, int numeroPoste,  Agent agent, double prix, int nbJourPrev, String dateGarantie, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.numeroEquipement = numeroPoste;
		this.agent = agent;
		this.prix = prix;
		this.nbJoursPrevUtilisation = nbJourPrev;
		this.dateGarantie = dateGarantie;
		this.marque = marque;
		this.modele = modele;
		this.calife = calife;
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getIdEquipement() {
		return idEquipement;
	}

	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	public String getTypeEquipement() {
		return typeEquipement;
	}

	public void setTypeEquipement(String typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	public String getDateGarantie() {
		return dateGarantie;
	}

	public void setDateGarantie(String dateGarantie) {
		this.dateGarantie = dateGarantie;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCalife() {
		return calife;
	}

	public void setCalife(String calife) {
		this.calife = calife;
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
		return dateGarantie;
	}

	public void setDateAchat(String dateAchat) {
		this.dateGarantie = dateAchat;
	}
	
	
}
