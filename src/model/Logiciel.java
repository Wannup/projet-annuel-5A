package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Logiciel est la classe reprï¿½sentant un logiciel.
 * 
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

	/**
	 * Constructeur par défaut
	 */
	public Logiciel(){}
	
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

	@Override
	public String toString() {
		return nom + " - " + licenceNumber;
	}
	

}
