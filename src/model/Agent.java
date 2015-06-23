package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Agent est la classe reprï¿½sentant un agent.
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
	private Pole pole;
	private List<Equipement> equipements;
	
	/**
	 * Constructeur par dï¿½faut
	 */
	public Agent(){
		this.equipements = new ArrayList<Equipement>();
	}
	
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
	 *     numero de l'agent
	 * @param numPoste
	 *     numero de poste de l'agent
	 *     
	 */
	public Agent(String nom, String prenom, Pole pole, String tel, String numCP) {		
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.numCP = numCP;
		this.pole = pole;
		this.equipements = new ArrayList<Equipement>();
	}
	
	public void addEquipement(Equipement equipement){
		if(equipement != null)
			this.equipements.add(equipement);
	}
	
	/**
     * Retourne l'identifiant de l'agent.
     * 
     * @return un entier correspondant ï¿½ l'identifiant de l'agent.
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

	public Pole getPole() {
		return pole;
	}

	public void setPole(Pole pole) {
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
     * Retourne le prenom de l'agent.
     * 
     * @return une chaine de caractere correspondant au prenom de l'agent.
     */
	public String getPrenom() {
		return prenom;
	}

	/**
	* Affecte un nouveau prenom à l'agent
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
     * Retourne le numero de l'agent.
     * 
     * @return une chaine de caractere correspondant au numï¿½ro de l'agent.
     */
	public String getNumCP() {
		return numCP;
	}

	/**
	* Affecte un nouveau numero à l'agent
	*
	* @param numCP
	*     numero de l'agent
	*     
	*/
	public void setNumCP(String numCP) {
		this.numCP = numCP;
	}
	
	/**
	 * @return the equipements
	 */
	public List<Equipement> getEquipements() {
		return equipements;
	}

	/**
	 * @param equipements the equipements to set
	 */
	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

	/**
     * Retourne un agent sous forme de chaine.
     * 
     * @return une chaine de caractere correspondant à l'agent.
     */
	@Override
	public String toString() {
		return numCP + " - " + nom + " " + prenom;
	}

}
