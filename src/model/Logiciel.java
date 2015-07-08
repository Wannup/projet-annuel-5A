package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Logiciel est la classe représentant un logiciel.
 * @author Erwan LE GUYADER
 * @version 1.0
 */
@Entity
public class Logiciel {
	
	@Id
	@GeneratedValue
	private int idLogiciel;
	
	private String nom;
	
	private double prix;
	
	private String licenceNumber;
	
	private String dateEndValidityLicence;
	
	private List<Equipement> equipements;

	/**
	 * Constructeur par défaut
	 */
	public Logiciel(){
		equipements = new ArrayList<Equipement>();
	}
	
	/**
	 * Constructeur logiciel
	 *
	 * @param nom
	 *     nom du logiciel
	 * @param prix
	 *     prix du logiciel
	 * @param licenceNumber
	 *     numero de licence du logiciel
	 * @param endValidityLicence
	 */
	public Logiciel(String nomVal, double prixVal, String licenceNumberVal, String endValidityLicence) {
		
		nom = nomVal;
		prix = prixVal;
		licenceNumber = licenceNumberVal;
		dateEndValidityLicence = endValidityLicence;
		equipements = new ArrayList<Equipement>();
	}
	
	/**
     * Retourne l'identifiant du logiciel.
     * 
     * @return un entier correspondant à l'identifiant du logiciel.
    */
	public int getId () {
		return idLogiciel;
	}

	/**
	 * Add a new Equipement for this agent
	 * @param equipement
	 */
	public void addEquipement(Equipement equipement){
		if(equipement != null)
			equipements.add(equipement);
	}
	
	/**
     * Retourne le nom du logiciel.
     * 
     * @return une chaine de caractère correspondant au nom du logiciel.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au logiciel
	*
	* @param nom
	*     nom du logiciel
	*     
	*/
	public void setNom(String nomValue) {
		nom = nomValue;
	}

	/**
     * Retourne le prix du logiciel.
     * 
     * @return prix du logiciel.
    */
	public double getPrix() {
		return prix;
	}

	/**
	* Affecte un nouveau prix au logiciel
	*
	* @param prix
	*     prix du logiciel
	*     
	*/
	public void setPrix(double priceValue) {
		prix = priceValue;
	}

	/**
     * Retourne le nombre de jour du logiciel.
     * 
     * @return un entier correspondant au nombre de jour du logiciel.
    */
	public String getLicenceNumber() {
		return licenceNumber;
	}

	/**
	* Affecte un nouveau nombre de jour au logiciel
	*
	* @param nbJourLicence
	*     nombre de jour du logiciel
	*     
	*/
	public void setLicenceNumber(String licenceNumberVal) {
		licenceNumber = licenceNumberVal;
	}

	
	public String getDateEndValidityLicence() {
		return dateEndValidityLicence;
	}

	public void setDateEndValidityLicence(String endValidityLicence) {
		dateEndValidityLicence = endValidityLicence;
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

	@Override
	public String toString() {
		return nom + " - " + licenceNumber;
	}
	

}
