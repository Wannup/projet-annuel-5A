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
	//private String renewalDate; 
	//(champ à calculer car le nombre d'année de renouvellement sur
	//un type d'équipement peut être modifier)
	private String dateGarantie;
	private String dateLivraison;
	private String dateAjoutEquipement;
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
	public Equipement(TypeEquipement typeEquip, Agent agent, double prix, String dateGarantie, String dateLivraison, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.prix = prix;
		this.dateGarantie = dateGarantie;
		this.dateLivraison = dateLivraison;
		this.marque = marque;
		this.modele = modele;
		this.nomCalife = calife;
		this.info = info;
	}
	
	public Equipement(TypeEquipement typeEquip, List<Logiciel> logiciels ,Agent agent, double prix, String dateGarantie, String dateLivraison, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.agent = agent;
		this.logiciels = logiciels;
		this.prix = prix;
		this.dateGarantie = dateGarantie;
		this.dateLivraison = dateLivraison;
		this.marque = marque;
		this.modele = modele;
		this.nomCalife = calife;
		this.info = info;
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
     * Retourne le type de l'équipement.
     * 
     * @return une chaine de caractère correspondant au type de l'équipement.
    */
	public TypeEquipement getTypeEquipement() {
		return typeEquipement;
	}

	/**
	* Affecte un nouveau type à l'équipement
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
	
}
