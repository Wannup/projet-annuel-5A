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
	private String typeEquipement;
	private String renewalDate;
	private String dateGarantie;
	private String dateLivraison;
	private String marque;
	private String modele;
	private String nomCalife;
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
	public Equipement(String typeEquip, Agent agent, double prix, String dateGarantie, String dateLivraison, String marque, String modele, String calife, String info, String renewalDate) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.prix = prix;
		this.dateGarantie = dateGarantie;
		this.dateLivraison = dateLivraison;
		this.renewalDate = renewalDate;
		this.marque = marque;
		this.modele = modele;
		this.nomCalife = calife;
		this.info = info;
	}
	
	public Equipement(String typeEquip, List<Logiciel> logiciels ,Agent agent, double prix, String dateGarantie, String dateLivraison, String marque, String modele, String calife, String info, String renewalDate) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.logiciels = logiciels;
		this.prix = prix;
		this.dateGarantie = dateGarantie;
		this.dateLivraison = dateLivraison;
		this.renewalDate = renewalDate;
		this.marque = marque;
		this.modele = modele;
		this.nomCalife = calife;
		this.info = info;
	}
	
	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	/**
     * Retourne les informations de l'équipement.
     * 
     * @return une chaine de caractère correspondant aux informations de l'équipement.
    */
	public String getInfo() {
		return info;
	}

	/**
	* Affecte de nouvelles informations
	*
	* @param info
	*     information
	*     
	*/
	public void setInfo(String info) {
		this.info = info;
	}

	/**
     * Retourne l'identifiant de l'ï¿½quipement.
     * 
     * @return un entier correspondant ï¿½ l'identifiant de l'ï¿½quipement.
    */
	public int getIdEquipement() {
		return idEquipement;
	}

	/**
	* Affecte un nouvelle identifiant ï¿½ l'ï¿½quipement
	*
	* @param idEquipement
	*     identifiant de l'ï¿½quipement
	*     
	*/
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	/**
     * Retourne le type de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant au type de l'ï¿½quipement.
    */
	public String getTypeEquipement() {
		return typeEquipement;
	}

	/**
	* Affecte un nouveau type ï¿½ l'ï¿½quipement
	*
	* @param typeEquipement
	*     type de l'ï¿½quipement
	*     
	*/
	public void setTypeEquipement(String typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	/**
     * Retourne la date de garantie de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant ï¿½ la date de garantie de l'ï¿½quipement.
    */
	public String getDateGarantie() {
		return dateGarantie;
	}

	/**
	* Affecte une nouvelle date de garantie ï¿½ l'ï¿½quipement
	*
	* @param dateGarantie
	*     date de garantie de l'ï¿½quipement
	*     
	*/
	public void setDateGarantie(String dateGarantie) {
		this.dateGarantie = dateGarantie;
	}

	/**
     * Retourne la marque de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant ï¿½ la marque de l'ï¿½quipement.
    */
	public String getMarque() {
		return marque;
	}

	/**
	* Affecte une nouvelle marque ï¿½ l'ï¿½quipement
	*
	* @param marque
	*     marque de l'ï¿½quipement
	*     
	*/
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
     * Retourne le modï¿½le de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant au modï¿½le de l'ï¿½quipement.
    */
	public String getModele() {
		return modele;
	}

	/**
	* Affecte un nouveau modele ï¿½ l'ï¿½quipement
	*
	* @param modele
	*     modele de l'ï¿½quipement
	*     
	*/
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
     * Retourne le calife de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant au calife de l'ï¿½quipement.
    */
	public String getCalife() {
		return nomCalife;
	}

	/**
	* Affecte un nouveau calife ï¿½ l'ï¿½quipement
	*
	* @param calife
	*     calife de l'ï¿½quipement
	*     
	*/
	public void setCalife(String calife) {
		this.nomCalife = calife;
	}

	/**
     * Retourne l'identifiant de l'ï¿½quipement.
     * 
     * @return un entier correspondant ï¿½ l'identifiant de l'ï¿½quipement.
    */
	public int getId () {
		return this.idEquipement;
	}
	
	/**
	* Affecte un nouveau nom ï¿½ l'ï¿½quipement
	*
	* @param typeEquip
	*     nom de l'ï¿½quipement
	*     
	*/
	public void setNom (String typeEquip) {
		this.typeEquipement = typeEquip;
	}
	
	/**
     * Retourne le nom de l'ï¿½quipement.
     * 
     * @return une chaine de caractï¿½re correspondant au nom de l'ï¿½quipement.
    */
	public String getNom() {
		return this.typeEquipement;
	}

	/**
     * Retourne l'agent de l'ï¿½quipement.
     * 
     * @return l'agent de l'ï¿½quipement.
     * 
     * @see Agent
    */
	public Agent getAgent() {
		return agent;
	}

	/**
	* Affecte un nouvelle agent ï¿½ l'ï¿½quipement
	*
	* @param agent
	*     agent de l'ï¿½quipement
	* @see Agent
	*/
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
     * Retourne les logiciels de l'ï¿½quipement.
     * 
     * @return les logiciels de l'ï¿½quipement.
     * 
     * @see List
     * @see Logiciel
    */
	public List<Logiciel> getLogiciels() {
		return logiciels;
	}

	/**
	* Affecte une nouvelle liste de logiciels ï¿½ l'ï¿½quipement
	*
	* @param logiciels
	*     liste de logiciels de l'ï¿½quipement
	* @see List
	* @see Logiciel
	*/
	public void setLogiciels(List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}

	/**
     * Retourne le prix de l'ï¿½quipement.
     * 
     * @return le prix de l'ï¿½quipement.
     * 
    */
	public double getPrix() {
		return prix;
	}

	/**
	* Affecte un nouveau prix ï¿½ l'ï¿½quipement
	*
	* @param prix
	*     prix de l'ï¿½quipement
	*     
	*/
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
     * Retourne la date d'achat de l'ï¿½quipement.
     * 
     * @return la date d'achat de l'ï¿½quipement.
     * 
    */
	public String getDateAchat() {
		return dateGarantie;
	}

	public String getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getNomCalife() {
		return nomCalife;
	}

	public void setNomCalife(String nomCalife) {
		this.nomCalife = nomCalife;
	}

	/**
	* Affecte une nouvelle date d'achat ï¿½ l'ï¿½quipement
	*
	* @param dateAchat
	*     date d'achat de l'ï¿½quipement
	*     
	*/
	public void setDateAchat(String dateAchat) {
		this.dateGarantie = dateAchat;
	}
	
	
}
