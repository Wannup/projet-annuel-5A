package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * model class Equipement
 * @author Charly FAROT
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
	private String marque;
	private String modele;
	private String nomCalife;
	private String info;
	private Agent agent;
	private Pole pole;
	private List<Logiciel> logiciels;
	private double prix;
	
	/**
	 * Equipement default constructor
	 */
	public Equipement(){}
	
	/**
	 * Equipement constructor without lstLogiciel
	 *
	 * @param typeEquip
	 * @param agent
	 * @param pole
	 * @param prix
	 * @param dateGarantie
	 * @param dateLivraison
	 * @param renewalDate
	 * @param marque
	 * @param modele
	 * @param calife
	 * @param info
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
	
	/**
	 * Equipement constructor with lstLogiciel
	 *
	 * @param typeEquip
	 * @param logiciels
	 * @param agent
	 * @param pole
	 * @param prix
	 * @param dateGarantie
	 * @param dateLivraison
	 * @param renewalDate
	 * @param marque
	 * @param modele
	 * @param calife
	 * @param info
	 */
	public Equipement(TypeEquipement typeEquip, List<Logiciel> logiciels ,Agent agent, Pole pole, double prix, String dateGarantie, String dateLivraison, String renewalDate, String marque, String modele, String calife, String info) {
		this.typeEquipement = typeEquip;
		this.logiciels = logiciels;
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

	/**
	 * @return the idEquipement
	 */
	public int getIdEquipement() {
		return idEquipement;
	}

	/**
	 * @param idEquipement the idEquipement to set
	 */
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	/**
	 * @return the typeEquipement
	 */
	public TypeEquipement getTypeEquipement() {
		return typeEquipement;
	}

	/**
	 * @param typeEquipement the typeEquipement to set
	 */
	public void setTypeEquipement(TypeEquipement typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	/**
	 * @return the renewalDate
	 */
	public String getRenewalDate() {
		return renewalDate;
	}

	/**
	 * @param renewalDate the renewalDate to set
	 */
	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	/**
	 * @return the dateGarantie
	 */
	public String getDateGarantie() {
		return dateGarantie;
	}

	/**
	 * @param dateGarantie the dateGarantie to set
	 */
	public void setDateGarantie(String dateGarantie) {
		this.dateGarantie = dateGarantie;
	}

	/**
	 * @return the dateLivraison
	 */
	public String getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * @param dateLivraison the dateLivraison to set
	 */
	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the nomCalife
	 */
	public String getNomCalife() {
		return nomCalife;
	}

	/**
	 * @param nomCalife the nomCalife to set
	 */
	public void setNomCalife(String nomCalife) {
		this.nomCalife = nomCalife;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
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

	/**
	 * @return the logiciels
	 */
	public List<Logiciel> getLogiciels() {
		return logiciels;
	}

	/**
	 * @param logiciels the logiciels to set
	 */
	public void setLogiciels(List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
