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
	private TypeEquipement typeEquipement;
	private String renewalDate; 
	private String dateGarantie;
	private String dateLivraison;
	private String dateAjoutEquipement;
	private String marque;
	private String modele;
	private String nomCalife;
	private String info;
	private Agent agent;
	private Pole pole;
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
	 *     agent lié é l'équipement
	 * @param prix
	 *     prix de l'équipement
	 * @param dateGarantie
	 *     date de garantie
	 * @param marque
	 *     marque de l'équipement
	 * @param modele
	 *     modéle de l'équipement
	 * @param calife
	 *     calife de l'équipement
	 * @param info
	 *     information de l'équipement
	 * @see Agent
	 */
	public Equipement(TypeEquipement typeEquip, Agent agent, Pole pole, double prix, String dateGarantie, String dateLivraison, String renewalDate, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.pole = pole;
		this.prix = prix;
		this.dateGarantie = dateGarantie;
		this.dateLivraison = dateLivraison;
		this.renewalDate = renewalDate;
		this.marque = marque;
		this.modele = modele;
		this.nomCalife = calife;
		this.info = info;
	}
	
	public Equipement(TypeEquipement typeEquip, List<Logiciel> logiciels ,Agent agent, Pole pole, double prix, String dateGarantie, String dateLivraison, String renewalDate, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.pole = pole;
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

	/**
     * Retourne les informations de l'équipement.
     * 
     * @return une chaine de caractére correspondant aux informations de l'équipement.
    */
	public String getInfo() {
		return this.info;
	}

	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
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
     * Retourne l'identifiant de l'équipement.
     * 
     * @return un entier correspondant à l'identifiant de l'équipement.
    */
	public int getIdEquipement() {
		return this.idEquipement;
	}

	/**
	* Affecte un nouvelle identifiant é l'équipement
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
     * @return une chaine de caractére correspondant au type de l'équipement.
    */
	public TypeEquipement getTypeEquipement() {
		return this.typeEquipement;
	}

	/**
	* Affecte un nouveau type é l'équipement
	*
	* @param typeEquipement
	*     type de l'équipement
	*     
	*/
	public void setTypeEquipement(TypeEquipement typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	/**
     * Retourne la date de garantie de l'équipement.
     * 
     * @return une chaine de caractére correspondant é la date de garantie de l'équipement.
    */
	public String getDateGarantie() {
		return this.dateGarantie;
	}

	/**
	* Affecte une nouvelle date de garantie é l'équipement
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
     * @return une chaine de caractére correspondant é la marque de l'équipement.
    */
	public String getMarque() {
		return marque;
	}

	/**
	* Affecte une nouvelle marque é l'équipement
	*
	* @param marque
	*     marque de l'équipement
	*     
	*/
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
     * Retourne le modéle de l'équipement.
     * 
     * @return une chaine de caractére correspondant au modéle de l'équipement.
    */
	public String getModele() {
		return modele;
	}

	/**
	* Affecte un nouveau modele é l'équipement
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
     * @return une chaine de caractére correspondant au calife de l'équipement.
    */
	public String getCalife() {
		return nomCalife;
	}

	/**
	* Affecte un nouveau calife é l'équipement
	*
	* @param calife
	*     calife de l'équipement
	*     
	*/
	public void setCalife(String calife) {
		this.nomCalife = calife;
	}

	/**
     * Retourne l'identifiant de l'équipement.
     * 
     * @return un entier correspondant é l'identifiant de l'équipement.
    */
	public int getId () {
		return this.idEquipement;
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
	* Affecte un nouvelle agent é l'équipement
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
	* 
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

	public String getDateAjoutEquipement() {
		return dateAjoutEquipement;
	}

	/**
	 * @return the pole
	 */
	public Pole getPole() {
		return pole;
	}

	/**
	 * @param pole the pole to set
	 */
	public void setPole(Pole pole) {
		this.pole = pole;
	}	
	
	
	
}
