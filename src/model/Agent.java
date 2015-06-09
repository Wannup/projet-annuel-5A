package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Agent est la classe représentant un agent.
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
	private String tel;
	private String numCP;
	private String numPoste;
	private String pole;
	
	/**
	 * Constructeur par défaut
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
	 *     numéro de l'agent
	 * @param numPoste
	 *     numéro de poste de l'agent
	 *     
	 */
	public Agent(String nom, String prenom, String pole, String tel, String numCP, String numPoste) {		
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.numCP = numCP;
		this.numPoste = numPoste;
		this.pole = pole;
	}
	
	/**
     * Retourne l'identifiant de l'agent.
     * 
     * @return un entier correspondant à l'identifiant de l'agent.
     */
	public int getId () {
		return this.idAgent;
	}

	public int getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}

	public String getPole() {
		return pole;
	}

	public void setPole(String pole) {
		this.pole = pole;
	}

	/**
     * Retourne le nom de l'agent.
     * 
     * @return une chaine de caractère correspondant au nom de l'agent.
     */
	public String getNom() {
		return nom;
	}

	/**
	* Affecte un nouveau nom à l'agent
	*
	* @param nom
	*     nom de l'agent
	*     
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Retourne le prénom de l'agent.
     * 
     * @return une chaine de caractère correspondant au prénom de l'agent.
     */
	public String getPrenom() {
		return prenom;
	}

	/**
	* Affecte un nouveau prénom à l'agent
	*
	* @param prenom
	*     prénom de l'agent
	*     
	*/
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
     * Retourne le nom de l'agent.
     * 
     * @return une chaine de caractère correspondant au nom de l'agent.
     */
	public String getTel() {
		return tel;
	}

	/**
	* Affecte une nouvelle date de naissance à l'agent
	*
	* @param dateDeNaissance
	*     date de naissance de l'agent
	*     
	*/
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
     * Retourne le numéro del'agent.
     * 
     * @return une chaine de caractère correspondant au numéro de l'agent.
     */
	public String getNumCP() {
		return numCP;
	}

	/**
	* Affecte un nouveau numéro à l'agent
	*
	* @param numCP
	*     numéro de l'agent
	*     
	*/
	public void setNumCP(String numCP) {
		this.numCP = numCP;
	}

	/**
     * Retourne le numéro de poste de l'agent.
     * 
     * @return une chaine de caractère correspondant au numéro de poste de l'agent.
     */
	public String getNumPoste() {
		return numPoste;
	}

	/**
	* Affecte un nouveau numéro de poste à l'agent
	*
	* @param numPoste
	*     numéro de poste de l'agent
	*     
	*/
	public void setNumPoste(String numPoste) {
		this.numPoste = numPoste;
	}
	
	/**
     * Retourne un agent sous forme de chaine.
     * 
     * @return une chaine de caractère correspondant à l'agent.
     */
	@Override
	public String toString() {
		return "" + nom + " " + prenom;
	}

}
