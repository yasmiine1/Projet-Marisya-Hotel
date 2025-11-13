
package com.hotel.backend.entites;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DateReservation {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateDebut;
    private Date dateFin;
	
	public DateReservation(Long id, Date dateDebut, Date dateFin, Reservation reservation, Chambre chambre) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.reservation = reservation;
		this.chambre = chambre;
	}
	@ManyToOne
    @JsonIgnore  

    private Reservation reservation;

    @ManyToOne
    @JsonBackReference
    private Chambre chambre;
	public DateReservation() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

    // Getters et setters
}
