package model;

public class Agent {

	private String nom;
	private String prenom;
	private String numeroCP;
	
	public Agent(String nom, String prenom, String numeroCP) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.numeroCP = numeroCP;
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

	public String getNumeroCP() {
		return numeroCP;
	}

	public void setNumeroCP(String numeroCP) {
		this.numeroCP = numeroCP;
	}
	
	
}
