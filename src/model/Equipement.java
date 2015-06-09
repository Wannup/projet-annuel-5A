package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Equipement est la classe repr�sentant un �quipement.
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
	 * Constructeur par d�faut
	 */
	public Equipement(){}
	
	/**
	 * Constructeur �quipement
	 *
	 * @param typeEquip
	 *     type de l'�quipement
	 * @param numeroPoste
	 *     num�ro de poste de l'�quipement
	 * @param agent
	 *     agent li� � l'�quipement
	 * @param prix
	 *     prix de l'�quipement
	 * @param nbJourPrev
	 *     nombre de jour
	 * @param dateGarantie
	 *     date de garantie
	 * @param marque
	 *     marque de l'�quipement
	 * @param modele
	 *     mod�le de l'�quipement
	 * @param calife
	 *     calife de l'�quipement
	 * @param info
	 *     information de l'�quipement
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
     * Retourne les information de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant aux information de l'�quipement.
    */
	public String getInfo() {
		return info;
	}

	/**
	* Affecte un nouveau nom � l'agent
	*
	* @param info
	*     information
	*     
	*/
	public void setInfo(String info) {
		this.info = info;
	}

	/**
     * Retourne l'identifiant de l'�quipement.
     * 
     * @return un entier correspondant � l'identifiant de l'�quipement.
    */
	public int getIdEquipement() {
		return idEquipement;
	}

	/**
	* Affecte un nouvelle identifiant � l'�quipement
	*
	* @param idEquipement
	*     identifiant de l'�quipement
	*     
	*/
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	/**
     * Retourne le type de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant au type de l'�quipement.
    */
	public String getTypeEquipement() {
		return typeEquipement;
	}

	/**
	* Affecte un nouveau type � l'�quipement
	*
	* @param typeEquipement
	*     type de l'�quipement
	*     
	*/
	public void setTypeEquipement(String typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	/**
     * Retourne la date de garantie de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant � la date de garantie de l'�quipement.
    */
	public String getDateGarantie() {
		return dateGarantie;
	}

	/**
	* Affecte une nouvelle date de garantie � l'�quipement
	*
	* @param dateGarantie
	*     date de garantie de l'�quipement
	*     
	*/
	public void setDateGarantie(String dateGarantie) {
		this.dateGarantie = dateGarantie;
	}

	/**
     * Retourne la marque de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant � la marque de l'�quipement.
    */
	public String getMarque() {
		return marque;
	}

	/**
	* Affecte une nouvelle marque � l'�quipement
	*
	* @param marque
	*     marque de l'�quipement
	*     
	*/
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
     * Retourne le mod�le de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant au mod�le de l'�quipement.
    */
	public String getModele() {
		return modele;
	}

	/**
	* Affecte un nouveau modele � l'�quipement
	*
	* @param modele
	*     modele de l'�quipement
	*     
	*/
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
     * Retourne le calife de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant au calife de l'�quipement.
    */
	public String getCalife() {
		return calife;
	}

	/**
	* Affecte un nouveau calife � l'�quipement
	*
	* @param calife
	*     calife de l'�quipement
	*     
	*/
	public void setCalife(String calife) {
		this.calife = calife;
	}

	/**
     * Retourne l'identifiant de l'�quipement.
     * 
     * @return un entier correspondant � l'identifiant de l'�quipement.
    */
	public int getId () {
		return this.idEquipement;
	}
	
	/**
	* Affecte un nouveau nom � l'�quipement
	*
	* @param typeEquip
	*     nom de l'�quipement
	*     
	*/
	public void setNom (String typeEquip) {
		this.typeEquipement = typeEquip;
	}
	
	/**
     * Retourne le nom de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant au nom de l'�quipement.
    */
	public String getNom() {
		return this.typeEquipement;
	}
	
	/**
     * Retourne le num�ro de poste de l'�quipement.
     * 
     * @return une chaine de caract�re correspondant au num�ro de poste de l'�quipement.
    */
	public int getNumeroPoste() {
		return numeroEquipement;
	}

	/**
	* Affecte un nouveau num�ro de poste � l'�quipement
	*
	* @param numeroPoste
	*     num�ro de poste de l'�quipement
	*     
	*/
	public void setNumeroPoste(int numeroPoste) {
		this.numeroEquipement = numeroPoste;
	}

	/**
     * Retourne l'agent de l'�quipement.
     * 
     * @return l'agent de l'�quipement.
     * 
     * @see Agent
    */
	public Agent getAgent() {
		return agent;
	}

	/**
	* Affecte un nouvelle agent � l'�quipement
	*
	* @param agent
	*     agent de l'�quipement
	* @see Agent
	*/
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
     * Retourne les logiciels de l'�quipement.
     * 
     * @return les logiciels de l'�quipement.
     * 
     * @see List
     * @see Logiciel
    */
	public List<Logiciel> getLogiciels() {
		return logiciels;
	}

	/**
	* Affecte une nouvelle liste de logiciels � l'�quipement
	*
	* @param logiciels
	*     liste de logiciels de l'�quipement
	* @see List
	* @see Logiciel
	*/
	public void setLogiciels(List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}

	/**
     * Retourne le prix de l'�quipement.
     * 
     * @return le prix de l'�quipement.
     * 
    */
	public double getPrix() {
		return prix;
	}

	/**
	* Affecte un nouveau prix � l'�quipement
	*
	* @param prix
	*     prix de l'�quipement
	*     
	*/
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
     * Retourne le num�ro de l'�quipement.
     * 
     * @return le num�ro de l'�quipement.
     * 
    */
	public int getNumeroEquipement() {
		return numeroEquipement;
	}

	/**
	* Affecte un nouveau num�ro � l'�quipement
	*
	* @param numeroEquipement
	*     num�ro de l'�quipement
	*     
	*/
	public void setNumeroEquipement(int numeroEquipement) {
		this.numeroEquipement = numeroEquipement;
	}

	/**
     * Retourne le nombre de jour de l'�quipement.
     * 
     * @return le nombre de l'�quipement.
     * 
    */
	public int getNbJoursPrevUtilisation() {
		return nbJoursPrevUtilisation;
	}

	/**
	* Affecte un nouveau nombre de jour � l'�quipement
	*
	* @param nbJoursPrevUtilisation
	*     nombre de jour de l'�quipement
	*     
	*/
	public void setNbJoursPrevUtilisation(int nbJoursPrevUtilisation) {
		this.nbJoursPrevUtilisation = nbJoursPrevUtilisation;
	}

	/**
     * Retourne la date d'achat de l'�quipement.
     * 
     * @return la date d'achat de l'�quipement.
     * 
    */
	public String getDateAchat() {
		return dateGarantie;
	}

	/**
	* Affecte une nouvelle date d'achat � l'�quipement
	*
	* @param dateAchat
	*     date d'achat de l'�quipement
	*     
	*/
	public void setDateAchat(String dateAchat) {
		this.dateGarantie = dateAchat;
	}
	
	
}
