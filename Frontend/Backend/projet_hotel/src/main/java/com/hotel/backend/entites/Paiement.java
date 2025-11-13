
package com.hotel.backend.entites;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paiement {
    @Id
    @GeneratedValue
    private Long id;
    private float montant;
    @Column(name = "date_paiement")
    private Date date;
    private String moyen;
	
	public Paiement(Long id, float montant, Date date, String moyen, Client client, Reservation reservation) {
		super();
		this.id = id;
		this.montant = montant;
		this.date = date;
		this.moyen = moyen;
		this.client = client;
		this.reservation = reservation;
	}
	@ManyToOne
    private Client client;

    @OneToOne(mappedBy = "paiement")
    @JsonIgnore  

    private Reservation reservation;
	public Paiement() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMoyen() {
		return moyen;
	}
	public void setMoyen(String moyen) {
		this.moyen = moyen;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

   
    // Getters et setters
}
