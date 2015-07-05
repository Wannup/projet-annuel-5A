package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Charly FAROT
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
	 *     nom du type d'équipement
	 *     
	 *  @param nbYear
	 *     nombre de mois par d�faut pour le renouvellement de ce type d'équipement 
	 */
	public TypeEquipement(String nomType, int nbYear) {
		nom = nomType;
		nbYearRenewal = nbYear;
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
	public int getNbYearRenewal() {
		return nbYearRenewal;
	}

	/**
	* Affecte une nouvelle durée de renouvellement au type d'équipement
	*
	* @param nbYearRenewalType
	*     nombre de mois par défaut pour le renouvellement de ce type d'équipement
	*     
	*/
	public void setNbYearRenewal(int nbYearRenewalType) {
		nbYearRenewal = nbYearRenewalType;
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
