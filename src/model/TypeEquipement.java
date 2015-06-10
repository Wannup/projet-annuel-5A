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
	private int nbMonthRenewal;
	
	/**
	 * Constructeur par défaut
	 */
	public TypeEquipement(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nomType
	 *     nom du type d'équipement
	 *     
	 *  @param nbMonth
	 *     nombre de mois par défaut pour le renouvellement de ce type d'équipement 
	 */
	public TypeEquipement(String nomType, int nbMonth) {
		nom = nomType;
		nbMonthRenewal = nbMonth;
	}
	
	/**
     * Retourne l'identifiant du type d'équipement.
     * 
     * @return un entier correspondant à l'identifiant du type d'équipement.
    */
	public int getId () {
		return idType;
	}

	/**
     * Retourne le nom du type d'équipement.
     * 
     * @return une chaine de caractère correspondant au nom du type d'équipement.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au type d'équipement
	*
	* @param nomType
	*     nom du type d'équipement
	*     
	*/
	public void setNom(String nomType) {
		nom = nomType;
	}
	
	/**
     * Retourne le nombre de mois pour le renouvellement de ce type d'équipement.
     * 
     * @return un entier.
    */
	public int getNbMonthRenewal() {
		return nbMonthRenewal;
	}

	/**
	* Affecte une nouvelle durée de renouvellement au type d'équipement
	*
	* @param nbMonthRenewalType
	*     nombre de mois par défaut pour le renouvellement de ce type d'équipement
	*     
	*/
	public void setNbMonthRenewal(int nbMonthRenewalType) {
		nbMonthRenewal = nbMonthRenewalType;
	}

	/**
     * Retourne un type d'équipement sous forme de chaine.
     * 
     * @return une chaine de caractère correspondant au type d'équipement.
     */
	@Override
	public String toString() {
		return nom;
	}
}
