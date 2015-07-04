package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Pole est la classe représentant un pôle.
 * @author Erwan LE GUYADER
 * @version 1.0
 */
@Entity
public class Pole {

	@Id
	@GeneratedValue
	private int idPole;
	
	private String nom;
	
	/**
	 * Constructeur par défaut
	 */
	public Pole(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nom
	 *     nom du pôle
	 */
	public Pole(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne l'identifiant du pôle.
     * 
     * @return un entier correspondant à l'identifiant du pôle.
    */
	public int getId () {
		return this.idPole;
	}

	/**
     * Retourne le nom du pôle.
     * 
     * @return une chaine de caractère correspondant au nom du pôle.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au pôle
	*
	* @param nom
	*     nom du pôle
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne un pôle sous forme de chaine.
     * 
     * @return une chaine de caractère correspondant au pôle.
     */
	@Override
	public String toString() {
		return nom;
	}
}
