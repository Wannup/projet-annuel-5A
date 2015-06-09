package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pole {

	@Id
	@GeneratedValue
	private int idPole;
	
	private String nom;
	
	
	public Pole(){}
	public Pole(String nom) {
		this.nom = nom;
	}
	
	public int getId () {
		return this.idPole;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
