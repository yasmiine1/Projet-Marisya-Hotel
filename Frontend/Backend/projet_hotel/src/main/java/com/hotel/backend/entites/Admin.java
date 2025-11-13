
package com.hotel.backend.entites;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String login;
    private String motDePasse;
	public Admin(int id, String nom, String login, String motDePasse) {
		super();
		this.id = id;
		this.nom = nom;
		this.login = login;
		this.motDePasse = motDePasse;
	}
	public Admin() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

    

    // Getters et setters
}
