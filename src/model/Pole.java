package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Pole est la classe repr�sentant un p�le.
 * 
 * @version 1.0
 */
@Entity
public class Pole {

	@Id
	@GeneratedValue
	private int idPole;
	
	private String nom;
	
	/**
	 * Constructeur par d�faut
	 */
	public Pole(){}
	
	/**
	 * Constructeur pole
	 *
	 * @param nom
	 *     nom du p�le
	 */
	public Pole(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne l'identifiant du p�le.
     * 
     * @return un entier correspondant � l'identifiant du p�le.
    */
	public int getId () {
		return this.idPole;
	}

	/**
     * Retourne le nom du p�le.
     * 
     * @return une chaine de caract�re correspondant au nom du p�le.
    */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom au p�le
	*
	* @param nom
	*     nom du p�le
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
     * Retourne un p�le sous forme de chaine.
     * 
     * @return une chaine de caract�re correspondant au p�le.
     */
	@Override
	public String toString() {
		return nom;
	}
}
