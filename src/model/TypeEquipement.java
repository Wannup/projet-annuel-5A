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
	
	/**
	 * Constructeur par défaut
	 */
	public TypeEquipement(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nom
	 *     nom du type d'équipement
	 */
	public TypeEquipement(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne l'identifiant du type d'équipement.
     * 
     * @return un entier correspondant à l'identifiant du type d'équipement.
    */
	public int getId () {
		return this.idType;
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
	* @param nom
	*     nom du type d'équipement
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
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
