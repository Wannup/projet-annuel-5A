package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TypeEquipement est la classe représentant un type d'équipement.
 * 
 * @version 1.0
 */
@Entity
public class TypeEquipement {

	@Id
	@GeneratedValue
	private int idType;
	
	private String nom;
	private int nbYearRenewal;
	
	/**
	 * Constructeur par défaut
	 */
	public TypeEquipement(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nomType
	 *     nom du type d'ï¿½quipement
	 *     
	 *  @param nbYear
	 *     nombre de mois par dï¿½faut pour le renouvellement de ce type d'ï¿½quipement 
	 */
	public TypeEquipement(String nomType, int nbYear) {
		nom = nomType;
		nbYearRenewal = nbYear;
	}
	
	/**
     * Retourne l'identifiant du type d'ï¿½quipement.
     * 
     * @return un entier correspondant ï¿½ l'identifiant du type d'ï¿½quipement.
    */
	public int getId () {
		return idType;
	}

	/**
     * Retourne le nom du type d'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant au nom du type d'ï¿½quipement.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au type d'ï¿½quipement
	*
	* @param nomType
	*     nom du type d'ï¿½quipement
	*     
	*/
	public void setNom(String nomType) {
		nom = nomType;
	}
	
	/**
     * Retourne le nombre de mois pour le renouvellement de ce type d'ï¿½quipement.
     * 
     * @return un entier.
    */
	public int getNbYearRenewal() {
		return nbYearRenewal;
	}

	/**
	* Affecte une nouvelle durï¿½e de renouvellement au type d'ï¿½quipement
	*
	* @param nbYearRenewalType
	*     nombre de mois par dï¿½faut pour le renouvellement de ce type d'ï¿½quipement
	*     
	*/
	public void setNbYearRenewal(int nbYearRenewalType) {
		nbYearRenewal = nbYearRenewalType;
	}

	/**
     * Retourne un type d'ï¿½quipement sous forme de chaine.
     * 
     * @return une chaine de caractï¿½re correspondant au type d'ï¿½quipement.
     */
	@Override
	public String toString() {
		return nom;
	}
	
}
