package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TypeEquipement est la classe repr�sentant un type d'�quipement.
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
	 * Constructeur par d�faut
	 */
	public TypeEquipement(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nomType
	 *     nom du type d'�quipement
	 *     
	 *  @param nbMonth
	 *     nombre de mois par d�faut pour le renouvellement de ce type d'�quipement 
	 */
	public TypeEquipement(String nomType, int nbMonth) {
		nom = nomType;
		nbYearRenewal = nbMonth;
	}
	
	/**
     * Retourne l'identifiant du type d'�quipement.
     * 
     * @return un entier correspondant � l'identifiant du type d'�quipement.
    */
	public int getId () {
		return idType;
	}

	/**
     * Retourne le nom du type d'�quipement.
     * 
     * @return une chaine de caract�re correspondant au nom du type d'�quipement.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au type d'�quipement
	*
	* @param nomType
	*     nom du type d'�quipement
	*     
	*/
	public void setNom(String nomType) {
		nom = nomType;
	}
	
	/**
     * Retourne le nombre de mois pour le renouvellement de ce type d'�quipement.
     * 
     * @return un entier.
    */
	public int getNbMonthRenewal() {
		return nbYearRenewal;
	}

	/**
	* Affecte une nouvelle dur�e de renouvellement au type d'�quipement
	*
	* @param nbMonthRenewalType
	*     nombre de mois par d�faut pour le renouvellement de ce type d'�quipement
	*     
	*/
	public void setNbMonthRenewal(int nbMonthRenewalType) {
		nbYearRenewal = nbMonthRenewalType;
	}

	/**
     * Retourne un type d'�quipement sous forme de chaine.
     * 
     * @return une chaine de caract�re correspondant au type d'�quipement.
     */
	@Override
	public String toString() {
		return nom;
	}
}
