package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Agent est la classe repr�sentant un agent.
 * 
 * @version 1.0
 */
@Entity
public class Agent {

	@Id
	@GeneratedValue
	private int idAgent;
	
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String numCP;
	private String numPoste;
	
	/**
	 * Constructeur par d�faut
	 */
	public Agent(){}
	
	/**
	 * Constructeur agent
	 *
	 * @param nom
	 *     nom de l'agent
	 * @param prenom
	 *     prenom de l'agent
	 * @param dateDeNaissance
	 *     date de naissance de l'agent
	 * @param numCP
	 *     num�ro de l'agent
	 * @param numPoste
	 *     num�ro de poste de l'agent
	 *     
	 */
	public Agent(String nom, String prenom, String dateDeNaissance, String numCP, String numPoste) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numCP = numCP;
		this.numPoste = numPoste;
	}
	
	/**
     * Retourne l'identifiant de l'agent.
     * 
     * @return un entier correspondant � l'identifiant de l'agent.
     */
	public int getId () {
		return this.idAgent;
	}

	/**
     * Retourne le nom de l'agent.
     * 
     * @return une chaine de caract�re correspondant au nom de l'agent.
     */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom � l'agent
	*
	* @param nom
	*     nom de l'agent
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Retourne le pr�nom de l'agent.
     * 
     * @return une chaine de caract�re correspondant au pr�nom de l'agent.
     */
	public String getPrenom() {
		return prenom;
	}

	/**
	* Affecte un nouveau pr�nom � l'agent
	*
	* @param prenom
	*     pr�nom de l'agent
	*     
	*/
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
     * Retourne le nom de l'agent.
     * 
     * @return une chaine de caract�re correspondant au nom de l'agent.
     */
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	* Affecte une nouvelle date de naissance � l'agent
	*
	* @param dateDeNaissance
	*     date de naissance de l'agent
	*     
	*/
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
     * Retourne le num�ro del'agent.
     * 
     * @return une chaine de caract�re correspondant au num�ro de l'agent.
     */
	public String getNumCP() {
		return numCP;
	}

	/**
	* Affecte un nouveau num�ro � l'agent
	*
	* @param numCP
	*     num�ro de l'agent
	*     
	*/
	public void setNumCP(String numCP) {
		this.numCP = numCP;
	}

	/**
     * Retourne le num�ro de poste de l'agent.
     * 
     * @return une chaine de caract�re correspondant au num�ro de poste de l'agent.
     */
	public String getNumPoste() {
		return numPoste;
	}

	/**
	* Affecte un nouveau num�ro de poste � l'agent
	*
	* @param numPoste
	*     num�ro de poste de l'agent
	*     
	*/
	public void setNumPoste(String numPoste) {
		this.numPoste = numPoste;
	}
	
	/**
     * Retourne un agent sous forme de chaine.
     * 
     * @return une chaine de caract�re correspondant � l'agent.
     */
	@Override
	public String toString() {
		return "" + nom + " " + prenom;
	}

}
