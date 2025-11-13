
package com.hotel.backend.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private int id;
    private String statut;
    private String typeReservation;
	
    
	public Reservation(int id, String statut, String typeReservation, Client client, Paiement paiement,
			List<DateReservation> dateReservations) {
		super();
		this.id = id;
		this.statut = statut;
		this.typeReservation = typeReservation;
		this.client = client;
		this.paiement = paiement;
		this.dateReservations = dateReservations;
	}
	@ManyToOne
    @JsonIgnore    // ne suit pas le lien vers Client (empêche la boucle)

    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paiement_id")

    @JsonIgnore  

    private Paiement paiement;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)    
    @JsonIgnore  

    private List<DateReservation> dateReservations;
	public Reservation() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getTypeReservation() {
		return typeReservation;
	}
	public void setTypeReservation(String typeReservation) {
		this.typeReservation = typeReservation;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Paiement getPaiement() {
		return paiement;
	}
	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}
	public List<DateReservation> getDateReservations() {
		return dateReservations;
	}
	public void setDateReservations(List<DateReservation> dateReservations) {
		this.dateReservations = dateReservations;
	}

    
    // Getters et setters
}
