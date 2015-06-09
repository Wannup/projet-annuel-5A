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
	
	/**
	 * Constructeur par d�faut
	 */
	public TypeEquipement(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nom
	 *     nom du type d'�quipement
	 */
	public TypeEquipement(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne l'identifiant du type d'�quipement.
     * 
     * @return un entier correspondant � l'identifiant du type d'�quipement.
    */
	public int getId () {
		return this.idType;
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
	* @param nom
	*     nom du type d'�quipement
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
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
