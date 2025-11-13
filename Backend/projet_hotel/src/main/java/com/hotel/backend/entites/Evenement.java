
package com.hotel.backend.entites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Evenement {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String lieu;
    @Column(name = "date_evenement")
    private Date date_e;
    @ManyToMany(mappedBy = "evenements")
    private List<Client> participants;

	
	public Evenement(Long id, String nom, String lieu, Date date_e, List<Client> participants) {
		super();
		this.id = id;
		this.nom = nom;
		this.lieu = lieu;
		this.date_e = date_e;
		this.participants = participants;
	}
	public Evenement() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Date getDate() {
		return date_e;
	}
	public void setDate(Date date_e) {
		this.date_e = date_e;
	}
	public Date getDate_e() {
		return date_e;
	}
	public void setDate_e(Date date_e) {
		this.date_e = date_e;
	}
	public List<Client> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Client> participants) {
		this.participants = participants;
	}

   

    // Getters et setters
}
