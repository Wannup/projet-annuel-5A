package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Logiciel est la classe repr�sentant un logiciel.
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
	
	private String number;

	/**
	 * Constructeur par d�faut
	 */
	public Logiciel(){}
	
	/**
	 * Constructeur logiciel
	 *
	 * @param nom
	 *     nom du logiciel
	 * @param prix
	 *     prix du logiciel
	 * @param nbJourLicence
	 *     nombre de jour du logiciel
	 */
	public Logiciel(String nom, double prix, String licenceNumber) {
		
		this.nom = nom;
		this.prix = prix;
		this.number = licenceNumber;
	}
	
	/**
     * Retourne l'identifiant du logiciel.
     * 
     * @return un entier correspondant � l'identifiant du logiciel.
    */
	public int getId () {
		return this.idLogiciel;
	}

	/**
     * Retourne le nom du logiciel.
     * 
     * @return une chaine de caract�re correspondant au nom du logiciel.
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
	public void setNom(String nom) {
		this.nom = nom;
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
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
     * Retourne le nombre de jour du logiciel.
     * 
     * @return un entier correspondant au nombre de jour du logiciel.
    */
	public String getLicenceNumber() {
		return number;
	}

	/**
	* Affecte un nouveau nombre de jour au logiciel
	*
	* @param nbJourLicence
	*     nombre de jour du logiciel
	*     
	*/
	public void setLicenceNumber(String licenceNumber) {
		this.number = licenceNumber;
	}

	@Override
	public String toString() {
		return nom + " - " + number;
	}
	

}
