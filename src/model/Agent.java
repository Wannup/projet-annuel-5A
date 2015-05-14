package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agent {

	@Id
	@GeneratedValue
	private int idAgent;
	
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String numCP;
	private String numPoste;
	
	public Agent(){}
	public Agent(String nom, String prenom, String dateDeNaissance, String numCP, String numPoste) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numCP = numCP;
		this.numPoste = numPoste;
	}
	
	public int getId () {
		return this.idAgent;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getNumCP() {
		return numCP;
	}

	public void setNumCP(String numCP) {
		this.numCP = numCP;
	}

	public String getNumPoste() {
		return numPoste;
	}

	public void setNumPoste(String numPoste) {
		this.numPoste = numPoste;
	}
	
}
