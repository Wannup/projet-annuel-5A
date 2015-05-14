package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Logiciel {
	
	@Id
	@GeneratedValue
	private int idLogiciel;
	
	private String nom;
	
	private double prix;
	
	private int nbJourLicence;

	public Logiciel(){}
	public Logiciel(String nom, double prix, int nbJourLicence) {
		
		this.nom = nom;
		this.prix = prix;
		this.nbJourLicence = nbJourLicence;
	}
	
	public int getId () {
		return this.idLogiciel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getNbJourLicence() {
		return nbJourLicence;
	}

	public void setNbJourLicence(int nbJourLicence) {
		this.nbJourLicence = nbJourLicence;
	}
	
	

}
