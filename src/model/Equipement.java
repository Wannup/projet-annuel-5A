package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Equipement est la classe représentant un équipement.
 * 
 * @version 1.0
 */
@Entity
public class Equipement {

	@Id
	@GeneratedValue
	private int idEquipement;
	
	private int numeroEquipement;
	private String typeEquipement;
	private int nbJoursPrevUtilisation;
	private String dateGarantie;
	private String marque;
	private String modele;
	private String calife;
	private String info;
	private Agent agent;
	private List<Logiciel> logiciels;
	private double prix;
	
	/**
	 * Constructeur par défaut
	 */
	public Equipement(){}
	
	/**
	 * Constructeur équipement
	 *
	 * @param typeEquip
	 *     type de l'équipement
	 * @param numeroPoste
	 *     numéro de poste de l'équipement
	 * @param agent
	 *     agent lié à l'équipement
	 * @param prix
	 *     prix de l'équipement
	 * @param nbJourPrev
	 *     nombre de jour
	 * @param dateGarantie
	 *     date de garantie
	 * @param marque
	 *     marque de l'équipement
	 * @param modele
	 *     modèle de l'équipement
	 * @param calife
	 *     calife de l'équipement
	 * @param info
	 *     information de l'équipement
	 * @see Agent
	 */
	public Equipement(String typeEquip, int numeroPoste,  Agent agent, double prix, int nbJourPrev, String dateGarantie, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.numeroEquipement = numeroPoste;
		this.agent = agent;
		this.prix = prix;
		this.nbJoursPrevUtilisation = nbJourPrev;
		this.dateGarantie = dateGarantie;
		this.marque = marque;
		this.modele = modele;
		this.calife = calife;
		this.info = info;
	}
	
	/**
     * Retourne les information de l'équipement.
     * 
     * @return une chaine de caractère correspondant aux information de l'équipement.
    */
	public String getInfo() {
		return info;
	}

	/**
	* Affecte un nouveau nom à l'agent
	*
	* @param info
	*     information
	*     
	*/
	public void setInfo(String info) {
		this.info = info;
	}

	/**
     * Retourne l'identifiant de l'équipement.
     * 
     * @return un entier correspondant à l'identifiant de l'équipement.
    */
	public int getIdEquipement() {
		return idEquipement;
	}

	/**
	* Affecte un nouvelle identifiant à l'équipement
	*
	* @param idEquipement
	*     identifiant de l'équipement
	*     
	*/
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	/**
     * Retourne le type de l'équipement.
     * 
     * @return une chaine de caractère correspondant au type de l'équipement.
    */
	public String getTypeEquipement() {
		return typeEquipement;
	}

	/**
	* Affecte un nouveau type à l'équipement
	*
	* @param typeEquipement
	*     type de l'équipement
	*     
	*/
	public void setTypeEquipement(String typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	/**
     * Retourne la date de garantie de l'équipement.
     * 
     * @return une chaine de caractère correspondant à la date de garantie de l'équipement.
    */
	public String getDateGarantie() {
		return dateGarantie;
	}

	/**
	* Affecte une nouvelle date de garantie à l'équipement
	*
	* @param dateGarantie
	*     date de garantie de l'équipement
	*     
	*/
	public void setDateGarantie(String dateGarantie) {
		this.dateGarantie = dateGarantie;
	}

	/**
     * Retourne la marque de l'équipement.
     * 
     * @return une chaine de caractère correspondant à la marque de l'équipement.
    */
	public String getMarque() {
		return marque;
	}

	/**
	* Affecte une nouvelle marque à l'équipement
	*
	* @param marque
	*     marque de l'équipement
	*     
	*/
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
     * Retourne le modèle de l'équipement.
     * 
     * @return une chaine de caractère correspondant au modèle de l'équipement.
    */
	public String getModele() {
		return modele;
	}

	/**
	* Affecte un nouveau modele à l'équipement
	*
	* @param modele
	*     modele de l'équipement
	*     
	*/
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
     * Retourne le calife de l'équipement.
     * 
     * @return une chaine de caractère correspondant au calife de l'équipement.
    */
	public String getCalife() {
		return calife;
	}

	/**
	* Affecte un nouveau calife à l'équipement
	*
	* @param calife
	*     calife de l'équipement
	*     
	*/
	public void setCalife(String calife) {
		this.calife = calife;
	}

	/**
     * Retourne l'identifiant de l'équipement.
     * 
     * @return un entier correspondant à l'identifiant de l'équipement.
    */
	public int getId () {
		return this.idEquipement;
	}
	
	/**
	* Affecte un nouveau nom à l'équipement
	*
	* @param typeEquip
	*     nom de l'équipement
	*     
	*/
	public void setNom (String typeEquip) {
		this.typeEquipement = typeEquip;
	}
	
	/**
     * Retourne le nom de l'équipement.
     * 
     * @return une chaine de caractère correspondant au nom de l'équipement.
    */
	public String getNom() {
		return this.typeEquipement;
	}
	
	/**
     * Retourne le numéro de poste de l'équipement.
     * 
     * @return une chaine de caractère correspondant au numéro de poste de l'équipement.
    */
	public int getNumeroPoste() {
		return numeroEquipement;
	}

	/**
	* Affecte un nouveau numéro de poste à l'équipement
	*
	* @param numeroPoste
	*     numéro de poste de l'équipement
	*     
	*/
	public void setNumeroPoste(int numeroPoste) {
		this.numeroEquipement = numeroPoste;
	}

	/**
     * Retourne l'agent de l'équipement.
     * 
     * @return l'agent de l'équipement.
     * 
     * @see Agent
    */
	public Agent getAgent() {
		return agent;
	}

	/**
	* Affecte un nouvelle agent à l'équipement
	*
	* @param agent
	*     agent de l'équipement
	* @see Agent
	*/
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
     * Retourne les logiciels de l'équipement.
     * 
     * @return les logiciels de l'équipement.
     * 
     * @see List
     * @see Logiciel
    */
	public List<Logiciel> getLogiciels() {
		return logiciels;
	}

	/**
	* Affecte une nouvelle liste de logiciels à l'équipement
	*
	* @param logiciels
	*     liste de logiciels de l'équipement
	* @see List
	* @see Logiciel
	*/
	public void setLogiciels(List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}

	/**
     * Retourne le prix de l'équipement.
     * 
     * @return le prix de l'équipement.
     * 
    */
	public double getPrix() {
		return prix;
	}

	/**
	* Affecte un nouveau prix à l'équipement
	*
	* @param prix
	*     prix de l'équipement
	*     
	*/
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
     * Retourne le numéro de l'équipement.
     * 
     * @return le numéro de l'équipement.
     * 
    */
	public int getNumeroEquipement() {
		return numeroEquipement;
	}

	/**
	* Affecte un nouveau numéro à l'équipement
	*
	* @param numeroEquipement
	*     numéro de l'équipement
	*     
	*/
	public void setNumeroEquipement(int numeroEquipement) {
		this.numeroEquipement = numeroEquipement;
	}

	/**
     * Retourne le nombre de jour de l'équipement.
     * 
     * @return le nombre de l'équipement.
     * 
    */
	public int getNbJoursPrevUtilisation() {
		return nbJoursPrevUtilisation;
	}

	/**
	* Affecte un nouveau nombre de jour à l'équipement
	*
	* @param nbJoursPrevUtilisation
	*     nombre de jour de l'équipement
	*     
	*/
	public void setNbJoursPrevUtilisation(int nbJoursPrevUtilisation) {
		this.nbJoursPrevUtilisation = nbJoursPrevUtilisation;
	}

	/**
     * Retourne la date d'achat de l'équipement.
     * 
     * @return la date d'achat de l'équipement.
     * 
    */
	public String getDateAchat() {
		return dateGarantie;
	}

	/**
	* Affecte une nouvelle date d'achat à l'équipement
	*
	* @param dateAchat
	*     date d'achat de l'équipement
	*     
	*/
	public void setDateAchat(String dateAchat) {
		this.dateGarantie = dateAchat;
	}
	
	
}
