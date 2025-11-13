
package com.hotel.backend.entites;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Visiteur {
	 @Id
	    @GeneratedValue
	    private int id;

	    private String nom;
	    private String prenom;
	    private Date dateNaissance;
	    private String tel;
	    private String adresse;

	    public Visiteur() {
	    }

	    public Visiteur(int id, String nom, String prenom, Date dateNaissance, String tel, String adresse) {
	        this.id = id;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.dateNaissance = dateNaissance;
	        this.tel = tel;
	        this.adresse = adresse;
	    }

	    // Getters & Setters

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

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    public Date getDateNaissance() {
	        return dateNaissance;
	    }

	    public void setDateNaissance(Date dateNaissance) {
	        this.dateNaissance = dateNaissance;
	    }

	    public String getTel() {
	        return tel;
	    }

	    public void setTel(String tel) {
	        this.tel = tel;
	    }

	    public String getAdresse() {
	        return adresse;
	    }

	    public void setAdresse(String adresse) {
	        this.adresse = adresse;
	    }  
}
