
package com.hotel.backend.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
	
	public Client(int id, String nom, String email, String motDePasse, List<Reservation> reservations, List<Avis> avis,
			List<Paiement> paiements, List<Offre> offres, List<Evenement> evenements) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.reservations = reservations;
		this.avis = avis;
		this.paiements = paiements;
		this.offres = offres;
		this.evenements = evenements;
	}
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "client")
    private List<Avis> avis;

    @OneToMany(mappedBy = "client")
    private List<Paiement> paiements;

    @ManyToMany
    private List<Offre> offres;

    @ManyToMany
    private List<Evenement> evenements;
    
	public Client() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public List<Avis> getAvis() {
		return avis;
	}
	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}
	public List<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}
	public List<Offre> getOffres() {
		return offres;
	}
	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
	public List<Evenement> getEvenements() {
		return evenements;
	}
	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

   

    // Getters et setters
}
