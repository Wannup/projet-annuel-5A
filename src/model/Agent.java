package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * model class Agent
 * @author: Charly FAROT
 * @version 1.0
 */
@Entity
public class Agent {

	@Id
	@GeneratedValue
	private int idAgent;
	
	private String nom;
	private String prenom;
	private String tel;
	private String numCP;
	private Pole pole;
	private List<Equipement> equipements;
	
	/**
	 * Agent default constructor
	 */
	public Agent(){
		this.equipements = new ArrayList<Equipement>();
	}
	
	/**
	 * @param idAgent
	 * @param nom
	 * @param prenom
	 * @param tel
	 * @param numCP
	 * @param pole
	 */
	public Agent(String nom, String prenom, String tel, String numCP, Pole pole) {
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.numCP = numCP;
		this.pole = pole;
		this.equipements = new ArrayList<Equipement>();
	}

	/**
	 * Add a new Equipement for this agent
	 * @param equipement
	 */
	public void addEquipement(Equipement equipement){
		if(equipement != null)
			this.equipements.add(equipement);
	}
	
	/**
	 * @return the idAgent
	 */
	public int getIdAgent() {
		return idAgent;
	}

	/**
	 * @param idAgent the idAgent to set
	 */
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the numCP
	 */
	public String getNumCP() {
		return this.numCP;
	}

	/**
	 * @param numCP the numCP to set
	 */
	public void setNumCP(String numCP) {
		this.numCP = numCP;
	}

	/**
	 * @return the pole
	 */
	public Pole getPole() {
		return this.pole;
	}

	/**
	 * @param pole the pole to set
	 */
	public void setPole(Pole pole) {
		this.pole = pole;
	}

	/**
	 * @return the equipements
	 */
	public List<Equipement> getEquipements() {
		return this.equipements;
	}

	/**
	 * @param equipements the equipements to set
	 */
	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}
	
	
	/**
     * 
     * @return an agent description
     */
	@Override
	public String toString() {
		return numCP + " - " + nom + " " + prenom;
	}

	public boolean isValidToSave(){
		return numCP != null && nom != null && prenom != null && pole != null;
	}
}
